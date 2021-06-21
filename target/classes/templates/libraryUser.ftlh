<#import "parts/common.ftlh" as c>
<#import "parts/prof.ftlh" as p>


<@c.page>
    <@p.prof>
        <div class="col-sm-3">
            <div class="card">
                <div class="text-center">
                    <h2 class="textS m-4"> Статус</h2>
                </div>
                <div class="btn-group-vertical">
                    <#list typesLibUser as types>
                        <form action="/user/libraryUser/next" class="btn-block" method="get">
                            <input type="hidden" name="idType" value="${types.idType!''}">
                            <button type="submit" class="btn btn-light d-flex justify-content-between align-items-center">
                                <h1 class="lead">${types.nameType!''} </h1>
                                <span class="badge badge-primary badge-pill float-right">${types.howBooks!''}</span>
                            </button>
                        </form>
                    </#list>
                </div>
            </div>
        </div>
        <div class="col-sm-9">
            <div class="card">
                <h3 class="textS m-4"> ${isStattus.nameType!''}</h3>
                <#list biblicBook as book>
                    <div class="card">
                        <div class="row ml-2">
                            <div class="col-sm-3">
                                <a href="/book/bookPage/${book.id}" class="card-text text-dark">
                                    <img src="/img/${book.imgBook!''}" width="200px" height="300px" class="card-img-top mt-3" alt="ну тут картинка">
                                </a>
                            </div>
                            <div class="col-sm-9" >
                                <a href="/book/bookPage/${book.id}" class="card-text text-dark"> <strong style="font-size: 20pt">${book.nameBook!''}</strong>  <strong style="font-size: 20pt">${book.ageRate.ageRate!''}</strong></a>
                                <br><a href="/user/profile/${book.author.id}" class="text-muted">${book.author.username!''}</a>
                                <div class="row ml-1">
                                    <#list book.genres as genres>
                                        <form method="post" action="/book/searchBooks" class="ml-2">
                                            <input type="hidden" name="idGenre" value="${genres.idGenre!''}">
                                            <input type="hidden" name="idStatus" value="0">
                                            <input type="hidden" name="idAgeRate" value="0">
                                            <input type="hidden" name="allTypes" value="0">
                                            <input type="submit" class="astext " value="${genres.nameGenre!''}">
                                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                                        </form>
                                    </#list>
                                </div>
                                <br>Длинная в знаках: ${book.lenghtBook!''}
                                <br>Статус: ${book.status.nameStatus!''}
                                <br> Просмотров: ${book.views!''} Лайков: ${book.likes!''}
                                <br> Рэйтинг : ${book.rating!''}
                                <div class="row-sm-8 m-2">
                                    <h5>Описание</h5>
                                    ${book.about!''}
                                </div>
                            </div>
                        </div>
                        <div class="crad-footer">
                            <div class="row ">
                                <form action="/user/libraryUser/move" method="post">
                                    <div class="col-sm mt-2 md-2">
                                        <div class="input-group">
                                            <select class="custom-select" id="inputGroupSelect04" name="idType">
                                                <option selected value="${isStattus.id}">${isStattus.nameType!''}</option>
                                                <#list typesLibUser as type>
                                                    <option value="${type.idType!''}">${type.nameType!''}</option>
                                                </#list>
                                            </select>
                                            <div class="input-group-append">
                                                <button class="btn btn-outline-primary" type="submit">Переместить</button>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" name="idBook" value="${book.id}">
                                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                                </form>
                                <div class="col-sm mt-2 md-2">
                                    <form action="/user/libraryUser/del" method="post">
                                        <input type="hidden" name="idType" value="${isStattus.id}">
                                        <input type="hidden" name="idBook" value="${book.id}">
                                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                                        <button class="btn btn-primary" type="submit">удалить из сборника</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                <#else >
                    Ни одной книги тута нету
                </#list>

            </div>
        </div>
    </@p.prof>
</@c.page>