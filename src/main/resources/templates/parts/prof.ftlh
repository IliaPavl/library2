<#macro prof>
    <div class="container-my">
        <div class="form-group">
            <div class="row">

                <div class="col-sm-3 ">

                    <img src="/img/<#if isUser>${filenameUs!''}<#else >${user.userImg!''} </#if>" class="card-img-top round2 mt-3">
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-head header" style=" background: url(/img/<#if isUser>${filenamePr} <#else>${user.userFontImg!''} </#if>) center center no-repeat;">

                            <div class="col-sm ml-5 mt-5">
                                <a class="textProfName" href="/user/profile/${user.id}"><h1 >${user.username}</h1></a>
                                <h3 class="textS">${statusLife!''}</h3>
                            </div>

                        </div>
                        <div class="card-footer mt-8">
                            <nav class="navbar navbar-expand-lg navbar-light bg-light col">
                                <ul class="navbar-nav ">
                                    <li class="nav-item">
                                        <a class="nav-link ml-4" href="/user/profile">Профиль</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link ml-4" href="/user/libraryUser">Моя библиотека</a>
                                    </li>

                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <#nested>
        </div>


    </div>
</#macro>