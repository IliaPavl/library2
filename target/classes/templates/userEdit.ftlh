<#import "parts/common.ftlh" as c>

<@c.page>
    <form action="/user" method="post">
        <div class="card mt-3">
            <div class="card-header">
                Редактирование пользователя
            </div>
            <div class="card-body">

                <div class="col-sm-12">
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="username" value="${user.username}">
                        </div>
                        <label class=" col-form-label">Текущее имя пользователя </label>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="email" value="${user.email!''}">
                        </div>
                        <label class="col-form-label">Текущий email пользователя: </label>
                    </div>

                </div>
                <div class="form-group row">
                    <div class="col-sm-6">
                        <#list roles as role>

                            <div class="custom-control custom-checkbox">
                                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
                            </div>
                        </#list>
                    </div>
                    <div class="col-sm-6 mt-3 ">Роли пользователя</div>
                </div>

            </div>
            <div class="card-footer text-muted">
                <input type="hidden" value="${user.id}" name="userId">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <button class="btn btn-primary ml-2" type="submit">Сохранить</button>
            </div>
        </div>
    </form>
</@c.page>