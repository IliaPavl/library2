package com.example.libra.servise;

import com.example.libra.domain.*;
import com.example.libra.reposit.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class UserSevice extends FileService implements UserDetailsService {
    private final UserRepo userRepo;

    private final SubsRepo subsRepo;

    private final UserPersonalRepo userPersonalRepo;

    private final PasswordEncoder passwordEncoder;

    private final FriendsRepo friendsRepo;

    private final UserPropertyRepo userPropertyRepo;

    private final MarksRepo marksRepo;

    private final LikeRepo likeRepo;

    private final LibraryUserRepo libraryUserRepo;

    private final CommentRepo commentRepo;

    private final ComentToUserRepo comentToUserRepo;

    private final SupportRepo supportRepo;

    private final BookRepo bookRepo;

    private final PageRepo pageRepo;

    public UserSevice(UserPersonalRepo userPersonalRepo, UserRepo userRepo, SubsRepo subsRepo, PasswordEncoder passwordEncoder, PageRepo pageRepo, FriendsRepo friendsRepo, UserPropertyRepo userPropertyRepo, MarksRepo marksRepo, LikeRepo likeRepo, LibraryUserRepo libraryUserRepo, SupportRepo supportRepo, CommentRepo commentRepo, BookRepo bookRepo, ComentToUserRepo comentToUserRepo) {
        this.userPersonalRepo = userPersonalRepo;
        this.userRepo = userRepo;
        this.subsRepo = subsRepo;
        this.passwordEncoder = passwordEncoder;
        this.pageRepo = pageRepo;
        this.friendsRepo = friendsRepo;
        this.userPropertyRepo = userPropertyRepo;
        this.marksRepo = marksRepo;
        this.likeRepo = likeRepo;
        this.libraryUserRepo = libraryUserRepo;
        this.supportRepo = supportRepo;
        this.commentRepo = commentRepo;
        this.bookRepo = bookRepo;
        this.comentToUserRepo = comentToUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
                return false;
        }
        user.setUserImg(getStandartProfileFileName());
        user.setUserFontImg(getStandartProfileFontName());
        user.setVip(false);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserPersonal(userPersonalRepo.save(new UserPersonal()));

        userRepo.save(user);
        UserPropertyPage userPropertyPage=new UserPropertyPage("#000000","12","#FFFFFF",user);
        userPropertyRepo.save(userPropertyPage);
        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username,String email, Map<String, String> form) {
        user.setUsername(username);

        if(!email.equals(user.getEmail()))
        user.setEmail(email);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

    public void updateProfile(User user, String password, String email, String fileName) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if(fileName!=null)
        user.setUserImg(fileName);

        if (isEmailChanged) {
            user.setEmail(email);
        }

        if (!isEmpty(password)) {
            user.setPassword(password);
        }

        userRepo.save(user);
    }

    public void deleteProfile(User user){

        List<Marks>marksList=marksRepo.findAllByUser(user);
        for(Marks marks:marksList){
            marksRepo.delete(marks);
        }

        List<LikeBooks>likeBooksList=likeRepo.findAllByUser(user);
        for(LikeBooks likeBooks:likeBooksList){
            likeRepo.delete(likeBooks);
        }

        List<LibraryUser>libraryUserList=libraryUserRepo.findAllByUser(user);
        for(LibraryUser l:libraryUserList){
            libraryUserRepo.delete(l);
        }

        List<Coment> comentList=commentRepo.findAllByAuthor(user);
        for(Coment c:comentList){
            commentRepo.delete(c);
        }

        List<ComentToUser> comentToUsers=comentToUserRepo.findAllByAuthor(user);
        for(ComentToUser c:comentToUsers){
            comentToUserRepo.delete(c);
        }

        List<ComentToUser> comentToUsers2=comentToUserRepo.findAllByPageUser(user);
        for(ComentToUser c:comentToUsers2){
            comentToUserRepo.delete(c);
        }

        List<Subs> subs=subsRepo.findAllByIdAuthor(user.getId());
        for(Subs c:subs){
            subsRepo.delete(c);
        }

        List<Subs> subs1=subsRepo.findAllByIdWhoSub(user.getId());
        for(Subs c:subs1){
            subsRepo.delete(c);
        }

        List<Friends> friends=friendsRepo.findAllByWhoSubFriend(user);
        for(Friends c:friends){
            friendsRepo.delete(c);
        }

        List<Friends> friends1=friendsRepo.findAllByAuthorFriend(user);
        for(Friends c:friends1){
            friendsRepo.delete(c);
        }

        List<Support> supports=supportRepo.findAllByAuthor(user);
        for(Support c:supports){
            supportRepo.delete(c);
        }

        List<Book> books=bookRepo.findAllByAuthor(user);
        for(Book book:books){
            List<Marks> marksList1=marksRepo.findAllByBook(book);
            for(Marks marks:marksList1){
                marksRepo.delete(marks);
            }

            List<LikeBooks>likeBooksList1=likeRepo.findAllByBook(book);
            for(LikeBooks likeBooks:likeBooksList1){
                likeRepo.delete(likeBooks);
            }

            List<LibraryUser>libraryUserList1=libraryUserRepo.findAllByBook(book);
            for(LibraryUser l:libraryUserList1){
                libraryUserRepo.delete(l);
            }

            List<Coment> comentList1=commentRepo.findAllByBook(book);
            for(Coment c:comentList1){
                commentRepo.delete(c);
            }

            List<Page> pageList=pageRepo.findAllByIdBook(book);
            for(Page p:pageList){
                pageRepo.delete(p);
            }

            bookRepo.delete(book);
        }

        userPropertyRepo.delete(userPropertyRepo.findByUser(user));


        userRepo.delete(user);
    }

    public void vipControl(User user) {
        user.setVip(!user.getVip());
        userRepo.save(user);
    }

    public void activControl(User user) {
        user.setActive(!user.isActive());
        userRepo.save(user);
    }

    public User findById(Long id){
        return userRepo.findAllById(id).get(0);
    }

    public Object findBy(String searchBy, String search) {

        switch (searchBy) {
            case ",id":
                return userRepo.findAllById(Long.valueOf(search));
            case ",email":
                return userRepo.findAllByEmailContaining(search);
            case ",username":
                return userRepo.findAllByUsernameContaining(search);
            default:
                return null;
        }
    }

    public List<User> findUserByNameAndRole(Role role,String name){
        return userRepo.findAllByRolesAndUsernameContaining(role,name);
    }

    public List<User> findUserByRole(Role role){
        return userRepo.findAllByRoles(role);
    }

    public void subs(User userWhoSub, User author){
        Subs subs=new Subs(userWhoSub.getId(),author.getId());

        if(subsRepo.findByIdWhoSubAndIdAuthor(userWhoSub.getId(),author.getId())==null && !author.getId().equals(userWhoSub.getId())) {
            subsRepo.save(subs);
            ArrayList<Subs> subsList = subsRepo.findAllByIdWhoSub(userWhoSub.getId());
            userWhoSub.getUserPersonal().setUserSubs(subsList);
            userPersonalRepo.save(userWhoSub.getUserPersonal());
        }
        //userRepo.save(userWhoSub);
    }

    public void delSub(String idAuthor,User user1){

        Optional<Subs> subs=subsRepo.findByIdAuthorAndIdWhoSub(Long.parseLong(idAuthor),user1.getId());
        Optional<UserPersonal> userPersonal=userPersonalRepo.findById(user1.getUserPersonal().getId());

        List<Subs> subsList=userPersonal.get().getUserSubs();
        for(Subs subs1:subsList){
            if(subs1.equals(subs.get()))
            {
                subsList.remove(subs1);
                break;
            }
        }
        userPersonal.get().setUserSubs(subsList);
        userPersonalRepo.save(userPersonal.get());
        subsRepo.delete(subs.get());
    }

    public List<User> findSubs(Long userId){
        ArrayList<Subs> subsList=subsRepo.findAllByIdWhoSub(userId);

        ArrayList<User> userArrayList=new ArrayList<>();
        for(Subs subs:subsList){
            userArrayList.add(userRepo.findById(subs.getIdAuthor()).get());
        }

        return userArrayList;
    }

    public void savePersonalInfo(String statusLife, User user, String name, String aboutUser, Date date, String gender, String surname, String vk, String inst, String face, String skype, String nameFile, String nameFileBg){
        user.getUserPersonal().setStatusLife(statusLife);
        user.getUserPersonal().setName(name);
        user.getUserPersonal().setAboutUser(aboutUser);
        user.getUserPersonal().setDateBirsday(date);
        user.getUserPersonal().setGender(gender);
        user.getUserPersonal().setSurname(surname);
        user.getUserPersonal().setVkHref(vk);
        user.getUserPersonal().setInstagramHref(inst);
        user.getUserPersonal().setFacebookHref(face);
        user.getUserPersonal().setSkypeHref(skype);
        if(nameFile!=null)
        user.setUserImg(nameFile);
        if(nameFileBg!=null)
        user.setUserFontImg(nameFileBg);
        userPersonalRepo.save(user.getUserPersonal());
        userRepo.save(user);
    }

    public List<Friends> findAllFrieds(User user){
        return friendsRepo.findAllByWhoSubFriend(user);
    }

    public void addFried(User sub,User whoSub){
        if(friendsRepo.findByAuthorFriendAndWhoSubFriend(sub,whoSub)==null) {
            Friends friends = new Friends(whoSub, sub);
            friendsRepo.save(friends);
        }
    }
    public void deleteFried(User sub,User whoSub){
        friendsRepo.delete(friendsRepo.findByAuthorFriendAndWhoSubFriend(sub,whoSub));
    }
    public void byVip(User user){
        user.setVip(true);

        userRepo.save(user);
    }

}