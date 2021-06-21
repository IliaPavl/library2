package com.example.libra.controllers;

import com.example.libra.domain.*;
import com.example.libra.reposit.ComentToUserRepo;
import com.example.libra.servise.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")

public class UserController extends FileService {
    @Autowired
    private UserSevice userSevice;

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreServise genreServise;

    @Autowired
    private CometsServise cometsServise;

    @Autowired
    private LibUserService libUserService;

    //Список пользователей_____________________________________________________________________________

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userSevice.saveUser(user, username,email, form);

        return "redirect:/user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("delete")
    public String delPr0file(
            @RequestParam("userId") User user,
            Model model
    ) {
        userSevice.deleteProfile(user);
        model.addAttribute("users", userSevice.findAll());

        return "/userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("vip")
    public String vipControl(
            @RequestParam("userId") User user,
            Model model
    ){
        userSevice.vipControl(user);
        model.addAttribute("users", userSevice.findAll());

        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("activ")
    public String activControl(
            @RequestParam("userId") User user,
            Model model
    ){
        userSevice.activControl(user);
        model.addAttribute("users", userSevice.findAll());

        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userSearch(
            @RequestParam(required = false, defaultValue = "") String searchBy,
            @RequestParam(required = false, defaultValue = "") String searchLine,
            Model model
    ){

        if (searchLine != null && !searchLine.isEmpty()) {
            model.addAttribute("users", userSevice.findBy(searchBy, searchLine));
        }else {
            model.addAttribute("users", userSevice.findAll());
        }
        model.addAttribute("searchLine",searchLine);
        model.addAttribute("searchBy",searchBy);

        return "userList";
    }

    //Профиль пользователя_____________________________________________________________________________

    @GetMapping("profile/{user}")
    public String getProfile(Model model, @AuthenticationPrincipal User author,@PathVariable User user) {
        boolean isUser=false;
        model.addAttribute("isUser",isUser);
        model.addAttribute("user2", author);
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("user",user);
        model.addAttribute("authors",userSevice.findSubs(user.getId()));
        model.addAttribute("statusLife",user.getUserPersonal().getStatusLife());
        model.addAttribute("profileBook",cometsServise.findMessageForUserProf(user));

        return "profile";
    }

    @PostMapping("profile")
    public String setMessagesProfile(Model model, @AuthenticationPrincipal User author,@RequestParam String userid,@RequestParam(required = false, defaultValue = "") String text) {
        boolean isUser=false;
        model.addAttribute("isUser",isUser);
        model.addAttribute("user2", author);
        User user=userSevice.findById(Long.parseLong(userid));
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("user",user);
        model.addAttribute("authors",userSevice.findSubs(user.getId()));
        model.addAttribute("statusLife",user.getUserPersonal().getStatusLife());
        if(!text.isEmpty())
        cometsServise.addMessageForUserProf(user,author,text);
        model.addAttribute("profileBook",cometsServise.findMessageForUserProf(user));
        return "profile";
    }

    @GetMapping("sub/{user}")
    public String subProfile(Model model, @AuthenticationPrincipal User author,@PathVariable User user) {
        boolean isUser=false;
        userSevice.subs(author,user);
        model.addAttribute("isUser",isUser);
        model.addAttribute("user2", author);
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("user",user);
        model.addAttribute("authors",userSevice.findSubs(user.getId()));
        model.addAttribute("statusLife",user.getUserPersonal().getStatusLife());
        model.addAttribute("profileBook",cometsServise.findMessageForUserProf(user));
        return "profile";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        boolean isUser=true;
        model.addAttribute("isUser",isUser);
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("user",user);
        model.addAttribute("statusLife",user.getUserPersonal().getStatusLife());
        model.addAttribute("authors",userSevice.findSubs(user.getId()));
        model.addAttribute("about",user.getUserPersonal().getAboutUser());
        model.addAttribute("biblicBook",null);
        model.addAttribute("profileBook",cometsServise.findMessageForUserProf(user));
        return "profile";
    }


    //Библиотека пользователя_____________________________________________________________________________

    @GetMapping("libraryUser")
    public String getLibraUser(Model model, @AuthenticationPrincipal User user,@RequestParam(required = false, defaultValue = "") Long idType) {
        boolean isUser=true;
        model.addAttribute("isUser",isUser);
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("user",user);
        model.addAttribute("statusLife",user.getUserPersonal().getStatusLife());
        model.addAttribute("authors",userSevice.findSubs(user.getId()));
        model.addAttribute("about",user.getUserPersonal().getAboutUser());
        model.addAttribute("biblicBook",null);
        model.addAttribute("profileBook",cometsServise.findMessageForUserProf(user));
        model.addAttribute("typesLibUser",libUserService.getTypesAndBooksLendht(user.getId()));
        idType=(long)1;
        model.addAttribute("biblicBook",libUserService.getBooksByType(user.getId(),idType));
        model.addAttribute("isStattus",libUserService.getNameTypeLib(idType));
        model.addAttribute("typesLibUser",libUserService.getTypesAndBooksLendht(user.getId()));
        return "libraryUser";
    }

    @GetMapping("libraryUser/addBook")
    public String setLibraUser(Model model, @AuthenticationPrincipal User user,@RequestParam String idBook,@RequestParam Long idType) {
        boolean isUser=true;
        libUserService.addBook(idBook,user,idType,(long) 0);
        model.addAttribute("isUser",isUser);
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("user",user);
        model.addAttribute("statusLife",user.getUserPersonal().getStatusLife());
        model.addAttribute("authors",userSevice.findSubs(user.getId()));
        model.addAttribute("about",user.getUserPersonal().getAboutUser());
        model.addAttribute("biblicBook",libUserService.getBooksByType(user.getId(),idType));
        model.addAttribute("profileBook",cometsServise.findMessageForUserProf(user));
        model.addAttribute("typesLibUser",libUserService.getTypesAndBooksLendht(user.getId()));
        model.addAttribute("isStattus",libUserService.getNameTypeLib(idType));
        return "libraryUser";
    }

    @GetMapping("libraryUser/next")
    public String libraUserNext(Model model, @AuthenticationPrincipal User user,@RequestParam String idType) {
        boolean isUser=true;
        model.addAttribute("isUser",isUser);
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("user",user);
        model.addAttribute("statusLife",user.getUserPersonal().getStatusLife());
        model.addAttribute("authors",userSevice.findSubs(user.getId()));
        model.addAttribute("about",user.getUserPersonal().getAboutUser());
        model.addAttribute("biblicBook",libUserService.getBooksByType(user.getId(),Long.parseLong(idType) ));
        model.addAttribute("profileBook",cometsServise.findMessageForUserProf(user));
        model.addAttribute("typesLibUser",libUserService.getTypesAndBooksLendht(user.getId()));
        model.addAttribute("biblicBook",libUserService.getBooksByType(user.getId(),Long.parseLong(idType)));
        model.addAttribute("isStattus",libUserService.getNameTypeLib(Long.parseLong(idType)));
        return "libraryUser";
    }

    @PostMapping("libraryUser/del")
    public String libraUserDel(Model model, @AuthenticationPrincipal User user,@RequestParam String idType,@RequestParam Long idBook) {
        boolean isUser=true;
        model.addAttribute("isUser",isUser);
        libUserService.deliteBook(idBook,user);
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("user",user);
        model.addAttribute("statusLife",user.getUserPersonal().getStatusLife());
        model.addAttribute("authors",userSevice.findSubs(user.getId()));
        model.addAttribute("about",user.getUserPersonal().getAboutUser());
        model.addAttribute("biblicBook",libUserService.getBooksByType(user.getId(),Long.parseLong(idType) ));
        model.addAttribute("profileBook",cometsServise.findMessageForUserProf(user));
        model.addAttribute("typesLibUser",libUserService.getTypesAndBooksLendht(user.getId()));
        model.addAttribute("biblicBook",libUserService.getBooksByType(user.getId(),Long.parseLong(idType)));
        model.addAttribute("isStattus",libUserService.getNameTypeLib(Long.parseLong(idType)));
        return "libraryUser";
    }

    @PostMapping("libraryUser/move")
    public String libraUserUpdate(Model model, @AuthenticationPrincipal User user,@RequestParam Long idType,@RequestParam Long idBook) {
        boolean isUser=true;
        libUserService.moveBook(idBook,idType,user);
        model.addAttribute("isUser",isUser);
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("user",user);
        model.addAttribute("statusLife",user.getUserPersonal().getStatusLife());
        model.addAttribute("authors",userSevice.findSubs(user.getId()));
        model.addAttribute("about",user.getUserPersonal().getAboutUser());
        model.addAttribute("biblicBook",libUserService.getBooksByType(user.getId(),idType));
        model.addAttribute("profileBook",cometsServise.findMessageForUserProf(user));
        model.addAttribute("typesLibUser",libUserService.getTypesAndBooksLendht(user.getId()));
        model.addAttribute("biblicBook",libUserService.getBooksByType(user.getId(),idType));
        model.addAttribute("isStattus",libUserService.getNameTypeLib(idType));
        return "libraryUser";
    }


    //Редактировние профиля_____________________________________________________________________________

    @GetMapping("profileEdit")
    public String getProfileEdit(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("filename",user.getUserImg());

        return "profileEdit";
    }

    @PostMapping("profileEdit")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "") String password,
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        userSevice.updateProfile(user, password, email,saveFile(file,2));

        return "redirect:/user/profile";
    }
    //Кабинет пользователя________________________________________________________________________
    //VIP________________________________________________________________________

    @GetMapping("byVip")
    public String vipUser(Model model, @AuthenticationPrincipal User user){

        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("user", user);
        return "byVip";
    }

    @PostMapping("byVip")
    public String byVipUser(Model model, @AuthenticationPrincipal User user,@RequestParam String cardInfo){
        userSevice.byVip(user);
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("user", user);
        return "byVip";
    }
    //Поддержка пользователя________________________________________________________________________
    @GetMapping("userCabSupport")
    public String getSup(Model model, @AuthenticationPrincipal User user){

        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("supportMessages",cometsServise.findUserMesSupport(user));
        return "userCabSupport";
    }

    @PostMapping("userCabSupport")
    public String setSup(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "")String textMessage
    ){
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        if(textMessage!=null)
        cometsServise.addNewSupport(user,textMessage);
        model.addAttribute("supportMessages",cometsServise.findUserMesSupport(user));
        return "userCabSupport";
    }
    //редактирование жанров_____________________________________________________________
    @GetMapping("genresAdd")
    public String getGenresCab(Model model, @AuthenticationPrincipal User user){

        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("genresList",genreServise.findAll());
        return "genresAdd";
    }
    @PostMapping("genresAdd")
    public String updateGenresCab(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "") String idGenreDel,
            @RequestParam(required = false, defaultValue = "") String nameNewGenre,
            @RequestParam(required = false, defaultValue = "") String idGenreUpdate,
            @RequestParam(required = false, defaultValue = "") String nameGenre
            ){
        genreServise.updateGenre(nameNewGenre,idGenreDel,idGenreUpdate,nameGenre);
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("genresList",genreServise.findAll());
        return "genresAdd";
    }
    //Рецензии пользователя________________________________________________________________________
    @GetMapping("recens")
    public String getRecenzCab(Model model, @AuthenticationPrincipal User user){

        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("listRecens",cometsServise.findAllRecens(user));
        model.addAttribute("user",user);
        return "recens";
    }

    @PostMapping("recens/update")
    public String setRecenzCab(Model model, @AuthenticationPrincipal User user,@RequestParam String idBook){
        cometsServise.delRec(user,bookService.findById(Long.parseLong(idBook)));
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("listRecens",cometsServise.findAllRecens(user));
        model.addAttribute("user",user);
        return "recens";
    }
    @GetMapping("recenzCorrespondence")
    public String getRecenzCorrCab(Model model, @AuthenticationPrincipal User user,@RequestParam String idBook){
        Book book=bookService.findById(Long.parseLong(idBook));
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("book",book);
        model.addAttribute("coment",cometsServise.findRecensByBookAndUser(user,book));
        model.addAttribute("user",user);
        return "recenzCorrespondence";
    }
    @PostMapping("recenzCorrespondence")
    public String setRecenzCorrCab(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam String idBook,
            @RequestParam String coment
    ){
        Book book=bookService.findById(Long.parseLong(idBook));
        cometsServise.saveRec(user,book,coment);
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("book",book);
        model.addAttribute("coment",cometsServise.findRecensByBookAndUser(user,book));
        model.addAttribute("user",user);
        return "recenzCorrespondence";
    }
    //Подписки пользователя________________________________________________________________________
    @GetMapping("userCabSub")
    public String getUserCabSub(Model model, @AuthenticationPrincipal User user){

        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());

        model.addAttribute("authors",userSevice.findSubs(user.getId()));
        return "userCabSub";
    }

    @PostMapping("userCabSub")
    public String setUserCabSub(Model model, @AuthenticationPrincipal User user,@RequestParam String idAuthor){
        userSevice.delSub(idAuthor,user);
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());

        model.addAttribute("authors",userSevice.findSubs(user.getId()));
        return "userCabSub";
    }
    //Переписки пользователя________________________________________________________________________
    @GetMapping("userCabСorrespondence/{user}")
    public String getСorrespondence(Model model, @AuthenticationPrincipal User user1,@PathVariable User user){

        model.addAttribute("filenameUs", user1.getUserImg());
        model.addAttribute("filenamePr", user1.getUserFontImg());
        model.addAttribute("userToMes",user);

        model.addAttribute("messenger", cometsServise.getComentsToUser(user1.getId(),user.getId(),true));
        return "userCabСorrespondence";
    }

    @PostMapping("userCabСorrespondence")
    public String setСorrespondence(
            Model model,
            @AuthenticationPrincipal User user1,
            @RequestParam String userToMes,
            @RequestParam String message){
        cometsServise.addComentToSuer(user1.getId(),Long.parseLong(userToMes),user1.getUsername()+" : "+message ,true);

        cometsServise.idReadFalse(user1);

        model.addAttribute("filenameUs", user1.getUserImg());
        model.addAttribute("filenamePr", user1.getUserFontImg());
        User user=userSevice.findById(Long.parseLong(userToMes));
        model.addAttribute("userToMes",user);
        model.addAttribute("messenger", cometsServise.getComentsToUser(user1.getId(),user.getId(),true));
        return "userCabСorrespondence";
    }

    @GetMapping("messagesForUser")
    public String getMessageForUser(Model model, @AuthenticationPrincipal User user1){

        model.addAttribute("filenameUs", user1.getUserImg());
        model.addAttribute("filenamePr", user1.getUserFontImg());

        model.addAttribute("messenger", cometsServise.findNewMesToUser(user1,true));

        return "messagesForUser";
    }
    //Модерация техподдержки пользователя________________________________________________________________________
    @GetMapping("moderSupport")
    public String getModerSupport(Model model, @AuthenticationPrincipal User user1){

        model.addAttribute("filenameUs", user1.getUserImg());
        model.addAttribute("filenamePr", user1.getUserFontImg());

        model.addAttribute("messengerNew", cometsServise.findMesSupport(true));
        model.addAttribute("messengerReady", cometsServise.findMesSupport(false));

        return "moderSupport";
    }

    @GetMapping("moderSupporCorrespondence/{id}")
    public String getModerSupportOtvet(Model model, @AuthenticationPrincipal User user1,@PathVariable(required = false) Long id){

        model.addAttribute("filenameUs", user1.getUserImg());
        model.addAttribute("filenamePr", user1.getUserFontImg());

        model.addAttribute("supportMessages",cometsServise.findUserMesSupportById(id));
        return "moderSupporCorrespondence";
    }


    @PostMapping("moderSupporCorrespondence")
    public String setUserSupportOtvet(Model model, @AuthenticationPrincipal User user1,@RequestParam String textMessage,@RequestParam Long idMes){

        model.addAttribute("filenameUs", user1.getUserImg());
        model.addAttribute("filenamePr", user1.getUserFontImg());
        cometsServise.saveUpdate(textMessage,idMes);
        model.addAttribute("messengerNew", cometsServise.findMesSupport(true));
        model.addAttribute("messengerReady", cometsServise.findMesSupport(false));
        return "moderSupport";
    }

    @PostMapping("userAddSupport")
    public String setUserSupport(Model model, @AuthenticationPrincipal User user1,@RequestParam String textMessage){

        model.addAttribute("filenameUs", user1.getUserImg());
        model.addAttribute("filenamePr", user1.getUserFontImg());
        cometsServise.addNewSupport(user1,textMessage   );
        model.addAttribute("supportMessages",cometsServise.findUserMesSupport(user1));
        return "moderSupport";
    }
    //Друзья пользователя________________________________________________________________________
    @GetMapping("addFriend/{user}")
    public String addFriend(Model model, @AuthenticationPrincipal User author,@PathVariable User user){

        userSevice.addFried(user,author);
        boolean isUser=false;
        model.addAttribute("isUser",isUser);
        model.addAttribute("user2", author);
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("user",user);
        model.addAttribute("authors",userSevice.findSubs(user.getId()));
        model.addAttribute("statusLife",user.getUserPersonal().getStatusLife());
        model.addAttribute("profileBook",cometsServise.findMessageForUserProf(user));

        return "profile";
    }

    @GetMapping("userCabFriends")
    public String ListFriends(Model model, @AuthenticationPrincipal User user1){

        model.addAttribute("filenameUs", user1.getUserImg());
        model.addAttribute("filenamePr", user1.getUserFontImg());
        model.addAttribute("friendsList",userSevice.findAllFrieds(user1));
        model.addAttribute("supportMessages",cometsServise.findUserMesSupport(user1));
        return "userCabFriends";
    }

    @PostMapping("userCabFriends")
    public String getFried(Model model, @AuthenticationPrincipal User user1,@RequestParam User userDel){

        model.addAttribute("filenameUs", user1.getUserImg());
        model.addAttribute("filenamePr", user1.getUserFontImg());
        userSevice.deleteFried(userDel,user1);
        model.addAttribute("friendsList",userSevice.findAllFrieds(user1));
        model.addAttribute("supportMessages",cometsServise.findUserMesSupport(user1));
        return "userCabFriends";
    }

    //Моя страница пользователя________________________________________________________________________
    @GetMapping("UserProfileRedCab")
    public String getUserCab(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("username", user.getUsername());
        if(user.getUserPersonal()==null){
            user.setUserPersonal(new UserPersonal());
        }
        model.addAttribute("userpersonal",user.getUserPersonal());
        model.addAttribute("supportMessages",cometsServise.findUserMesSupport(user));
        return "UserProfileRedCab";
    }


    @PostMapping("UserProfileRedCab")
    public String setUserCab(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String surname,
            @RequestParam(required = false, defaultValue = "") String date,
            @RequestParam(required = false, defaultValue = "") String gender,
            @RequestParam(required = false, defaultValue = "") String aboutUser,
            @RequestParam(required = false, defaultValue = "") String vk,
            @RequestParam(required = false, defaultValue = "") String face,
            @RequestParam(required = false, defaultValue = "") String inst,
            @RequestParam(required = false, defaultValue = "") String skype,
            @RequestParam(required = false, defaultValue = "") String statusLife,
            @RequestParam("fileBG") MultipartFile fileBG,
            @RequestParam("file") MultipartFile file,
            Model model,
            @AuthenticationPrincipal User user
    ) throws IOException, ParseException {

        Date docDate=new Date();

        if(!date.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd-mm-yyyy");
            docDate = format.parse(date);
        }
        java.sql.Date sqlStartDate = new java.sql.Date(docDate.getTime());
        userSevice.savePersonalInfo(statusLife,user,name,aboutUser, sqlStartDate,gender,surname,vk,face,inst,skype,saveFile(file,2),saveFile(fileBG,2));

        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("username", user.getUsername());
        if(user.getUserPersonal()==null){
            user.setUserPersonal(new UserPersonal());
        }
        model.addAttribute("userpersonal",user.getUserPersonal());
        model.addAttribute("supportMessages",cometsServise.findUserMesSupport(user));
        return "UserProfileRedCab";
    }
    //Кабинет Управлениями написанными книгами_____________________________________________________
    @GetMapping("userCarMyWritedBook")
    public String listBook(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        //fix
        List<Book> books=bookService.findAllBooksUserWrite(user);
        List<Book> books1=bookService.fixBook( books);
        //__________________________________
        model.addAttribute("books",books1);


        model.addAttribute("genres",genreServise.findAll());
        model.addAttribute("status",bookService.findAllStatus());
        model.addAttribute("ageRate",bookService.findAllAgeRate());
        return "userCarMyWritedBook";
    }

    @PostMapping("userCarMyWritedBook/delete")
    public String dellBook(Model model, @AuthenticationPrincipal User user,@RequestParam String idBook) {

        bookService.deleteBook(bookService.findById(Long.parseLong(idBook)));
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("genres",genreServise.findAll());
        model.addAttribute("status",bookService.findAllStatus());
        model.addAttribute("ageRate",bookService.findAllAgeRate());
        //fix
        List<Book> books=bookService.findAllBooksUserWrite(user);
        List<Book> books1=bookService.fixBook( books);
        //__________________________________
        model.addAttribute("books",books1);

        return "userCarMyWritedBook";
    }

    @PostMapping("userCarMyWritedBook/update")
    public String updateBook(
            @RequestParam(required = false, defaultValue = "") String nameBook,
            @RequestParam(required = false, defaultValue = "") String about,
            @RequestParam(required = false, defaultValue = "") String ageRate,
            @RequestParam(required = false, defaultValue = "") String status,
            @RequestParam(required = false, defaultValue = "") String idGenreDel,
            @RequestParam(required = false, defaultValue = "") String idGenreAdd,
            @RequestParam("file") MultipartFile file,
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "") String idBook
    ) throws IOException {
        bookService.updateBook(idBook,nameBook,about,ageRate,status,idGenreDel,idGenreAdd,saveFile(file,2));

        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        //fix
        List<Book> books=bookService.findAllBooksUserWrite(user);
        List<Book> books1=bookService.fixBook( books);
        //__________________________________
        model.addAttribute("books",books1);
        model.addAttribute("genres",genreServise.findAll());
        model.addAttribute("status",bookService.findAllStatus());
        model.addAttribute("ageRate",bookService.findAllAgeRate());
        return "userCarMyWritedBook";
    }
    //Добавление книги_____________________________________________________________________________
    @GetMapping("addBook")
    public String getNewBook(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("genres", genreServise.findAll());
        model.addAttribute("user",user);
        model.addAttribute("status",bookService.findAllStatus());
        model.addAttribute("ageRate",bookService.findAllAgeRate());

        return "addBook";
    }

    @PostMapping("addBook")
    public String addBook(
            @RequestParam String nameBook,
            @RequestParam String about,
            @RequestParam String ageRate,
            @RequestParam String status,
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {
        if(file.isEmpty())
        bookService.addBook(nameBook,about,form,ageRate,status,user,saveFile(file,1));
        else
            bookService.addBook(nameBook,about,form,ageRate,status,user,saveFile(file,2));
        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        //fix
        List<Book> books=bookService.findAllBooksUserWrite(user);
        List<Book> books1=bookService.fixBook( books);
        //__________________________________
        model.addAttribute("books",books1);
        model.addAttribute("genres",genreServise.findAll());
        model.addAttribute("status",bookService.findAllStatus());
        model.addAttribute("ageRate",bookService.findAllAgeRate());
        return "userCarMyWritedBook";
    }


    @GetMapping("userCabUpdatePage")
    public String redactPagesBook(Model model, @AuthenticationPrincipal User user,@RequestParam String idBook) {

        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("books",bookService.findById(Long.parseLong(idBook)));
        model.addAttribute("pagesInfo",bookService.findAllInfoPages(Long.parseLong(idBook)));
        model.addAttribute("pageText",bookService.findPageById(0));
        boolean takePage=false;
        model.addAttribute("takePage", takePage);
        return "userCabUpdatePage";
    }

    @GetMapping("userCabUpdatePage/{id}")
    public String redactPagesBookTrue(Model model, @AuthenticationPrincipal User user,@RequestParam String idBook) {

        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("books",bookService.findById(Long.parseLong(idBook)));
        model.addAttribute("pagesInfo",bookService.findAllInfoPages(Long.parseLong(idBook)));
        model.addAttribute("pageText",bookService.findPageById(0));
        boolean takePage=true;
        model.addAttribute("takePage", takePage);
        return "userCabUpdatePage";
    }

    @PostMapping("userCabUpdatePage")
    public String saveRedactPagesBook(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "") String idBook,
            @RequestParam(required = false, defaultValue = "") String idPage,
            @RequestParam(required = false, defaultValue = "") String text,
            @RequestParam(required = false, defaultValue = "") String chapter,
            @RequestParam(required = false, defaultValue = "") String page,
            @RequestParam(required = false, defaultValue = "") String namePage,
            @RequestParam(required = false, defaultValue = "") String save
            ) {

        model.addAttribute("filenameUs", user.getUserImg());
        model.addAttribute("filenamePr", user.getUserFontImg());
        model.addAttribute("books", bookService.findById(Long.parseLong(idBook)));

        if(save.equals("true")) {
            bookService.addPage(text,idBook,chapter,namePage,page,idPage);
            Page page1= bookService.findPageByIdBookAndChapterAndPage(idBook,chapter,page);
            model.addAttribute("pageText", page1);
            model.addAttribute("pageInfo",bookService.findPageInfoById(page1.getId()));
        }else {
            if(Integer.parseInt(idPage)!=0) {
                model.addAttribute("pageText", bookService.findPageById(Long.parseLong(idPage)));
                model.addAttribute("pageInfo",bookService.findPageInfoById(Long.parseLong(idPage)));
            }
            else {
                model.addAttribute("pageText", bookService.findPageById(0));
                model.addAttribute("pageInfo",bookService.findPageInfoById(0));
            }
        }
        model.addAttribute("pagesInfo", bookService.findAllInfoPages(Long.parseLong(idBook)));
        boolean takePage=true;
        model.addAttribute("takePage", takePage);
        return "userCabUpdatePage";
    }

}