<#import "parts/common.ftlh" as c>
<#import "parts/cab.ftlh" as cab>
<@c.page>
    <@cab.cad>
        <div class="card mt-2">
            <div class="card-head mx-auto">
                <h3 class="textS row mt-3">Редактирование книги : ${books.nameBook!''}</h3>
                <h5 class="textS row mt-3 lead">Памятка пользователя:</h5>
                <p>Если не хотите писать с тэга то можете сделать всё в word и конвентировать в html формат </p>
                <p>Примеры сайтов: <a href="https://ru.texthandler.com/text-tools/text-to-html/"> Очень качественный переводчик в html </a></p>
            </div>

            <div class="row">
                <div class="col-sm-5">
                    <form method="post" action="/user/userCabUpdatePage">
                        <h5 class="textS row mt-3 ml-3">Выберите страницу</h5>
                        <div class="input-group mb-3">
                            <select class="custom-select" id="inputGroupSelect01" name="idPage">
                                <option selected value="0">Добавить новую страницу</option>
                                <#list pagesInfo as pInfo>
                                    <option value="${pInfo.id!''}">глава : ${pInfo.numberChapter!''}/ страница : ${pInfo.numberPage!''}/ название : ${pInfo.namePage!''}</option>
                                </#list>
                            </select>
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="submit">Открыть</button>
                            </div>
                        </div>
                        <input type="hidden" name="idBook" value="${books.id!''}">
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                    </form>
                </div>
                <div class="col-sm-2 ">
                    <div class="col-sm">
                        <button type="button" class="btn btn-light d-flex justify-content-between align-items-center btnRefresh vbottom mt-2" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">Отобразить текст на странице</button>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="card col mr-3 ml-3">
                    <#if takePage>
                        <form method="post" action="/user/userCabUpdatePage">
                            <input name="chapter" type="number" value="${pageInfo.numberChapter!''}" class="form-control">часть
                            <input name="page" type="number" value="${pageInfo.numberPage!''}" class="form-control">глава
                            <input name="namePage" type="text" value="${pageInfo.namePage!''}" class="form-control" placeholder="Название главы">
                            <div class="card row">
                                <div class="card-head mx-auto">
                                    <h3 class="textS row mt-3">Текст на странице</h3>
                                </div>
                                <div class="out collapse" id="collapseExample">
                                </div>
                            </div>
                            <div class="card row ">
                                <div class="card-head mx-auto">
                                    <h3 class="textS row mt-3">Текс для редактирования</h3>
                                </div>
                                <textarea class="text-area btn-block textPage" name="text" >
                                ${pageText.textPage!''}
                            </textarea>
                            </div>

                            <div class="card row">
                                <input type="hidden" name="idPage" value="${pageInfo.id!''}">
                                <input type="hidden" name="idBook" value="${books.id!''}">
                                <input type="hidden" value="${_csrf.token}" name="_csrf">
                                <button type="submit" class="btn btn-light d-flex justify-content-between align-items-center  mt-2" name="save" value="true">Сохранить</button>

                            </div>
                        </form>
                    <#else >
                        Страница не выбрана!
                    </#if>
                </div>
            </div>
        </div>
        <script>
            document.querySelector('.btnRefresh').onclick = refresh;
            function refresh(){
                let firs = document.querySelector('.text-area').value;
                document.querySelector('.out').innerHTML=firs;
            }
        </script>
    </@cab.cad>
</@c.page>