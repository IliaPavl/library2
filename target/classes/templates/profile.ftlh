<#import "parts/common.ftlh" as c>
<#import "parts/prof.ftlh" as p>
<@c.page>
<@p.prof>

    <div class="col-sm-3">
        <div class="card col">
            <a href="/user/byVip" class="link-cab ">
            <button type="button" class="btn btn-lg btn-block
             <#if user.vip>
                 btn-warning
                 <#else >
                 btn-outline-warning
             </#if>
                mb-1 mt-3"
                    <#if user.vip>
                        id="btn-tooltip" title="Поздравляю у вас есть VIP!!!"
                        <#else >
                        id="btn-tooltip" title="У вас нету вип? Купи его и твой книги попадут в топ!"
                    </#if>
            >VIP</button></a>
            <br><h4 class="textS mr-3 ml-3 mb-3 mt-1 text-center"> <#if user.active>На сайте<#else >Заблокирован</#if></h4>
        </div>
        <div class="card col mt-2">
            <h4 class="textS m-4 lead text-center">
                Дата регистрации :
                <br class="mt-2">${user.dateCreate!''}
                <br><br>Ссылки :
                <a href="${user.userPersonal.vkHref!''}">vk</a>
                <a href="${user.userPersonal.skypeHref!''}">Skype</a>
                <a href="${user.userPersonal.instagramHref!''}">Instagram</a>
                <a href="${user.userPersonal.facebookHref!''}">Facebook</a>
            </h4>
        </div>
    </div>
    <div class="col-sm-6">

        <div class="card col" >
            <h5 class="textS m-3 "> Информация о пользователе</h5>
            <div class="card" >
                <div style="overflow: auto;">
                    <p class="Lead"> День рождения :${user.userPersonal.dateBirsday!''}</p>
                    <p class="Lead"> Пол :${user.userPersonal.gender!''}</p>
                        <div class="card row-sm-7">
                            <h5 class="textS m-3 "> О себе</h5>
                                ${user.userPersonal.aboutUser!''}
                        </div>
                </div>
            </div>
        </div>
        <div class="card col mt-2">
            <h5 class="textS m-3 "> Гостевая книга</h5>
            <div class="card" style="height: 250px">
                <div style="overflow: auto;">
                    <#list profileBook as book>
                        <h5 class="textS">${book.author.username!''}</h5>
                        Номер сообщения : <strong>${book.numberMes!''}</strong>//${book.dateCreate!''}
                        <br>${book.coment!''}

                        <#else >
                    </#list>
                </div>
            </div>
            <form method="post" action="/user/profile">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2" name="text">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">Отправить</button>
                    </div>
                </div>
                <input type="hidden" value="${user.id}" name="userid">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
            </form>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="card">
            <h4 class="textS m-4 text-center">
                Подписки :
                <div class="row">
                    <#list authors as author>
                        <a href="/user/profile/${author.id}" class="text-muted name" >
                        <img src="/img/${author.userImg}" id="btn-tooltip" title="${author.username}" class="round text-center m-3" width="80px">
                        </a>
                        <#else >
                        0
                    </#list>
                </div>

            </h4>
        </div>
    </div>

</@p.prof>
</@c.page>