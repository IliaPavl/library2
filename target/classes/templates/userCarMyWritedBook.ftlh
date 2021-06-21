<#import "parts/common.ftlh" as c>
<#import "parts/cab.ftlh" as cab>
<@c.page>
    <@cab.cad>
        <div class="card mt-2">
            <div class="card-head mx-auto">
                <h3 class="textS row mt-3">Мои произведения</h3>
            </div>

            <#list books as book>

                <div class="card">
                    <form method="post" action="/user/userCarMyWritedBook/update" enctype="multipart/form-data">
                        <input type="hidden" value="${book.id!''}" name="idBook">
                        <div class="row ml-2">

                            <div class="col-sm-3">
                                <a href="/book/bookPage/${book.id}" class="card-text text-dark">
                                    <img src="/img/${book.imgBook}" width="200px" height="300px" class="card-img-top mt-3" alt="ну тут картинка">
                                </a>
                                <div class="form-group mt-2">
                                    <div class="custom-file">
                                        <input type="file" name="file" id="customFile${book.id}">
                                        <label class="custom-file-label" for="customFile${book.id}">Выберите картинку</label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-9" >
                                <a href="/book/bookPage/${book.id}" class="card-text text-dark"> Страница книги</a>
                                <input type="text " class="form-control" name="nameBook" value="${book.nameBook!''}">

                                <select class="custom-select" id="inputGroupSelect01" name="ageRate">
                                    <option selected value="${book.ageRate.id!''}">${book.ageRate.ageRate!''}</option>
                                    <#list ageRate as ageR>
                                        <option value=${ageR.id!''}>${ageR.ageRate!''}</option>
                                    </#list>
                                </select>

                                <br><a href="/user/profile/${book.author.id}" class="text-muted">${book.author.username}</a>
                                <br>

                                <#list book.genres as genre>
                                        <div class="input-group mb-3 ">
                                            <input type="submit" class="astext " value="${genre.nameGenre!''}">
                                            <div class="input-group-append">
                                                <button class="btn btn-outline-danger" name="idGenreDel" value="${genre.idGenre!''}" type="submit" id="button-addon2">-</button>
                                            </div>
                                        </div>
                                </#list>
                                <br>
                                <div class="input-group mb-3">

                                    <select class="custom-select" id="inputGroupSelect01" name="idGenreAdd">
                                        <option value="0">Любой жанр</option>
                                        <#list genres as genre>
                                            <option value=${genre.idGenre}>${genre.nameGenre}</option>
                                        </#list>
                                    </select>

                                    <div class="input-group-append">
                                        <button class="btn btn-outline-primary" type="submit" id="button-addon2">+</button>
                                    </div>
                                </div>
                                <br>Длинная в знаках: ${book.lenghtBook!''}
                                <br>Статус:
                                <select class="custom-select" id="inputGroupSelect01" name="status">
                                    <option selected value="${book.status.id!''}">${book.status.nameStatus!''}</option>
                                    <#list status as stat>
                                        <option value=${stat.id!''}>${stat.nameStatus!''}</option>
                                    </#list>
                                </select>
                                <br> Просмотров: ${book.views} Лайков: ${book.likes}
                                <br> Рэйтинг : ${book.rating}
                                <div class="row-sm-8 m-2">
                                    <h5>Описание</h5>
                                    <textarea class="form-control" name="about" style="height: 200px">
                                     ${book.about}
                                    </textarea>
                                </div>
                            </div>
                            <div class="card-footer">
                                <div class="row">
                                    <div class="col-sm-2">
                                        <button class="btn btn-success" type="submit">Сохранить</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                    </form>


                    <div class="card-footer">
                        <div class="row">
                            <div class="col">
                                <form method="post" action="/user/userCarMyWritedBook/delete">
                                    <button class="btn btn-danger" type="submit">Удалить</button>
                                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                                    <input type="hidden" value="${book.id!''}" name="idBook">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <button type="submit" class="btn btn-light ">
                    <form method="get" action="/user/userCabUpdatePage">
                        <input type="hidden" name="idBook" value="${book.id}">
                        <button type="submit" type="submit" class="btn btn-light d-flex justify-content-between align-items-center"><h5 class="lead hide">Редактировать страницы</h5></button>
                    </form>
                </button>

            <#else >
                <h2 class="textS lead mx-auto"> У вас нету написанных произведений</h2>
            </#list>

        </div>
    </@cab.cad>
</@c.page>