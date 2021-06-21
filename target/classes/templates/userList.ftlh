<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="container mt-3">
        <form action="/user" method="get">
        <div class="input-group m-3">
            <input type="text" class="form-control" name="searchLine" placeholder="Искать..." value="${searchLine?if_exists}">

            <div class="input-group-append">

                    <div  class="dropdown">
                        <a class="btn btn-light dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <input type="hidden" class="btn btn-link" value="" name="searchBy">Искать
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">

                            <a class="dropdown-item" type="submit" value="id" href="#">

                                <input type="submit" class="btn btn-link" value="id" name="searchBy">
                            </a>
                            <a class="dropdown-item" type="submit" name="searchBy" value="username" href="#" >
                                <input type="submit" class="btn btn-link" value="username" name="searchBy">
                                </a>
                            <a class="dropdown-item" type="submit" name="searchBy" value="email" href="#">
                                <input type="submit" class="btn btn-link" value="email" name="searchBy"></a>
                        </div>
                    </div>

            </div>
        </div>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
        </form>

        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
            <tr>
                <th scope="col" width="10%">id</th>
                <th scope="col">Имя</th>
                <th scope="col">Email</th>
                <th scope="col">Роль</th>
                <th scope="col">VIP</th>
                <th scope="col">ACTIV</th>
                <th scope="col" width="10%">#</th>
                <th scope="col" width="10%">#</th>
            </tr>
            </thead>
            <tbody class="table-light">
            <#list users as user>

                <tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.username}</td>
                    <td>${user.email!''}</td>
                    <td><#list user.roles as role>${role}<#sep>, </#list></td>
                    <td>
                        <form action="/user/vip" method="post">
                            <input type="hidden" value="${user.id}" name="userId">
                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                            <button class="btn btn-primary ml-2" type="submit">
                                <#if user.vip!=false>
                                    Off
                                <#else >
                                    On
                                </#if>
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="/user/activ" method="post">
                            <input type="hidden" value="${user.id}" name="userId">
                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                            <button class="btn btn-primary ml-2" type="submit">
                                <#if user.active!=false>
                                    Off
                                <#else >
                                    On
                                </#if>
                            </button>
                        </form>
                    </td>

                    <td width="10%"><a style="text-decoration: none;" href="/user/${user.id}"><strong>EDIT</strong></a></td>
                    <td width="10%">
                        <form action="/user/delete" method="post">
                            <input type="hidden" value="${user.id}" name="userId">
                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                            <button class="btn btn-primary ml-2" type="submit">DEL</button>
                        </form>
                    </td>
                </tr>

            </#list>
            </tbody>
        </table>
    </div>
</@c.page>