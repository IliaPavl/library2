package com.example.libra.servise;

import com.example.libra.domain.*;
import com.example.libra.reposit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PagesPerMinute;
import java.sql.Date;
import java.util.*;

@Service
public class BookService extends GenreServise{

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private AgeRateRepo ageRateRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PageRepo pageRepo;

    @Autowired
    private UserPropertyRepo userPropertyRepo;
    @Autowired
    private MarksRepo marksRepo;

    @Autowired
    private LikeRepo likeRepo;

    @Autowired
    private LibraryUserRepo libraryUserRepo;

    @Autowired
    private CommentRepo commentRepo;

    public void addBook(String nameBook, String about, Map<String, String> form, String ageRate, String status, User user, String filname){
        Book book=new Book();
        book.setAbout(about);
        book.setNameBook(nameBook);
        book.setDatePublish();
        book.setAgeRate(ageRateRepo.findByAgeRate(ageRate));
        book.setStatus(statusRepo.findByNameStatus(status));
        book.setAuthor(user);
        book.setDateUpdateReg();

        book.setImgBook(filname);


        ArrayList< Genre> genre=new ArrayList<>();
        Genre genreBuff;
        for (String key : form.keySet()) {
            genreBuff=genreRepo.findByNameGenre(key);
            if (genreBuff !=null ) {
                genre.add(genreBuff);
            }
        }
        book.setGenres(genre);
        bookRepo.save(book);

        Set<Role> roles= user.getRoles();
        roles.add(Role.AUTHOR);
        user.setRoles(roles);
        userRepo.save(user);
    }

    public void deleteMark(Marks marks){
        marksRepo.delete(marks);
    }

    public void deleteBook(Book book){
        List<Marks>marksList=marksRepo.findAllByBook(book);
        for(Marks marks:marksList){
            marksRepo.delete(marks);
        }

        List<LikeBooks>likeBooksList=likeRepo.findAllByBook(book);
        for(LikeBooks likeBooks:likeBooksList){
            likeRepo.delete(likeBooks);
        }

        List<LibraryUser>libraryUserList=libraryUserRepo.findAllByBook(book);
        for(LibraryUser l:libraryUserList){
            libraryUserRepo.delete(l);
        }

        List<Coment> comentList=commentRepo.findAllByBook(book);
        for(Coment c:comentList){
            commentRepo.delete(c);
        }

        List<Page> pageList=pageRepo.findAllByIdBook(book);
        for(Page p:pageList){
            pageRepo.delete(p);
        }

        bookRepo.delete(book);
    }
    public List<Book> findAllBook(){
        return bookRepo.findAll();
    }

    public List<Book> findByNamebook(String namebook) {
        return bookRepo.findByNameBookContaining(namebook);
    }

    public List<Genre> findAllGenres(){
        return genreRepo.findAll();
    }

    public List<Status> findAllStatus(){
        return statusRepo.findAll();
    }

    public List<AgeRate> findAllAgeRate(){
        return ageRateRepo.findAll();
    }

    public List<Status> findByStatus(Long id){return statusRepo.findAllById(id);}

    public List<AgeRate> findByAgeRate(Long id){return ageRateRepo.findAllById(id);}

    public List<Genre> findByGenre(Long id){return genreRepo.findAllByIdGenre(id);}

    public ArrayList<Book> sort(ArrayList<Book> books, int allTypes) {
        Collections.sort(books,new CompareToSearchBook(allTypes));
        return books;
    }

    public List<Book> findAllBooksUserWrite(User user) {
        return bookRepo.findAllByAuthor(user);
    }

    public Book findById(Long idBook){
        Book book=new Book();
        if(bookRepo.findById(idBook).isPresent())
            book=bookRepo.findById(idBook).get();
        return book;
    }

    public void updateBook(String idBook, String nameBook, String about, String ageRate, String status, String idGenreDel, String idGenreAdd, String saveFile) {
        Book book=bookRepo.findById(Long.parseLong(idBook)).get();
        List<Book> list=new ArrayList<>();
        list.add(book);
        list=fixBook(list);
        book=list.get(0);
        if(!nameBook.isEmpty()){
            book.setNameBook(nameBook);
        }
        if(!about.isEmpty()){
            book.setAbout(about);
        }
        if(!ageRate.isEmpty()){
            book.setAgeRate(ageRateRepo.findById(Long.parseLong(ageRate)).get());
        }
        if(!status.isEmpty()){
            book.setStatus(statusRepo.findById(Long.parseLong(status)).get());
        }
        if(!idGenreDel.isEmpty()){
            Genre buf=genreRepo.findByIdGenre(Long.parseLong(idGenreDel)).get();
            List<Genre> genres= book.getGenres();
            for(Genre genre:genres){
                if(buf.equals(genre)){
                    genres.remove(genre);
                    break;
                }
            }
            book.setGenres(genres);
        }
        if(!idGenreAdd.isEmpty() && !idGenreAdd.equals("0")){
            boolean isNew=true;
            Genre buf2=genreRepo.findByIdGenre(Long.parseLong(idGenreAdd)).get();
            Book books=bookRepo.findById(Long.parseLong(idBook)).get();
            List<Genre> genres=books.getGenres();
            List<Genre> genres1=new ArrayList<>();
            boolean add=false;
            Genre buf=new Genre();
            for (Genre genre:genres){
                if(genres1.size()==0)
                    genres1.add(genre);
                else
                    for(Genre genre2:genres1){
                        if(!genre.equals(genre2)){
                            add=true;
                            buf=genre;
                        }else {add=false;}
                    }
                if(add){
                    genres1.add(buf);
                    add=false;
                }
            }
            for(Genre genre:genres1){
                if(buf2.equals(genre)){
                    isNew=false;
                    break;
                }
            }
            if(isNew)
                genres1.add(buf2);
            book.setGenres(genres1);
        }
        if(saveFile!=null){
            book.setImgBook(saveFile);
        }
        book.setDateUpdateReg();
        bookRepo.save(book);
    }

    public List<PageInfo> findAllInfoPages(long idBook) {
        List<Page> pages= pageRepo.findAllByIdBook(bookRepo.findById(idBook).get());
        List<PageInfo> pageInfos=new ArrayList<>();
        PageInfo pageInfo;
        for(Page page:pages){
            pageInfo = new PageInfo();
            pageInfo.setId(page.getId().longValue());
            pageInfo.setIdBook(page.getIdBook().getId());
            pageInfo.setNamePage(page.getNamePage());
            pageInfo.setNumberChapter(page.getNumberChapter());
            pageInfo.setNumberPage(page.getNumberPage());
            pageInfos.add(pageInfo);
        }
        return pageInfos;
    }

    public Page findPageById(long idPage) {
        Page page=new Page();
        if(pageRepo.findById(idPage).isPresent())
            page=pageRepo.findById(idPage).get();
        return page;
    }

    public void addPage(String text, String idBook, String chapter, String namePage, String page, String idPage) {
        Page page1=new Page();
        if(!idPage.equals(""))
            page1.setId(Long.parseLong( idPage));
        page1.setIdBook(bookRepo.findById(Long.parseLong(idBook)).get());
        page1.setNamePage(namePage);
        page1.setTextPage(text);
        page1.setNumberPage(Integer.parseInt(page));
        page1.setNumberChapter(Integer.parseInt(chapter));
        pageRepo.save(page1);
        int lenght = 0;
        Book book=bookRepo.findById(Long.parseLong(idBook)).get();
        List<Page> pageList=pageRepo.findAllByIdBook(bookRepo.findById(Long.parseLong(idBook)).get());
        for(Page page2:pageList){
            lenght+=page2.getTextPage().length();
        }
        book.setLenghtBook(lenght);
        bookRepo.save(book);
    }

    public PageInfo findPageInfoById(long idPage) {
        Page page;
        PageInfo pageInfo = new PageInfo();
        if(pageRepo.findById(idPage).isPresent()) {
            page = pageRepo.findById(idPage).get();
            pageInfo.setId(page.getId().longValue());
            pageInfo.setIdBook(page.getIdBook().getId());
            pageInfo.setNamePage(page.getNamePage());
            pageInfo.setNumberChapter(page.getNumberChapter());
            pageInfo.setNumberPage(page.getNumberPage());
        }
        return pageInfo;
    }

    public List<Book> find20UpdateBooks() {
        return bookRepo.getTop20ByDateUpdateLessThanEqual(new Date(Calendar.getInstance().getTime().getTime()));
    }

    public ArrayList<Book> vipSort(List<Book> books){
        ArrayList<Book> books1=new ArrayList<>();
        ArrayList<Book> books2=new ArrayList<>();
        for(Book book:books){
            if(book.getAuthor().getVip()==true)
                books1.add(book);
            else
                books2.add(book);
        }
        books1.addAll(books2);
        return books1;
    }

    public Page findPageByIdBook(Long idBook){
        return pageRepo.findFirstByIdBook(bookRepo.findById(idBook).get());
    }

    public Page findByIdPage(Long idPage) {
        return pageRepo.findById(idPage).get();
    }

    public Page findPageByIdBookAndChapterAndPage(String idBook, String chapter, String page) {
        return pageRepo.findByIdBookAndNumberChapterAndNumberPage(bookRepo.findById(Long.parseLong(idBook)).get(),Integer.parseInt(chapter),Integer.parseInt(page));
    }

    public UserPropertyPage findUserPropert(User user) {
        return userPropertyRepo.findByUser(user);
    }

    public void savePropertyPage(User user, String bg, String ct, String st) {
        UserPropertyPage userPropertyPage= userPropertyRepo.findByUser(user);
        if(!bg.equals("")){
            userPropertyPage.setColorBorder(bg);
        }
        if(!ct.equals("")){
            userPropertyPage.setColorText(ct);
        }
        if(!st.equals("")){
            userPropertyPage.setSizeText(st);
        }
        userPropertyRepo.save(userPropertyPage);
    }


    public void addSee(Long id) {
        Book book=bookRepo.findById(id).get();
        book.setViews(book.getViews()+1);
        bookRepo.save(book);
    }

    public List<Book> fixBook(List<Book> books) {
        for (Book book:books){
            List<Genre> genres=book.getGenres();
            List<Genre> genres1=new ArrayList<>();
            boolean add=false;
            Genre buf=new Genre();
            for (Genre genre:genres){
                if(genres1.size()==0)
                    genres1.add(genre);
                else
                    for(Genre genre2:genres1){
                        if(!genre.equals(genre2)){
                            add=true;
                            buf=genre;
                        }else {add=false;}
                    }
                if(add){
                    genres1.add(buf);
                    add=false;
                }
            }
            book.setGenres(genres1);
        }
        return books;
    }
}

