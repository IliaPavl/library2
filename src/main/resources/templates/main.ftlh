<#import "parts/common.ftlh" as c>

<@c.page>
    <#assign
    known = Session.SPRING_SECURITY_CONTEXT??
    >

    <#if known>
        <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isAdmin = user.isAdmin()
        >
    <#else>
        <#assign
        name = "Гость"
        isAdmin = false
        >
    </#if>


        <div class="form-row mt-2 ml-3" >
            <div class="row col-md-6" >
                <form method="get" action="/main" class="form-inline ml-3">
                    <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Поиск по тэгу">
                    <button type="submit" class="btn btn-primary ml-2">Найти</button>
                </form>
                <#if isAdmin>
                    <a class="btn btn-primary ml-2" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                        Новая новость
                    </a>
                </#if>

            </div>
        </div>


        <div class="collapse mt-2 ml-3 mr-3 <#if message??>show</#if>" id="collapseExample">
            <div class="card mt-2 ml-3 mr-3">
                <div class="card-header">
                    Новая новость
                </div>
                <div class="form-group mt-3">
                    <form method="post" enctype="multipart/form-data">
                        <div class="form-group">
                    <textarea   type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                                value="<#if message??>${message.text}</#if>" name="text" placeholder="Введите сообщение" ></textarea>

                            <#if textError??>
                                <div class="invalid-feedback">
                                    ${textError}
                                </div>
                            </#if>
                        </div>
                        <div class="form-group col-sm-6">
                            <input type="text" class="form-control"
                                   value="<#if message??>${message.tag}</#if>" name="tag" placeholder="Тэг">
                            <#if tagError??>
                                <div class="invalid-feedback">
                                    ${tagError}
                                </div>
                            </#if>
                        </div>
                        <div class="form-group col-sm-6">
                            <div class="custom-file">
                                <input type="file" name="file" id="customFile">
                                <label class="custom-file-label" for="customFile">Выберите файл</label>
                            </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <div class="card-footer">
                            <button type="submit" class="btn btn-primary">Добавить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="card-rows mt-2 ml-3 mr-3">
            <#list messages as message>
                <div class="card my-3" >
                    <#if message.filename??>
                        <img src="/img/${message.filename}" class="card-img-top" style="height: 200px">
                    </#if>
                    <div class="m-2">
                        <span>${message.text}</span>
                        <i>${message.tag}</i>
                    </div>
                    <div class="card-footer text-muted">
                        ${message.authorName}
                    </div>
                </div>
            <#else>
                Нет сообщений
            </#list>
        </div>

</@c.page>