<#import "parts/common.ftlh" as c>
<#import "parts/cab.ftlh" as cab>
<@c.page>
    <@cab.cad>
        <div class="card mt-2">
            <div class="card-head mx-auto">
                <h3 class="textS row mt-3">Входящие сообщения и переписки</h3>
            </div>
            <div class="row ml-5 mr-5">
                <#list messenger as mes>
                    <#if mes.isitnew>
                        <div class="row ml-2 ">

                            <div class="card ml-4 mr-4 mt-3 md-3 " style="width: 10rem;">
                                <a href="/user/profile/${mes.pageUser.id}" class="text-muted">
                                    <img src="/img/${mes.pageUser.userImg}" class="card-img-top" alt="ну тут картинка">
                                </a>
                                <div class="card-footer ">
                                    <div class="mx-auto">
                                        <a href="/user/profile/${mes.pageUser.id}" class="text-muted ">${mes.pageUser.username}</a>
                                            <button type="submit" class="btn btn-light d-flex justify-content-between align-items-center" value="${mes.id}" name="idMes">
                                                <a href="/user/userCabСorrespondence/${mes.pageUser.id}" class="link-cab hide"><h1 class="lead hide">Ответить</h1></a>
                                            </button>
                                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                                    </div>
                                </div>
                            </div>
                            <div class="card-head mx-auto">
                                <h3 class="textS row mt-3">Пользователь : ${mes.pageUser.username}
                                    <br> <#if mes.isitnew> Написал вам письмо <#else > Ведёт переписку</#if>
                                    <#if mes.read>
                                    <#else >
                                        <span class="badge badge-pill badge-primary"></span>
                                    </#if>
                                </h3>
                            </div>
                        </div>
                    </#if>
                <#else >

                </#list>
            </div>
        </div>
    </@cab.cad>
</@c.page>