<#import "parts/common.ftlh" as c>
<#import "parts/searchPart.ftlh" as search>
<@c.page>
    <@search.search>
        <form method="post" action="/book/searchBooks">
        <div class="row-sm">
            <div class="card mt-2">
                <div class="card-head">
                    <h3 class="textS m-3 ">Параметры поиска книги</h3>
                </div>
                <div class="card-body">
                    <div class="col md-3">
                        <h4 class="textSearchHead">Жанры</h4>
                        <div class="md-size-list">
                            <select class="custom-select" id="inputGroupSelect01" name="idGenre">
                                <option selected value="${idGenre.idGenre!''}">${idGenre.nameGenre!''}</option>
                                <option value="0">Любой жанр</option>
                                <#list genres as genre>
                                    <option value=${genre.idGenre}>${genre.nameGenre}</option>
                                </#list>
                            </select>
                        </div>
                    </div>

                    <div class="col md-3">
                        <h4 class="textSearchHead">Статус</h4>
                        <div class="md-size-list">
                            <select class="custom-select" id="inputGroupSelect01" name="idStatus">
                                <option selected value="${idStatus.id!''}">${idStatus.nameStatus!''}</option>
                                <option value="0">Любой статус</option>
                                <#list status as stat>
                                    <option value=${stat.id!''}>${stat.nameStatus!''}</option>
                                </#list>
                            </select>
                        </div>
                    </div>

                    <div class="col md-3 mt-3">
                        <h4 class="textSearchHead">Возрастной рейтинг</h4>
                        <div class="md-size-list">
                            <select class="custom-select" id="inputGroupSelect01" name="idAgeRate">
                                <option selected value="${idAgeRate.id!''}">${idAgeRate.ageRate!''}</option>
                                <option value="0">Любой рейтинг</option>
                                <#list ageRate as ageR>
                                    <option value=${ageR.id!''}>${ageR.ageRate!''}</option>
                                </#list>
                            </select>
                        </div>
                    </div>

                    <div class="col mt-3">
                        <h4 class="textSearchHead">Сортировка</h4>
                        <div class="md-size-list">
                            <select class="custom-select" id="inputGroupSelect01" name="allTypes">
                                <option selected value="0">По популярности</option>
                                <option value="1">По дате обновления</option>
                                <option value="2">По дате добавления</option>
                                <option value="3">По колличеству страниц</option>
                                <option value="4">По колличеству просмотров</option>
                            </select>
                        </div>
                    </div>
                    <div class="col mt-3">
                        <input type="submit" class="btn btn btn-primary btnRefresh" value="Найти">
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="textSearch" class="out" value="${searchValue!''}">
        </form>
    </@search.search>
</@c.page>