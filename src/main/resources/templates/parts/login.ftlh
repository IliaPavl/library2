<#macro login path isRegisterForm>
    <form action="${path}" method="post" >
        <div class="form-group " >
            <label class="col-sm-2 col-form-label">Имя пользователя :</label>
            <div class="col-sm-6">
                <input type="text" name="username" <#if username??>value="${username}"</#if>
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="Имя пользователя" />
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-2 col-form-label">Пароль:</label>
            <div class="col-sm-6">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="Пароль"
                <#if pass??>
                    value="${pass}"
                </#if>
                />
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group ">
                <label class="col-sm-2 col-form-label">Пароль:</label>
                <div class="col-sm-6">
                    <input type="password" name="password2"
                           class="form-control ${(password2Error??)?string('is-invalid', '')}"
                           placeholder="Повторите пароль" />
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group ">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                           class="form-control ${(emailError??)?string('is-invalid', '')}"
                           placeholder="some@some.com"
                    <#if email??>
                        value="${email}"
                    </#if>
                    />
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <#if isRegisterForm>
        <button class="btn btn-success btn-primary" type="submit"> Зарегистрироваться</button>
        <#else>
        <div style="width: 500px" class="form-group row">
            <div class="col-sm-6">
                <button class="btn btn-success btn-primary btn-block ml-2" type="submit">Войти</button>
            </div>
            </#if>
            <div class="col-sm-6">
                <#if !isRegisterForm><button type="button" class="btn btn-light btn-primary btn-block"><a class="mr-3" href="/registration">Зарегистрироваться</a></button></#if>
            </div>
        </div>
    </form>
</#macro>