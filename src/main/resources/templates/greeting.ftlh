<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="m-2 ml-2 fix">
        <div class="row-sm-2 m-3 Aaa" id="id2">
            <#list firstGenres as genre>
                <form method="post" action="/book/searchBooks" class="ml-2">
                    <input type="hidden" name="idGenre" value="${genre.idGenre!''}">
                    <input type="hidden" name="idStatus" value="0">
                    <input type="hidden" name="idAgeRate" value="0">
                    <input type="hidden" name="allTypes" value="0">
                    <input type="submit" class="astext " value="${genre.nameGenre!''}">
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                </form>
                <#else >
            </#list>
            <form method="post" action="/book/searchBooks" class="ml-2">
                <input type="hidden" name="idGenre" value="0">
                <input type="hidden" name="idStatus" value="0">
                <input type="hidden" name="idAgeRate" value="0">
                <input type="hidden" name="allTypes" value="0">
                <input type="submit" class="astext " value="Другие жанры">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
            </form>
            </div>
        </div>
        <div class="row-sm-8 m-3 " id="id2">
            <div class="row m-3">
                <h2 class="textS">Последние добавления</h2>
            </div>
            <div class="row m-3">
                <#list books as book>
                <div class="card m-2 ml-3" style="width: 13rem;">
                    <a href="/book/bookPage/${book.id}" class="card-text text-dark">
                        <img src="/img/${book.imgBook!''}" height="200px" class="card-img-top" alt="ну тут картинка">
                    </a>
                    <div class="card-body">
                        <a href="/book/bookPage/${book.id}" class="card-text text-dark"> <strong style="font-size: 20pt">${book.nameBook!''}</strong>  <strong style="font-size: 20pt">${book.ageRate.ageRate!''}</strong></a>
                    </div>
                    <div class="card-footer">
                        <a href="/user/profile/${book.author.id}" class="text-muted">${book.author.username!''}</a>
                    </div>
                    </div>
                    <#else >
                </#list>
            </div>

        </div>
    </div>
</@c.page>