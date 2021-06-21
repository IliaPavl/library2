<#import "parts/common.ftlh" as c>

<#import "parts/common.ftlh" as c>
<#import "parts/cab.ftlh" as cab>
<@c.page>
    <@cab.cad>
        <form method="post" action="/user/recenzCorrespondence">
            <div class="card mt-2">
                <div class="card-head mx-auto">
                    <h3 class="textS row mt-3">Рецензия ${user.username!''} к книге : ${book.nameBook!''} </h3>
                </div>
                <div class="card" style="height: 500px">
                    <div style="overflow: auto;">
                        <textarea  class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2" name="coment">
                        ${coment.recenzText!''}
                        </textarea>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">Отправить</button>
                    </div>
                </div>
            </div>
            <input type="hidden" name="idBook" value="${book.id!''}">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
        </form>
    </@cab.cad>
</@c.page>