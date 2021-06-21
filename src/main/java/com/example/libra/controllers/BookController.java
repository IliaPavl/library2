package com.example.libra.controllers;


import com.example.libra.domain.*;
import com.example.libra.reposit.BookRepo;
import com.example.libra.servise.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    private final BookRepo bookRepo;

    private final UserSevice userSevice;

    private final GenreServise genreServise;

    private final StatusServise statusServise;

    private final AgeRateServise ageRateServise;

    private final LibUserService libUserService;

    private final CometsServise cometsServise;

    public BookController(BookService bookService, BookRepo bookRepo, UserSevice userSevice, GenreServise genreServise, StatusServise statusServise, AgeRateServise ageRateServise, LibUserService libUserService, CometsServise cometsServise) {
        this.bookService = bookService;
        this.bookRepo = bookRepo;
        this.userSevice = userSevice;
        this.genreServise = genreServise;
        this.statusServise = statusServise;
        this.ageRateServise = ageRateServise;
        this.libUserService = libUserService;
        this.cometsServise = cometsServise;
    }

    @GetMapping
    public String bookList(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("user", user);
        return "main";
    }

    @PostMapping("search")
    private String doSearch(
            @RequestParam(required = false, defaultValue = "") String textSearch,
            @RequestParam String typeSearch, Model model
    ){
        model.addAttribute("searchValue",textSearch);
        model.addAttribute("typeSearch",typeSearch);

        return "search";
    }

    @GetMapping("search")
    public String search(
            @RequestParam(required = false, defaultValue = "") String textSearch,
            @AuthenticationPrincipal User user,
            @RequestParam String typeSearch,
            Model model
    ){
        if (textSearch != null && !textSearch.isEmpty()) {
            model.addAttribute("books",bookService.findByNamebook(textSearch));
            model.addAttribute("authors",userSevice.findUserByNameAndRole(Role.USER,textSearch));
        } else {
            model.addAttribute("books",bookService.findAllBook());
            model.addAttribute("authors",userSevice.findUserByRole(Role.USER));
        }
        model.addAttribute("userId", user.getId());
        model.addAttribute("searchValue",textSearch);
        model.addAttribute("typeSearch",typeSearch);

        return "search";
    }

    @GetMapping("/ordersUser")
    public String searchOrders(
            @RequestParam(required = false, defaultValue = "") String textSearch,
            @AuthenticationPrincipal User user,@RequestParam String typeSearch,
            Model model){


        if (textSearch != null && !textSearch.isEmpty()) {
            model.addAttribute("books",bookService.findByNamebook(textSearch));
            model.addAttribute("authors",userSevice.findUserByNameAndRole(Role.USER,textSearch));
        } else {
            model.addAttribute("books",bookService.findAllBook());
            model.addAttribute("authors",userSevice.findUserByRole(Role.USER));
        }
        model.addAttribute("userId", user.getId());
        model.addAttribute("searchValue",textSearch);
        model.addAttribute("typeSearch",typeSearch);

        return "search";
    }


    @GetMapping("/searchAuthor")
    public String searchAuthor(
            @RequestParam(required = false, defaultValue = "") String textSearch,
            @RequestParam String typeSearch,
            Model model
    ) {
        model.addAttribute("searchValue",textSearch);
        model.addAttribute("typeSearch",typeSearch);


        if (textSearch != null && !textSearch.isEmpty()) {
            model.addAttribute("books",  new ArrayList<Book>());
            model.addAttribute("authors",userSevice.findUserByNameAndRole(Role.AUTHOR,textSearch));
        } else {
            model.addAttribute("books",  new ArrayList<Book>());
            if(!typeSearch.equals("Пользователи"))
                model.addAttribute("authors",userSevice.findUserByRole(Role.AUTHOR));
            else
                model.addAttribute("authors",userSevice.findUserByRole(Role.USER));
        }
        return "searchAuthor";
    }

    @PostMapping("/searchAuthor")
    public String searchByAuthirs(){
        return "searchAuthor";
    }

    //Страница книги ________________________________________________________________________

    @GetMapping("bookPage/{id}")
    public String redactPagesBookTrue(
            Model model,
            @AuthenticationPrincipal User user,
            @PathVariable String id
//            ,
//            @RequestParam(required = false, defaultValue = "") String like,
//            @RequestParam(required = false, defaultValue = "") String mark
    ) {

        Book book=bookService.findById(Long.parseLong(id));
        model.addAttribute("book",book);
        model.addAttribute("author",book.getAuthor());
        List<TypesLibUser> typesLibUsers=libUserService.findAllTypes();
        model.addAttribute("typesLib",typesLibUsers);
        model.addAttribute("selectType",typesLibUsers.get(0));
        model.addAttribute("comentsToBook",cometsServise.findAllComentTiBook(id));
        model.addAttribute("isLike",cometsServise.findIsLikeBook(user,id));
        model.addAttribute("myMark",cometsServise.getMark(user,Long.parseLong(id)));
        model.addAttribute("recenz",cometsServise.listRecenz(Long.parseLong(id)));
        return "bookPage";
    }

    @PostMapping("bookPage")
    public String updatePagesBookTrue(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam String id,
            @RequestParam(required = false, defaultValue = "") String isDo,
            @RequestParam(required = false, defaultValue = "") String like,
            @RequestParam(required = false, defaultValue = "") String mark
    ) {

        if(!user.getId().equals(bookRepo.findById(Long.parseLong(id)).get().getAuthor().getId())) {
            if (isDo.equals("1"))
                cometsServise.addLikeToBook(user, id, Boolean.parseBoolean(like));
            else
                cometsServise.addMark(user, Long.parseLong(id), Integer.parseInt(mark));
        }
        Book book=bookService.findById(Long.parseLong(id));
        model.addAttribute("book",book);
        model.addAttribute("author",book.getAuthor());
        List<TypesLibUser> typesLibUsers=libUserService.findAllTypes();
        model.addAttribute("typesLib",typesLibUsers);
        model.addAttribute("selectType",typesLibUsers.get(0));
        model.addAttribute("comentsToBook",cometsServise.findAllComentTiBook(id));
        model.addAttribute("isLike",cometsServise.findIsLikeBook(user,id));
        model.addAttribute("myMark",cometsServise.getMark(user,Long.parseLong(id)));
        model.addAttribute("recenz",cometsServise.listRecenz(Long.parseLong(id)));
        return "bookPage";
    }

    //Поиск книг_____________________________________________________________
    @GetMapping("searchBooks")
    public String searchBooks(
            @RequestParam(required = false, defaultValue = "") String textSearch,
            @RequestParam(required = false, defaultValue = "") String idGenre,
            @RequestParam(required = false, defaultValue = "") String idStatus,
            @RequestParam(required = false, defaultValue = "") String idAgeRate,
//            @RequestParam(required = false, defaultValue = "") String allTypes,
            @RequestParam(required = false, defaultValue = "") String typeSearch,
            Model model
    ) {
        model.addAttribute("searchValue",textSearch);
        model.addAttribute("typeSearch",typeSearch);

        if (textSearch != null && !textSearch.isEmpty()) {
            model.addAttribute("authors",new ArrayList<User>());
            model.addAttribute("books",bookService.findByNamebook(textSearch));
        } else {
            model.addAttribute("authors",new ArrayList<User>());
            model.addAttribute("books",bookService.findAllBook());
        }
        model.addAttribute("idGenre",genreServise.findByIdGenre(idGenre));
        model.addAttribute("idStatus",statusServise.findByIdStatus(idStatus));
        model.addAttribute("idAgeRate",ageRateServise.findByIdAgeRate(idAgeRate));
        model.addAttribute("genres",genreServise.findAll());
        model.addAttribute("status",bookService.findAllStatus());
        model.addAttribute("ageRate",bookService.findAllAgeRate());
        return "searchBooks";
    }

    @PostMapping("searchBooks")
    public String searchByBooks(
            @RequestParam(required = false, defaultValue = "") String textSearch,
            @RequestParam Long idGenre,
            @RequestParam Long idStatus,
            @RequestParam Long idAgeRate,
            @RequestParam int allTypes,
            Model model
    ){

        ArrayList<Book> books;

        if(idGenre==0){
            if(idStatus==0){
                if(idAgeRate==0){
                    books=bookRepo.findAll();
                }
                else {
                    books=bookRepo.findAllByAgeRate(bookService.findByAgeRate(idAgeRate).get(0));
                }
            }
            else {
                if(idAgeRate==0){
                    books=bookRepo.findAllByStatus(bookService.findByStatus(idStatus).get(0));
                }
                else {
                    books=bookRepo.findAllByAgeRateAndStatus(bookService.findByAgeRate(idAgeRate).get(0),bookService.findByStatus(idStatus).get(0));
                }
            }
        }else {
            if(idStatus==0){
                if(idAgeRate==0){
                    books=bookRepo.findAllByGenres(bookService.findByGenre(idGenre).get(0));
                }
                else {
                    books=bookRepo.findAllByAgeRateAndGenres(bookService.findByAgeRate(idAgeRate).get(0), bookService.findByGenre(idGenre).get(0));
                }
            }
            else {
                if(idAgeRate==0){
                    books=bookRepo.findAllByStatusAndGenres(bookService.findByStatus(idStatus).get(0), bookService.findByGenre(idGenre).get(0));
                }
                else {
                    books=bookRepo.findAllByStatusAndGenresAndAgeRate(bookService.findByStatus(idStatus).get(0), bookService.findByGenre(idGenre).get(0), bookService.findByAgeRate(idAgeRate).get(0));
                }
            }
        }

        books=bookService.sort(books,allTypes);
        books=bookService.vipSort(books);

        model.addAttribute("books",books);
        model.addAttribute("searchValue",textSearch);
        model.addAttribute("typeSearch","Произведения");
        model.addAttribute("authors",new ArrayList<User>());

        model.addAttribute("idGenre",genreServise.findByIdGenre(idGenre.toString()));
        model.addAttribute("idStatus",statusServise.findByIdStatus(idStatus.toString()));
        model.addAttribute("idAgeRate",ageRateServise.findByIdAgeRate(idAgeRate.toString()));

        model.addAttribute("genres",genreServise.findAll());
        model.addAttribute("status",bookService.findAllStatus());
        model.addAttribute("ageRate",bookService.findAllAgeRate());

        return "searchBooks";
    }


    @GetMapping("pageRead/{id}")
    public String readPage(
            Model model,
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ){
        boolean prev=true;
        List<PageInfo> pageInfo=bookService.findAllInfoPages(id);
        bookService.addSee(id);
        PageInfo buffPage=new PageInfo();
        model.addAttribute("pagesInfo",pageInfo);
        if(pageInfo.size()!=0)
        model.addAttribute("setPage",pageInfo.get(0));
        else
            model.addAttribute("setPage",buffPage);
        if(pageInfo.size()<=1) {
            model.addAttribute("nextPage", buffPage);
        }
        else {
            model.addAttribute("nextPage", pageInfo.get(1));
        }
        if(pageInfo.size()==1){
            prev=false;
        }
        model.addAttribute("isNext",false);
        model.addAttribute("isPrev",prev);
        model.addAttribute("idBook",id);
        model.addAttribute("previousPage",buffPage);
        model.addAttribute("userProperty",bookService.findUserPropert(user));
        model.addAttribute("text",bookService.findPageByIdBook(id));
        if(pageInfo.size()!=0)
        model.addAttribute("comentsToBook",cometsServise.findAllComentToPage(pageInfo.get(0).getId()));
        else
            model.addAttribute("comentsToBook",cometsServise.findAllComentToPage((long)0));
        return "pageRead";
    }

    @PostMapping("pageRead")
    public String readPageNext(
            Model model,
            @RequestParam Long idPage,
            @AuthenticationPrincipal User user,
            @RequestParam Long idBook,
            @RequestParam(required = false, defaultValue = "") String bg,
            @RequestParam(required = false, defaultValue = "") String ct,
            @RequestParam(required = false, defaultValue = "") String st,
            @RequestParam(required = false, defaultValue = "") String message
    ){

        bookService.savePropertyPage(user,bg,ct,st);
        cometsServise.saveMessageToPage(user,idPage,message,idBook);

        List<PageInfo> pageInfo=bookService.findAllInfoPages(idBook);
        PageInfo buffPage=new PageInfo();
        model.addAttribute("pagesInfo",pageInfo);
        boolean next=false,prev=false;

        int size=pageInfo.size(),i=0;
        for(PageInfo pageInfo1:pageInfo){
            if(pageInfo1.getId().equals(idPage)){
                model.addAttribute("setPage",bookService.findPageById(idPage));
                if(size==1){
                    model.addAttribute("nextPage", buffPage);
                    model.addAttribute("previousPage",buffPage);
                    break;
                }
                else if(i==0){
                    prev=true;
                    model.addAttribute("nextPage", bookService.findPageById(pageInfo.get(i+1).getId()));
                    model.addAttribute("previousPage",buffPage);
                    break;
                }else {
                    if(i+1<size){
                        model.addAttribute("nextPage", bookService.findPageById(pageInfo.get(i+1).getId()));
                        next=true;
                        if(i==0){
                            model.addAttribute("previousPage",buffPage);
                        }else {
                            prev=true;
                            model.addAttribute("previousPage",bookService.findPageById(pageInfo.get(i-1).getId()));
                        }
                        break;
                    }else{
                        model.addAttribute("nextPage", buffPage);
                        next=true;
                        prev=false;
                        model.addAttribute("previousPage",bookService.findPageById(pageInfo.get(i-1).getId()));
                        break;
                    }
                }
            }
            i++;
        }
        model.addAttribute("isNext",next);
        model.addAttribute("isPrev",prev);
        model.addAttribute("idBook",idBook);
        model.addAttribute("setPage",bookService.findPageById(idPage));
        model.addAttribute("userProperty",bookService.findUserPropert(user));
        model.addAttribute("text",bookService.findByIdPage(idPage));
        model.addAttribute("comentsToBook",cometsServise.findAllComentToPage(idPage));
        return "pageRead";
    }
}
