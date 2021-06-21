<#macro search>

    <div class="form-group">
        <div class="row">
            <div class="col-sm-9">
                <div class="col">
                    <div class="card mt-2">
                        <div class="card-head m-3">
                            <h1 class="textS"> Результаты поиска :${typeSearch!''}</h1>
                        </div>
                        <div class="card-body mr-3">
                            <form method="get" action="/book/search">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <button type="submit" class="btn btn-light input-group-text" id="basic-addon1"><h1 class="textS lead"> Q</h1></button>
                                    </div>
                                    <input type="text" name="textSearch" class="form-control textA" placeholder="Найти произведение или автора" aria-describedby="basic-addon1" value="${searchValue!''}">
                                </div>
                                <input class="hide btn btn-light t" type="text" name="typeSearch"  value="${typeSearch}">
                                <input type="hidden" value="${_csrf.token}" name="_csrf">
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <#list books as book>
                        <div class="card">
                            <div class="row ml-2">
                                <div class="col-sm-3">
                                    <#if book.author.vip>
                                    <button type="button" class="btn btn-lg btn-block
                                             btn-warning
                                             mb-1 mt-3"
                                            <#if book.author.vip>
                                                id="btn-tooltip" title="Горячая работа"
                                            <#else >
                                            </#if>
                                    >Горячая работа</button></a>
                                    <#else >
                                    </#if>
                                    <a href="/book/bookPage/${book.id}" class="card-text text-dark">
                                        <img src="/img/${book.imgBook!''}" width="200px" height="300px" class="card-img-top mt-3" alt="ну тут картинка">
                                    </a>
                                </div>
                                <div class="col-sm-9" >
                                    <a href="/book/bookPage/${book.id}" class="card-text text-dark"> <strong style="font-size: 20pt">${book.nameBook!''}</strong>  <strong style="font-size: 20pt">${book.ageRate.ageRate!''}</strong></a>
                                    <br><a href="/user/profile/${book.author.id}" class="text-muted">${book.author.username!''}</a>
                                    <div class="row ml-1">
                                        <#list book.genres as genres>
                                            <form method="get" action="/book/searchBooks" class="ml-2">
                                                <input type="hidden" name="idGenre" value="${genres.idGenre!''}">
                                                <input type="submit" class="astext " value="${genres.nameGenre!''}">
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
                        </div>
                    <#else >
                    </#list>

                </div>
                <div class="col">
                    <div class="card">
                        <div class="row ">
                            <#list authors as author>
                                <div class="row ml-2 ">
                                    <div class="card ml-4 mr-4 mt-3 md-3" style="width: 10rem;">
                                        <a href="/user/profile/${author.id!''}" class="text-muted">
                                            <img src="/img/${author.userImg!''}" class="card-img-top imgsearch" alt="ну тут картинка">
                                        </a>
                                        <a href="/user/sub/${author.id!''}" class="text-muted mx-auto"><p >Подписаться</p></a>
                                        <a href="/user/userCabСorrespondence/${author.id!''}" class="text-muted"><p >Написать сообщение</p></a>
                                        <a href="/user/addFriend/${author.id!''}" class="text-muted mx-auto"><p >Добавить в друзья</p></a>
                                        <div class="card-footer">
                                            <a href="/user/profile/${author.id!''}" class="text-muted">${author.username!''}</a>
                                        </div>
                                    </div>
                                </div>
                            <#else >
                            </#list>
                        </div>
                    </div>
                </div>
            </div>


            <div class="col-sm-3">
                <div class="row-sm">
                    <div class="card mt-2">
                        <div class="card-body mr-3">
                            <div class="btn-group-vertical row m-2 btn-block">
                                <form method="get" action="/book/search" class="btn-block mt-2">
                                    <input type="hidden" name="textSearch" class="out" value="${searchValue!''}">
                                    <input class="hide btn btn-light btnRefresh" type="submit" name="typeSearch"  value="Все">
                                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                                </form>
                                <form method="get" action="/book/searchBooks" class="btn-block mt-2">
                                    <input type="hidden" name="textSearch" class="out" value="${searchValue!''}">
                                    <input class="hide btn btn-light btnRefresh" type="submit" name="typeSearch"  value="Произведения">
                                </form>
                                <form method="get" action="/book/searchAuthor" class="btn-block">
                                    <input type="hidden" name="textSearch" class="out" value="${searchValue!''}">
                                    <input class="hide btn btn-light btnRefresh" type="submit" name="typeSearch"  value="Авторы">
                                </form>
                                <form method="get" action="/book/searchAuthor" class="btn-block">
                                    <input type="hidden" name="textSearch" class="out" value="${searchValue!''}">
                                    <input class="hide btn btn-light btnRefresh" type="submit" name="typeSearch"  value="Пользователи">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <#nested >
            </div>
        </div>
    </div>
    <script>
        document.querySelector('.btnRefresh').onclick = refresh;
        function refresh(){
            let firs = document.querySelector('.textA').value;
            let t = document.querySelector('.t').value;
            document.querySelector('.out').innerHTML=firs;
            document.querySelector('.outT').innerHTML=t;
        }
    </script>
</#macro>