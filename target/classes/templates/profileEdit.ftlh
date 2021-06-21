<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="container mt-4">

        ${message?ifExists}
        <div class="card">
            <div class="card-header">
                Редактирование пользователя
            </div>
        <form method="post" action="/user/profileEdit" enctype="multipart/form-data">
            <div class="form-group row">
                <div class="card ml-5 mt-2" style="width: 18rem;">
                    <img src="/img/${filename}" class="card-img-top">
                    <div class="card-body">
                        <div class="custom-file">
                            <input type="file" name="file" id="customFile">
                            <label class="custom-file-label" style="width: 103%" for="customFile">Выберите файл</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group row ml-2">
                <label class="col-sm-2 col-form-label">Password:</label>
                <div class="col-sm-6">
                    <input type="password" name="password" class="form-control" placeholder="Новый пароль" />
                </div>
            </div>
            <div class="form-group row ml-2">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control" placeholder="some@some.com" value="${email!''}" />
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="card-footer text-muted">
            <button class="btn btn-primary" type="submit">Save</button>
            </div>
        </form>
        </div>
    </div>
</@c.page>