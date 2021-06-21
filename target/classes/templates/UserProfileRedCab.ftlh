<#import "parts/common.ftlh" as c>
<#import "parts/cab.ftlh" as cab>
<@c.page>
    <@cab.cad>
        <form method="post" enctype="multipart/form-data">
            <div class="card mt-2">
                <div class="card-head mx-auto">
                    <h3 class="textS row mt-3">Основная информация</h3>
                </div>
                <div class="row mt-3 mx-auto">
                    <div class="form-group row">
                        <div class="card  mt-2" style="width: 18rem;">
                            <img src="/img/${filenameUs}" class="card-img-top">
                            <div class="card-body">
                                <div class="custom-file">
                                    <input type="file" name="file" id="customFile">
                                    <label class="custom-file-label" style="width: 103%" for="customFile">Выберите файл</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-head header" style=" background: url(/img/${filenamePr}) center center no-repeat;">
                    <div class="form-group col-sm-5 m-3">
                        <div class="custom-file">
                            <input type="file" name="fileBG" id="customFile2">
                            <label class="custom-file-label" for="customFile2">Выберите картинку для фона</label>
                        </div>
                    </div>
                </div>
            <div class="card ">
                <div class="row-sm-8 m-3 ">

                    <div class="col-sm-8 mx-auto">

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">Имя</span>
                            </div>
                            <input type="text" class="form-control" placeholder="Имя" name="name" aria-label="Username" aria-describedby="basic-addon1" value="${userpersonal.name!''}">
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">Фамилия</span>
                            </div>
                            <input type="text" class="form-control" placeholder="Фамилия" name="surname" aria-label="Username" aria-describedby="basic-addon1" value="${userpersonal.surname!''}">
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">Дата рождения : ${userpersonal.dateBirsday!''}</span>
                            </div>
                            <input type="date" min="1930-01-01" max="2012-01-01" value="${userpersonal.dateBirsday!''}" class="form-control" name="date">
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">Пол</span>
                            </div>
                            <select class="custom-select" id="inputGroupSelect01" name="gender" >
                                <option selected value="${userpersonal.gender!''}">${userpersonal.gender!''}</option>
                                <option value="Мужской">Мужской</option>
                                <option value="Женский">Женский</option>
                            </select>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">Статус</span>
                            </div>
                            <input type="text" class="form-control" placeholder="Статус" name="statusLife" aria-label="Username" aria-describedby="basic-addon1" value="${userpersonal.statusLife!''}">
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">О себе</span>
                            </div>
                            <textarea class="text-area col-sm-10" name="aboutUser" >
                            ${userpersonal.aboutUser!''}
                    </textarea>
                        </div>

                    </div>
                </div>
            </div>


            <div class="card">
                <h3 class="textS mt-3 mx-auto">Ссылки</h3>
                <div class="row-sm-8 m-3">
                    <br><input type="text" value="${userpersonal.vkHref!''}" class="form-control" name="vk" placeholder="https://vk.com/username">
                    <br><input type="text" value="${userpersonal.facebookHref!''}" class="form-control" name="face" placeholder="https://www.facebook.com/username">
                    <br><input type="text" value="${userpersonal.instagramHref!''}" class="form-control" name="inst" placeholder="https://instagram.com/username">
                    <br><input type="text" value="${userpersonal.skypeHref!''}" class="form-control" name="skype" placeholder="skype.username">
                </div>
                <div class="card-footer ">
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <div class="row ">
                        <button class="btn btn-primary ml-2" type="submit">Сохранить</button>
                    </div>

                </div>
            </div>
        </form>
    </@cab.cad>
</@c.page>