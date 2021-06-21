<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="row">

        <div class="col-sm-9">
            <div class="card mt-2">
                <div class="card-head mx-auto">
                    <h3 class="textS row"></h3>
                </div>
                <div class="card">
                    <div class="row ml-2">
                        <div class="col-sm-3 ">
                            <img src="/img/${book.imgBook}" width="200px" height="300px" class="card-img-top mt-3" alt="ну тут картинка">

                            <button type="submit" class="btn mt-2 btn-primary d-flex justify-content-between align-items-center btn-block ">
                                <a href="/book/pageRead/${book.id}" class="link-cab-white hide"><h3 class="lead">Читать</h3></a>
                            </button>
                            <form action="/user/libraryUser/addBook" method="get">
                                <input type="hidden" name="idBook" value=${book.id}>
                                <div class="input-group mt-2">
                                    <select class="custom-select" id="inputGroupSelect04" name="idType">
                                        <option selected value="${selectType.id}">${selectType.nameType!''}</option>
                                        <#list typesLib as type>
                                            <option value="${type.id!''}">${type.nameType!''}</option>
                                        </#list>
                                    </select>
                                    <div class="input-group-append">
                                        <button class="btn btn-outline-primary" type="submit">+</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                        <div class="col-sm-9" >
                            <a href=# class="card-text text-dark"> <strong style="font-size: 20pt">${book.nameBook}</strong>  <strong style="font-size: 20pt">${book.ageRate.ageRate}</strong></a>
                            <br><a href="/user/profile/${book.author.id}" class="text-muted">${book.author.username}</a>
                            <div class="row ml-1">
                                <#list book.genres as genres>
                                    <form method="get" action="/book/searchBooks" class="ml-2">
                                        <input type="hidden" name="idGenre" value="${genres.idGenre!''}">
                                        <input type="submit" class="astext " value="${genres.nameGenre!''}">
                                    </form>
                                </#list>
                            </div>
                            <br>Длинная в знаках: ${book.lenghtBook}
                            <br>Статус: ${book.status.nameStatus}
                            <br> Просмотров: ${book.views} Лайков: ${book.likes}
                            <br> Рэйтинг : ${book.rating}
                            <form method="post" action="/book/bookPage">
                                <div class="row mt-1">
                                    <div class="col-sm-1 mr-2">
                                        <button type="submit" name="isDo" value="1" class="btn
                                        <#if isLike!''>
                                        btn-success
                                        <#else >
                                        btn-outline-success
                                        </#if>">Лайк</button>
                                    </div>
                                    <div class="col-sm-3">

                                        <div class="input-group">
                                            <select class="custom-select " id="inputGroupSelect04" name="mark">
                                                <option selected value="${myMark!''}">${myMark!''}</option>
                                                <option value="5">5</option>
                                                <option value="4">4</option>
                                                <option value="3">3</option>
                                                <option value="2">2</option>
                                                <option value="1">1</option>
                                            </select>
                                            <div class="input-group-append">
                                                <button class="btn btn-outline-secondary" name="idDo" value="2" type="submit">Оценка</button>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <#if isLike!''>
                                    <input type="hidden" value="true" name="like">
                                <#else >
                                    <input type="hidden" value="false" name="like">
                                </#if>
                                <input type="hidden" value="${book.id!''}" name="id">
                                <input type="hidden" value="${_csrf.token}" name="_csrf">
                            </form>
                            <form method="get" action="/user/recenzCorrespondence">
                                <input type="hidden" value="${book.id!''}" name="idBook">
                                <input type="submit" class="btn btn-outline-info" value="Написать рецензию">
                            </form>
                            <div class="row-sm-8 m-2">
                                <h5>Описание</h5>
                                ${book.about}
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card">
                    <button class="btn btn-outline-info" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                        <h2 class="textS row mt-3 ml-5">Рецензии</h2>
                    </button>
                    <div class=collapse" id="collapseExample">
                        <div class="card card-body">
                            <#list recenz as mes>
                                <p class="lead">${mes.author.username!''}</p>
                                ${mes.recenzText!''}
                            </#list>
                        </div>
                    </div>

                </div>
            </div>

            <div class="card">
                <div class="card" style="height: 500px">
                    <h2 class="textS row mt-3 ml-5">коментарии</h2>
                    <div style="overflow: auto;">
                        <#list comentsToBook as mes>
                            <p class="lead">idCom :${mes.id!''}/Глава:${mes.page.namePage!''}/ <strong>${mes.dateCreate!''}</strong> : ${mes.author.username!''}</p>
                            ${mes.coment!''}
                        </#list>
                    </div>
                </div>
            </div>


        </div>
        <div class="col mt-2">

            <div class="card">
                <div class="row ">
                    <div class="row ml-2 ">
                        <div class="card ml-4 mr-4 mt-3 md-3" style="width: 10rem;">
                            <a href="/user/profile/${author.id!''}" class="text-muted">
                                <img src="/img/${author.userImg!''}" class="card-img-top" alt="ну тут картинка">
                            </a>
                            <a href="/user/sub/${author.id!''}" class="text-muted mx-auto"><p >Подписаться</p></a>
                            <a href="/user/userCabСorrespondence/${author.id!''}" class="text-muted"><p >Написать сообщение</p></a>
                            <a href="/user/addFriend/${author.id!''}" class="text-muted mx-auto"><p >Добавить в друзья</p></a>
                            <div class="card-footer">
                                <a href="/user/profile/${author.id!''}" class="text-muted">${author.username!''}</a>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                </div>
            </div>
        </div>
    </div>

</@c.page>