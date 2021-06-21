<#import "parts/common.ftlh" as c>
<#import "parts/cab.ftlh" as cab>
<@c.page>
    <@cab.cad>
        <form action="/user/byVip" method="post">
            <div class="card mt-2">
                <div class="card-head mx-auto">
                    <h3 class="textS row mt-3">VIP</h3>
                </div>
                <div class="card-body">
                    <h3 class="textS row mt-3">Что такое вип? И зачем он вам нужен?</h3>
                    <h3 class="textS row mt-3">1) Это конечноже даёт вам новые возможности!!!</h3>
                    <h3 class="textS row mt-3">2) Благодаря Vip статусу ваши произведения попадаю в шапку в поиске с особой пометкой</h3>
                    <h3 class="textS row mt-3">3) Вы поддерживаете нас и нам это ОЧЕНЬ приятно</h3>
                    <h3 class="textS row mt-3">4) В вашем профиле загорается почётная кнопка VIP </h3>
                    <h3 class="textS row mt-3 lead">Новые предложения для вашего VIP статуса можете написать в
                        <button class="btn btn-default  align-items-center btn-sm" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                            <a href="#" class="link-cab hide"><h1 class="lead hide">Служба поддержки</h1></a>
                        </button> </h3>

                    <br><p class="textS">Информиция о карде</p>
                    <input class="form-control" placeholder="Информация о карке"type="text" name="cardInfo">
                </div>
                <div class="card-footer">
                    <div class="row  mx-auto">
                        <div class="col-sm-8 mx-auto">
                            <button type="submit" class="btn btn-lg btn-block
             <#if user.vip>
                 btn-warning
                 <#else >
                 btn-outline-warning
             </#if>
                mb-1 mt-3"
                                    <#if user.vip>
                                        id="btn-tooltip" title="Поздравляю у вас есть VIP!!!"
                                    <#else >
                                        id="btn-tooltip" title="У вас нету вип? Купи его и твой книги попадут в топ!"
                                    </#if>
                                    value="Купить вип"
                            >VIP</button></a>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
        </form>
    </@cab.cad>
</@c.page>