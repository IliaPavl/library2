<#import "parts/common.ftlh" as c>
<#import "parts/cab.ftlh" as cab>
<@c.page>
    <@cab.cad>
        <div class="card">
            <div class="col card-body mt-3">
                <form method="post" action="/user/genresAdd">
                <div class="row m-2">
                    <div class="input-group mb-3">

                        <input type="text" class="form-control" name="nameNewGenre" placeholder="Имя нового жанра">
                        <div class="input-group-append">
                            <button class="btn btn-outline-primary" type="submit" id="button-addon2">сохранить</button>
                        </div>
                    </div>
                </div>
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                </form>
                <div class="row">
                    <#list genresList as genre>
                        <form method="post" action="/user/genresAdd">
                            <div class="row-sm m-2">
                                <div class="input-group mb-3 ">
                                    <input type="text" class="form-control" name="nameGenre" value="${genre.nameGenre!''}">
                                    <div class="input-group-append">
                                        <button class="btn btn-outline-danger" name="idGenreDel" value="${genre.idGenre!''}" type="submit" id="button-addon2">-</button>
                                    </div>
                                    <div class="input-group-append">
                                        <button class="btn btn-success" name="idGenreUpdate" value="${genre.idGenre!''}" type="submit" id="button-addon2">+</button>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                        </form>
                    <#else >
                        Нету жанров
                    </#list>
                </div>
            </div>
        </div>
    </@cab.cad>
</@c.page>