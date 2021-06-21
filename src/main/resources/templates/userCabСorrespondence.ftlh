<#import "parts/common.ftlh" as c>
<#import "parts/cab.ftlh" as cab>
<@c.page>
    <@cab.cad>
        <form method="post" action="/user/userCabСorrespondence">
        <div class="card mt-2">
        <div class="card-head mx-auto">
            <h3 class="textS row mt-3">Переписка с пользователем : ${userToMes.username}</h3>
        </div>
            <div class="card" style="height: 500px">
                <div style="overflow: auto;">
                    <#list messenger as mes>
                           <p class="lead">Номер сообщения : <strong>${mes.numberMes}</strong>//${mes.dateCreate!''}</p>
                            ${mes.coment}
                    </#list>
                </div>
            </div>
            <div class="input-group mb-3">
                <input type="hidden" name="userToMes" value="${userToMes.id}">
                <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2" name="message">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="submit">Отправить</button>
                </div>
            </div>

        </div>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
        </form>
    </@cab.cad>
</@c.page>