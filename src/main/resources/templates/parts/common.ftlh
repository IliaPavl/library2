<#macro page>
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
    <#assign
    known = Session.SPRING_SECURITY_CONTEXT??
    >

    <#if known>
        <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isAdmin = user.isAdmin()
        isModer= user.isModer()
        >
    <#else>
        <#assign
        name = "Гость"
        isAdmin = false
        isModer = false
        >
    </#if>
    <div class="row">
        <nav class="navbar navbar-expand-lg navbar-light bg-light col">
            <a class="navbar-brand ml-4" href="/">Библиотека</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link ml-4" href="/main">Новости сайта</a>
                    </li>
                    <#if isAdmin>
                        <li class="nav-item">
                            <a class="nav-link ml-4" href="/user">Список пользователей</a>
                        </li>
                    </#if>
                    <#if isModer>
                        <li class="nav-item">
                            <a class="nav-link ml-4" href="/user/moderSupport">Ответы службы поодержки</a>
                        </li>
                    </#if>
                    <#if user??>
                        <li class="nav-item">
                            <a class="nav-link ml-4" href="/user/libraryUser">Моя библиотека</a>
                        </li>

                    </#if>
                </ul>

                <div class="float-right row mt-2">
                    <div class=" mr-2 textS ">
                        <form method="get" action="/book/search">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <button type="submit" class="btn btn-light input-group-text" id="basic-addon1"><h1 class="textS lead">Q </h1></button>
                                </div>
                                <input type="text" name="textSearch" class="form-control" placeholder="Найти произведение или автора" aria-describedby="basic-addon1">
                            </div>
                            <input type="hidden" name="typeSearch" value="Все">
                        </form>
                    </div>
                    <div class=" mr-3">
                        <#if user??>
                            <div  class="dropdown">
                                <a class="btn btn-light dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    ${name}
                                </a>

                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
                                    <a class="dropdown-item" href="/user/profileEdit">Редактировать профиль</a>
                                    <a class="dropdown-item" href="/user/profile">Профиль</a>
                                    <a class="dropdown-item" href="/user/libraryUser">Моя библиотека</a>
                                    <a class="dropdown-item" href="/user/addBook">Стать автором</a>
                                    <a class="dropdown-item" href="/main">Новости сайта</a>
                                    <a class="dropdown-item" href="/user/UserProfileRedCab">Личный кабинет</a>
                                    <#if isAdmin>
                                        <a class="nav-link ml-4" href="/user/genresAdd">Управление жанрами</a>
                                    </#if>
                                </div>
                            </div>
                        <#else >
                            ${name}
                        </#if>
                    </div>
                    <div class=" mr-4 ">
                        <form action="/logout" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <button class="btn btn-primary" type="submit">
                                <#if user??>
                                    Выйти
                                <#else >
                                    Войти
                                </#if>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </nav>
    </div>
    <div class="container-my" id="maket">
        <#nested>
    </div>

    <div id="footer" class="mt-5 navbar-light bg-light">
        <div class="row navbar-light bg-light">
            <div class="col-sm-4">
                <h3 class="textS">Ссылки</h3>
                <a class="nav-link ml-4" href="/">Библиотека</a>
                <a class="nav-link ml-4" href="/main">Новости сайта</a>
                <#if isAdmin>
                    <a class="nav-link ml-4" href="/user">Список пользователей</a>
                </#if>
                <#if isModer>
                    <a class="nav-link ml-4" href="/user/moderSupport">Ответы службы поодержки</a>
                </#if>

            </div>
            <div class="col mt-4"> <#if user??>
                    <a class="nav-link ml-4" href="/user/profileEdit">Редактировать профиль</a>
                    <a class="nav-link ml-4" href="/user/profile">Профиль</a>
                    <a class="nav-link ml-4" href="/user/libraryUser">Моя библиотека</a>
                    <a class="nav-link ml-4" href="/user/addBook">Стать автором</a>
                    <a class="nav-link ml-4" href="/main">Новости сайта</a>
                    <a class="nav-link ml-4" href="/user/UserProfileRedCab">Личный кабинет</a>
                </#if>
            </div>
            <div class="col-sm-4">
                <h3 class="textS">Информаци о сайте</h3>
                <a class="nav-link ml-4" href="/main/sucses">Пользовательское соглашение</a>
                <a class="nav-link ml-4" href="/main/about">О нас</a>
                <a class="nav-link ml-4" href="/main/rules">Правила</a>
            </div>
        </div>
        <div class="row navbar-light bg-light ">
            <div class="ml-5">
                Работу выполнил Павлючков Илья 873601
            </div>
        </div>
    </div>

    </body>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

    </html>
</#macro>