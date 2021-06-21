package com.example.libra.servise;

import com.example.libra.domain.*;
import com.example.libra.reposit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CometsServise {

    private final CommentRepo comentRepo;

    private final ComentToUserRepo comentToUserRepo;

    private final UserSevice userSevice;

    private final SupportRepo supportRepo;

    private final PageRepo pageRepo;

    private final BookService bookService;

    private final LikeRepo likeRepo;

    private final MarksRepo marksRepo;

    private final BookRepo bookRepo;

    private final RecenzRepo recenzRepo;

    public CometsServise(CommentRepo comentRepo, ComentToUserRepo comentToUserRepo, UserSevice userSevice, SupportRepo supportRepo, PageRepo pageRepo, BookService bookService, LikeRepo likeRepo, MarksRepo marksRepo, BookRepo bookRepo, RecenzRepo recenzRepo) {
        this.comentRepo = comentRepo;
        this.comentToUserRepo = comentToUserRepo;
        this.userSevice = userSevice;
        this.supportRepo = supportRepo;
        this.pageRepo = pageRepo;
        this.bookService = bookService;
        this.likeRepo = likeRepo;
        this.marksRepo = marksRepo;
        this.bookRepo = bookRepo;
        this.recenzRepo = recenzRepo;
    }

    public List<ComentToUser> getComentsToUser(Long idUser,Long idAuthor,boolean isPerson){

        ArrayList<ComentToUser> comentToUserFirst=comentToUserRepo.findAllByAuthorAndPersonalAndPageUser(userSevice.findById(idAuthor),isPerson,userSevice.findById(idUser));
        ArrayList<ComentToUser> comentToUserSecond=comentToUserRepo.findAllByAuthorAndPersonalAndPageUser(userSevice.findById(idUser),isPerson,userSevice.findById(idAuthor));
        ArrayList<ComentToUser> buffer=new ArrayList<>();

        int sizeMax=0,sizeFirst=comentToUserFirst.size(),sizeSecond=comentToUserSecond.size();

        sizeMax+=sizeFirst;
        sizeMax+=sizeSecond;

        int x1=0,x2=0;

        for(int i=1;i<=sizeMax;i++) {
            if(x1<sizeFirst)
            if (comentToUserFirst.get(x1).getNumberMes()==i) {
                buffer.add(comentToUserFirst.get(x1));
                x1++;
            }
            if(x2<sizeSecond)
            if (comentToUserSecond.get(x2).getNumberMes()==i) {
                buffer.add(comentToUserSecond.get(x2));
                x2++;
            }
        }
        return buffer;
    }

    public void addComentToSuer(Long idUser,Long idAuthor,String coment,boolean isPerson){

        ArrayList<ComentToUser> comentToUserFirst=comentToUserRepo.findAllByAuthorAndPersonalAndPageUser(userSevice.findById(idAuthor),isPerson,userSevice.findById(idUser));
        ArrayList<ComentToUser> comentToUserSecond=comentToUserRepo.findAllByAuthorAndPersonalAndPageUser(userSevice.findById(idUser),isPerson,userSevice.findById(idAuthor));
        ComentToUser buffer=new ComentToUser();
        int number1=-1,number2=-1;

        if(comentToUserFirst.size()!=0)
        number1=comentToUserFirst.get(comentToUserFirst.size()-1).getNumberMes();

        if(comentToUserSecond.size()!=0)
            number2=comentToUserSecond.get(comentToUserSecond.size()-1).getNumberMes();

        buffer.setAuthor(userSevice.findById(idAuthor));
        buffer.setPageUser(userSevice.findById(idUser));
        buffer.setComent(coment);
        buffer.setPersonal(isPerson);
        buffer.setIsitnew(false);
        buffer.setRead(false);

        if(number1>number2 ){
            number1++;
            buffer.setNumberMes(number1);
            comentToUserRepo.save(buffer);
        }else if(number1<number2){
            number2++;
            buffer.setNumberMes(number2);
            comentToUserRepo.save(buffer);
        }else {

            if(number2==-1) {
                buffer.setIsitnew(true);
                number1 = 1;
            }else {
                number1++;
            }

            buffer.setNumberMes(number1);
            comentToUserRepo.save(buffer);
        }
    }

    public List<ComentToUser> findNewMesToUser(User user, boolean isPerson) {


        List<ComentToUser> comentToUsers=comentToUserRepo.findAllByPageUserAndPersonalAndIsitnew(user,isPerson,true);
        if(comentToUsers.size()==0)
            comentToUsers=comentToUserRepo.findAllByAuthorAndPersonalAndIsitnew(user,isPerson,true);
        return comentToUsers;

    }

//    public ComentToUser findMesToUserById(Long idMes){
//        return comentToUserRepo.findById(idMes).get();
//    }

    public void idReadFalse(User user){
        ComentToUser comentToUser= comentToUserRepo.findFirstByPageUser(user);
        comentToUser.setRead(false);
        comentToUserRepo.save(comentToUser);
    }

    public List<Support> findMesSupport(boolean isNew) {
        return supportRepo.findAllByIsitnew(isNew);
    }

    public void addNewSupport(User user1, String comment) {
        Support support=new Support(user1,comment);
        support.setIsitnew(true);
        supportRepo.save(support);
    }

    public List<Support> findUserMesSupport(User user) {
        return supportRepo.findAllByAuthor(user);
    }

    public List<Support> findUserMesSupportById(Long idMes) {
        return supportRepo.findAllById(idMes);
    }

    public void saveUpdate(String textMessage, Long idMes) {
        List<Support> supports= supportRepo.findAllById(idMes);
        supports.get(0).setAnswer(textMessage);
        supports.get(0).setIsitnew(false);
        supportRepo.save(supports.get(0));
    }

    public List<ComentToUser> findMessageForUserProf(User author) {

        return comentToUserRepo.findAllByPageUserAndPersonal(author,false);
    }

    public void addMessageForUserProf(User author,User pageUser,String text){
        ComentToUser buffer = new ComentToUser();
        List<ComentToUser> list;

        if(author.getId().equals(pageUser.getId())){
             list=findMessageForUserProf(pageUser);

        }else {
             list = comentToUserRepo.findAllByAuthorAndPersonal(pageUser, false);
        }
            int number1;
            number1=list.size();

            buffer.setAuthor(pageUser);
            buffer.setPageUser(author);
            buffer.setComent(text);
            buffer.setPersonal(false);
            buffer.setIsitnew(false);
            buffer.setRead(false);


            if (number1 == 0) {
                buffer.setIsitnew(true);
                number1 = 1;
            } else {
                number1++;
            }
            buffer.setNumberMes(number1);
            comentToUserRepo.save(buffer);


    }

    public List<Coment> findAllComentToPage(Long idPage) {
        List<Coment> coments=new ArrayList<>();
        if(pageRepo.findById(idPage).isPresent())
        coments=comentRepo.findAllByPage(pageRepo.findById(idPage).get());
        return coments;
    }

    public void saveMessageToPage(User user, Long idPage, String message, Long idBook) {
        if(!message.equals("")){
            Coment coment=new Coment(user,pageRepo.findById(idPage).get(),message,bookService.findById(idBook));
            comentRepo.save(coment);
        }
    }

    public List<Coment> findAllComentTiBook(String idBook) {
        Book book=bookService.findById(Long.parseLong(idBook));
        return comentRepo.findAllByBook(book);
    }

    public boolean findIsLikeBook(User user, String idBook) {
        boolean like=false;
        Optional<LikeBooks> likeBooks=likeRepo.findByBookAndUser(bookService.findById(Long.parseLong(idBook)),user);
        if(likeBooks.isPresent()){
            like=likeBooks.get().isLike();
        }
        return like;
    }

    public void addLikeToBook(User user,String idBook,boolean like){
        Optional<LikeBooks> likeBooks=likeRepo.findByBookAndUser(bookService.findById(Long.parseLong(idBook)),user);
        if(likeBooks.isPresent()){
            if(like) {
                like = false;
                Book book= bookService.findById(Long.parseLong(idBook));
                book.setLikes(book.getLikes()-1);
                bookRepo.save(book);
            }
            else {
                Book book= bookService.findById(Long.parseLong(idBook));
                book.setLikes(book.getLikes()+1);
                bookRepo.save(book);
                like = true;
            }
            likeBooks.get().setLike(like);
            likeRepo.save(likeBooks.get());
        }else{
            LikeBooks likeBooks1=new LikeBooks(user,bookService.findById(Long.parseLong(idBook)),true);
            likeRepo.save(likeBooks1);
        }
    }

    public void addMark(User user,Long idBook,int mark){
        Book book=bookService.findById(idBook);
        Optional<Marks> marks;
        marks=marksRepo.findByUserAndBook(user,book);
        if(marks.isPresent())
        {
            marks.get().setMark(mark);
            marksRepo.save(marks.get());
        }else{
            Marks marks2=new Marks();
            marks2.setBook(book);
            marks2.setUser(user);
            marks2.setMark(mark);
            marksRepo.save(marks2);
        }
        List<Marks> marksList=marksRepo.findAllByBook(book);
        float sum=0;
        for(Marks marks1:marksList){
            sum+=marks1.getMark();
        }
        double o=sum/marksList.size();
        book.setRating(o);
        bookRepo.save(book);
    }

    public int getMark(User user,Long idBook){
        Book book=bookService.findById(idBook);
        Optional<Marks> marks;
        marks=marksRepo.findByUserAndBook(user,book);
        if(marks.isPresent())
        return marks.get().getMark();
        else {
            Marks marks1=new Marks();
            return marks1.getMark();
        }
    }

    public List<Recenz> listRecenz(Long idBook){
        Optional<Book> book=bookRepo.findById(idBook);
        List<Recenz> recenzs=new ArrayList<>();
        if(book.isPresent()) {
            recenzs = recenzRepo.findAllByBookid(book.get());
        }
        return recenzs;
    }

    public Recenz findRecensByBookAndUser(User user, Book book) {
        Recenz recenz=recenzRepo.findByAuthorAndBookid(user,book);
        if (recenz==null) {
            recenz= new Recenz();
            recenz.setAuthor(user);
            recenz.setBookid(book);
            recenz.setRecenzText("");
        }
        return recenz;
    }

    public List<Recenz> findAllRecens(User user) {
        return recenzRepo.findAllByAuthor(user);
    }

    public void delRec(User user, Book byId) {
        recenzRepo.delete(recenzRepo.findByAuthorAndBookid(user,byId));
    }

    public void saveRec(User user, Book book, String coment) {
        Recenz recenz;
        if(!coment.isEmpty()) {
            recenz= new Recenz();
            recenz.setAuthor(user);
            recenz.setBookid(book);
            recenz.setRecenzText(coment);
            recenzRepo.save(recenz);
        }
    }
}
