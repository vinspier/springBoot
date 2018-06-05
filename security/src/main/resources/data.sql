insert into role (id,name) VALUES (1,"ROLE_USER");
insert into role (id,name) VALUES (2,"ROLE_ADMIN");

insert into user (id,username,password) VALUES (1,"fxb1","fxb1");
insert into user (id,username,password) VALUES (2,"fxb2","fxb2");
insert into user (id,username,password) VALUES (3,"fxb3","fxb3");

insert into user_roles (user_id,roles_id) VALUES (1,1);
insert into user_roles (user_id,roles_id) VALUES (2,2);
insert into user_roles (user_id,roles_id) VALUES (3,1);
insert into user_roles (user_id,roles_id) VALUES (3,2);