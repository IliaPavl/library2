<#import "parts/common.ftlh" as c>

<@c.page>

    <form method="post" class="form-horizontal" action="/user/addBook" enctype="multipart/form-data">
        <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal>
        <div class="container col-sm-10 mt-5">
            <div class="form-group col-sm-5">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Выберите картинку для обложки</label>
                </div>
            </div>

            <div class="row-md-6 mt-4">
                <label >  Название книги</label>
                <input type="text" name="nameBook" class="form-control" placeholder="Название..."/>
            </div>

            <div class="row-md-6 mt-4">
                <label>Описание книги</label>
                <textarea type="text" name="about" class="form-control" placeholder="Описание..."></textarea>
            </div>

            <div class="row-md-6 mt-4">
                <label class="m-2">Жанры :</label>
                <div class="form-group">
                    <div class="row">
                        <#list genres as gener>

                            <div class="col-sm-4">
                                <div class=" qwe custom-control custom-checkbox ">
                                    <input type="checkbox" name="${gener.nameGenre}">
                                    <label>${gener.nameGenre}</label>
                                </div>
                            </div>

                        <#else>
                            <label>Нет жанров</label>
                        </#list>
                    </div>
                </div>
            </div>

            <div class="row-md-6 ml-3">
                <div class="form-group">
                    <label class="row-sm-2 mb-2">Возрастной рейтинг :</label>
                    <#list ageRate as ageR>
                        <div class="input-group row mt-1">
                            <div class="input-group-prepend mr-2">
                                <div class="input-group-text">
                                    <input type="radio" name="ageRate" value="${ageR.ageRate}">
                                </div>
                            </div>
                            <span class="row-sm-4" >${ageR.ageRate}</span>
                        </div>
                    <#else>
                        <label>Нет ограничений по возрасту</label>
                    </#list>

                </div>
            </div>

            <div class="row-md-6 ml-3">
                <div class="form-group">
                    <label class="row-sm-2 mb-2">Статус :</label>
                    <#list status as stat>
                        <div class="input-group row mt-1">
                            <div class="input-group-prepend mr-2">
                                <div class="input-group-text">
                                    <input type="radio" name="status" value="${stat.nameStatus}">
                                </div>
                            </div>
                            <span class="row-sm-4" >${stat.nameStatus}</span>
                        </div>
                    <#else>
                        <label>Нет статусов</label>
                    </#list>
                </div>
            </div>


        </div>
        <div class="row-md-6 " align="center">

            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button type="submit" class="btn btn-primary">Добавить</button>
        </div>

    </form>
</@c.page>