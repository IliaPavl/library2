<#import "parts/common.ftlh" as c>

<#import "parts/common.ftlh" as c>
<#import "parts/cab.ftlh" as cab>
<@c.page>
    <@cab.cad>
<div class="card">
    <div class="card-head">
        <h3 class="textS m-3 ">Рецензии пользователя</h3>
            <#list listRecens as rec>
                <div class="card">
                    <div class="card-head">
                        <h3>Рецензия на книгу: ${rec.bookid.nameBook!''}</h3>
                    </div>
                    <div class="crad-footer">
                        <form method="get" action="/user/recenzCorrespondence">
                            <input type="hidden" value="${rec.bookid.id!''}" name="idBook">
                            <input type="submit" class="btn btn-primary" value="Открыть">
                        </form>
                        <form method="post" action="/user/recens/update">
                            <input type="submit" class="btn btn-danger" value="Удалить">
                            <input type="hidden" value="${rec.bookid.id!''}" name="idBook">
                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                        </form>
                    </div>
                </div>
                <#else >
                У вас нету рецензий
            </#list>
    </div>
</div>
    </@cab.cad>
</@c.page>