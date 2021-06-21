<#import "parts/common.ftlh" as c>
<#import "parts/cab.ftlh" as cab>
<@c.page>
    <@cab.cad>

            <div class="card mt-2">
                <div class="card-head mx-auto">
                    <h3 class="textS row mt-3">Друзья</h3>
                </div>
                <#list friendsList as frriend>
                    <div class="row">
                        <div class="col-sm-2">
                            <a href="/user/profile/${frriend.authorFriend.id}" class="text-muted name" >
                                <img src="/img/${frriend.authorFriend.userImg}" id="btn-tooltip" title="${frriend.authorFriend.username}" class="round text-center m-3" width="80px">
                            </a>
                        </div>
                        <div class="col-sm-2 my-auto">
                            <button type="submit" class="btn btn-light d-flex justify-content-between align-items-center">
                                <a href="/user/userCabСorrespondence/${frriend.authorFriend.id}" class="link-cab hide"><h1 class="lead hide">Написать</h1></a>
                            </button>
                        </div>
                        <div class="col-sm-2 my-auto">
                            <form method="post" action="/user/userCabFriends">
                            <button type="submit" class="btn btn-light d-flex justify-content-between align-items-center" value="${frriend.authorFriend.id}" name="userDel">
                                <a href="" class="link-cab hide"><h1 class="lead hide">удалить</h1></a>
                            </button>
                                <input type="hidden" value="${_csrf.token}" name="_csrf">
                            </form>
                        </div>
                    </div>
                <#else >
                </#list>

            </div>

    </@cab.cad>
</@c.page>