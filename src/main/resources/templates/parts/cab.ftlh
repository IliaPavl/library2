<#macro cad>
    <div class="row">

        <div class="col-sm-3">
            <div class="form-group">
                <div class="card mt-2 ml-2">
                    <div class=" header2 " style=" background: url(/img/${filenamePr}) center center no-repeat;">
                        <img src="/img/${filenameUs}" class="round text-center m-3" >
                    </div>

                    <div class="btn-group-vertical row m-2">

                        <button type="submit" class="btn mt-4 btn-light d-flex justify-content-between align-items-center">
                            <a href="/user/UserProfileRedCab" class="link-cab hide"><h1 class="lead">Моя страница</h1></a>
                        </button><input type="hidden" name="_csrf" value="${_csrf.token}" />

                        <button type="submit" class="btn btn-light d-flex justify-content-between align-items-center">
                            <a href="/user/userCarMyWritedBook" class="link-cab hide"><h1 class="lead hide" >Мои произведения</h1></a>
                        </button>
                        <button type="submit" class="btn btn-light d-flex justify-content-between align-items-center">
                            <a href="/user/recens" class="link-cab hide"><h1 class="lead hide">Рецензии</h1></a>
                        </button>
                        <button type="submit" class="btn btn-light d-flex justify-content-between align-items-center">
                            <a href="/user/profileEdit" class="link-cab hide"><h1 class="lead hide">Безопасность и общее</h1></a>
                        </button>
                        <button type="submit" class="btn btn-light d-flex justify-content-between align-items-center">
                            <a href="/user/messagesForUser" class="link-cab hide"><h1 class="lead hide">Входящие</h1></a>
                        </button>
                        <button type="submit" class="btn btn-light d-flex justify-content-between align-items-center">
                            <a href="/user/userCabFriends" class="link-cab hide"><h1 class="lead hide">Друзья</h1></a>
                        </button>
                        <button type="submit" class="btn btn-light d-flex justify-content-between align-items-center">
                            <a href="/user/userCabSub" class="link-cab hide"><h1 class="lead hide">Подписки</h1></a>
                        </button>
                    </div>

                    <div class="btn-group-vertical row m-2">
                        <button class="btn btn-light align-items-center" type="button" >
                            <a href="/user/userCabSupport" class="link-cab hide"><h1 class="lead hide">Служба поддержки</h1></a>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-9">
            <#nested >
        </div>
    </div>

</#macro>