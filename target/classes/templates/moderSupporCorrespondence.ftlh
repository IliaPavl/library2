<#import "parts/common.ftlh" as c>
<#import "parts/cab.ftlh" as cab>
<@c.page>
    <@cab.cad>
        <form method="post" action="/user/moderSupporCorrespondence">
            <div class="card">
                <div class="card-head">
                    <h3 class="textS row mt-3 ml-5">Тех поддержка пользователя</h3>
                </div>

                <div class="card" style="height: 500px">
                    <div style="overflow: auto;">
                        <#list supportMessages as sup>
                            <div class="card row-sm-7">
                                <p class="lead">Дата создания сообщения : ${sup.dateCreate!''}
                                    <br>Вопрос :&nbsp;
                                    ${sup.coment!''}
                                    <br>Ответ :&nbsp;
                                    ${sup.answer!''}
                            </div>
                            <input class="form-control" aria-label="With textarea" type="hidden" value="${sup.id}" name="idMes"/>
                        </#list>
                    </div>
                </div>
                <div class="input-group">

                    <input class="form-control" aria-label="With textarea" name="textMessage"/>
                    <div class="input-group-prepend ">
                        <button class="btn btn-light input-group-text rounded-right"> Отправить</button>
                    </div>

                </div>
                <input type="hidden" value="${_csrf.token}" name="_csrf">
            </div>
        </form>
    </@cab.cad>
</@c.page>