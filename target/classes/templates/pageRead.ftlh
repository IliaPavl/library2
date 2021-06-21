<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Библиотека онлайн</title>
    <link rel="stylesheet" href="/static/style.css">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body id="id1">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
            <a class="nav-link" href="/book/bookPage/${idBook}">Вернуться на сайт <span class="sr-only">(current)</span></a>
        </li>
    </ul>
    <div class="float-right col mt-2">
        <div class="row float-right">
            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                Button with data-target
            </button>
        </div>

    </div>
</nav>

<div class="container">
    <div class="card mt-3">
        <div class="collapse" id="collapseExample">
            <form action="/book/pageRead/" method="post">
                <div class="col">
                    <div class="row">
                        <p>Укажите цвет фона: <input type="color" name="bg"  value="${userProperty.colorBorder!''}"></p>
                    </div>
                    <div class="row">
                        <p>Укажите цвет текста: <input type="color" name="ct" value="${userProperty.colorText!''}"></p>
                    </div>
                    <div class="row">
                        <p>Укажите размер текста:
                            <select class="custom-select"  name="st">
                            <option selected value="${userProperty.sizeText!''}">${userProperty.sizeText!''}</option>
                                <option value="24">24</option>
                                <option value="22">22</option>
                                <option value="20">20</option>
                                <option value="16">16</option>
                                <option value="14">14</option>
                                <option value="12">12</option>
                                <option value="10">10</option>
                                <option value="8">8</option>
                            </select>
                        </p>
                    </div>
                </div>
                <button class="btn btn-primary">Сохранить настройки</button>
                <input type="hidden" value="${setPage.id}" name="idPage">
                <input type="hidden" value="${idBook}" name="idBook">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
            </form>
        </div>
    </div>
    <div class="card">
        <div class="card-head row m-2">
            <div class="col-sm-3">
                <#if isNext>
                    <form action="/book/pageRead" method="post">
                        <div class="input-group-append">
                            <button class="btn btn-outline-primary" value="${previousPage.id!''}" name="idPage" type="submit">${previousPage.namePage!''}</button>
                        </div>
                        <input type="hidden" value="${idBook}" name="idBook">
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                    </form>
                </#if>
            </div>
            <div class="col-sm-5">
                <form action="/book/pageRead" method="post">
                    <div class="input-group">
                        <select class="custom-select" id="inputGroupSelect04 " name="idPage">
                            <option selected value="${setPage.id}">Часть:${setPage.numberChapter!''}/Глава:${setPage.numberPage!''} - ${setPage.namePage!''}</option>
                            <#list pagesInfo as info>
                                <option value="${info.id!''}">Часть:${info.numberChapter!''}/Глава:${info.numberPage!''} - ${info.namePage!''}</option>
                            </#list>
                        </select>
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="submit">Открыть</button>
                        </div>
                    </div>
                    <input type="hidden" value="${idBook}" name="idBook">
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                </form>
            </div>
            <div class="col-sm-3">
                <#if isPrev>
                    <form action="/book/pageRead" method="post">
                        <div class="">
                            <button class="btn btn-outline-primary" value="${nextPage.id!''}"  name="idPage" type="submit">${nextPage.namePage!''}</button>
                        </div>
                        <input type="hidden" value="${idBook}" name="idBook">
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                    </form>
                </#if>
            </div>

        </div>

        <div class="out mt-2 md-2" style="color: ${userProperty.colorText!''};background: ${userProperty.colorBorder!''}; font-size: ${userProperty.sizeText!''}pt">
            <input type="hidden" class="text-area" value="${text.textPage!''}">
        </div>

        <div class="card-footer row m-1">
            <div class="col-sm-3">
                <#if isNext>
                    <form action="/book/pageRead" method="post">
                        <div class="input-group-append">
                            <button class="btn btn-outline-primary" value="${previousPage.id!''}" name="idPage" type="submit">${previousPage.namePage!''}</button>
                        </div>
                        <input type="hidden" value="${idBook}" name="idBook">
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                    </form>
                </#if>
            </div>
            <div class="col-sm-5">
                <form action="/book/pageRead" method="post">
                    <div class="input-group">
                        <select class="custom-select" id="inputGroupSelect04 " name="idPage">
                            <option selected value="${setPage.id}">Часть:${setPage.numberChapter!''}/Глава:${setPage.numberPage!''} - ${setPage.namePage!''}</option>
                            <#list pagesInfo as info>
                                <option value="${info.id!''}">Часть:${info.numberChapter!''}/Глава:${info.numberPage!''} - ${info.namePage!''}</option>
                            </#list>
                        </select>
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="submit">Открыть</button>
                        </div>
                    </div>
                    <input type="hidden" value="${idBook}" name="idBook">
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                </form>
            </div>
            <div class="col-sm-3">
                <#if isPrev>
                    <form action="/book/pageRead" method="post">
                        <div class="">
                            <button class="btn btn-outline-primary" value="${nextPage.id!''}"  name="idPage" type="submit">${nextPage.namePage!''}</button>
                        </div>
                        <input type="hidden" value="${idBook}" name="idBook">
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                    </form>
                </#if>
            </div>

        </div>
    </div>
    <div class="card">
        <div class="card" style="height: 500px">
            <h2 class="textS row mt-3 ml-5">коментарии</h2>
            <div style="overflow: auto;">
                <#list comentsToBook as mes>
                    <p class="lead">idCom :${mes.id!''}/ <strong>${mes.dateCreate!''}</strong> : ${mes.author.username!''}</p>
                    ${mes.coment!''}
                </#list>
            </div>
        </div>
        <form action="/book/pageRead" method="post">
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2" name="message">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit">Отправить</button>
            </div>
        </div>
            <input type="hidden" value="${setPage.id!''}"  name="idPage">
            <input type="hidden" value="${idBook}" name="idBook">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
        </form>
    </div>

</div>
</body>
<script>
    window.onload = function(){
        let firs = document.querySelector('.text-area').value;
        document.querySelector('.out').innerHTML=firs;
    }
    colorPicker.addEventListener(".input", updateFirst, false);
    colorPicker.addEventListener("change", watchColorPicker, false);

    function watchColorPicker(event) {
        document.querySelectorAll("p").forEach(function(p) {
            p.style.color = event.target.value;
        });
    }
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</html>