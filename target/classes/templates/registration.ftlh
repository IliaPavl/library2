
<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div align="center">
        <div class="mb-3 ml-4"><h5>Панель регистрации</h5></div>
        <div class="ml-5">
            ${message?ifExists}
            <@l.login "/registration" true />
        </div>
    </div>
</@c.page>