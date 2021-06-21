<#import "parts/common.ftlh" as c>
<#import "parts/cab.ftlh" as cab>
<@c.page>
    <@cab.cad>
        <div class="card mt-2">
        <div class="card-head mx-auto">
            <h3 class="textS row mt-3 ml-5">Входящие запросы в техподдержку (Требующие ответа)</h3>
            <div class="row ml-5 mr-5">
                <#list messengerNew as mes>
                        <div class="row ml-2 ">

                            <div class="card ml-4 mr-4 mt-3 md-3 " style="width: 10rem;">
                                <a href="/user/profile/${mes.author.id}" class="text-muted">
                                    <img src="/img/${mes.author.userImg}" class="card-img-top" alt="ну тут картинка">
                                </a>
                                <div class="card-footer ">
                                    <div class="mx-auto">
                                        <a href="/user/profile/${mes.author.id}" class="text-muted ">${mes.author.username}</a>
                                        <button type="submit" class="btn btn-light d-flex justify-content-between align-items-center" value="${mes.id}" name="idMes">
                                            <a href="/user/moderSupporCorrespondence/${mes.id}" class="link-cab hide"><h1 class="lead hide">Ответить</h1></a>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-head mx-auto">
                                <h3 class="textS row mt-3">Пользователь : ${mes.author.username}
                                    <br>  Задал вопрос
                                </h3>
                            </div>
                        </div>

                <#else >
                </#list>
            </div>
        </div>
        </div>
        <div class="card mt-2">
            <div class="card-head mx-auto">
                <h3 class="textS row mt-3 ml-5">Входящие запросы в техподдержку (обработанные)</h3>
                <div class="row ml-5 mr-5">
                    <#list messengerReady as mes>
                        <div class="row ml-2 ">

                            <div class="card ml-4 mr-4 mt-3 md-3 " style="width: 10rem;">
                                <a href="/user/profile/${mes.author.id}" class="text-muted">
                                    <img src="/img/${mes.author.userImg}" class="card-img-top" alt="ну тут картинка">
                                </a>
                                <div class="card-footer ">
                                    <div class="mx-auto">
                                        <a href="/user/profile/${mes.author.id}" class="text-muted ">${mes.author.username}</a>
                                        <button type="submit" class="btn btn-light d-flex justify-content-between align-items-center" value="${mes.id}" name="idMes">
                                            <a href="/user/moderSupporCorrespondence/${mes.id}" class="link-cab hide"><h1 class="lead hide">Ответить</h1></a>
                                        </button>

                                    </div>
                                </div>
                            </div>
                            <div class="card-head mx-auto">
                                <h3 class="textS row mt-3">Пользователь : ${mes.author.username}
                                    <br> Вопрос обработан
                                </h3>
                            </div>
                        </div>

                    <#else >
                    </#list>
                </div>
            </div>
        </div>
        <input type="hidden" value="${_csrf.token}" name="_csrf">

    </@cab.cad>
</@c.page>