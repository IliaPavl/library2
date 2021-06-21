insert into usr (id, username, email, password, active, vip, user_font_img,user_img)
    values (1, 'admin','admin@mail.ru', '$2a$08$jyzOgCGZJeX086BlkJL29u1gayACy0vUB/gMOyzJbplHpprgZ0/NO', true,true,'fon.png','user.png');

insert into user_role (user_id, roles)
    values (1, 'USER'), (1, 'ADMIN'), (1, 'AUTHOR'),(1, 'MODER');

insert into usrP (id)
values (1);

insert into user_property (id,color_border,color_text,user_id,size_text)
values (1,'#FFFFFF','#000000','1','12');