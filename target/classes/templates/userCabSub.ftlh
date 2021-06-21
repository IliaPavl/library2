<#import "parts/common.ftlh" as c>
<#import "parts/cab.ftlh" as cab>
<@c.page>
    <@cab.cad>
            <form method="post" action="/user/userCabSub">
            <div class="card mt-2">
                <div class="card-head mx-auto">
                    <h3 class="textS row mt-3">Мои подписки</h3>
                </div>
                <div class="row m-3 mx-auto">
                    <div class="row">
                        <#list authors as author>
                            <div class="card m-1">
                                <div class="row-sm-3">
                                    <div class="col">
                                        <a href="/user/profile/${author.id}" class="text-muted" >
                                            <img src="/img/${author.userImg}" id="btn-tooltip" title="${author.username}" class="round text-center m-3">
                                        </a>
                                    </div>
                                    <div class="col mx-auto text-center">
                                        <a href="/user/profile/${author.id}" class="text-muted" ><p class="lead">${author.username} </p> </a>
                                    </div>
                                    <div class="col mx-auto mb-2">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <button class="btn btn-light d-flex justify-content-between align-items-center" type="submit" name="idAuthor" value="${author.id}">
                                             Отписаться
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <#else >
                            0
                        </#list>
                    </div>
                </div>
            </div>
                <input type="hidden" value="${_csrf.token}" name="_csrf">
            </form>
    </@cab.cad>
</@c.page>