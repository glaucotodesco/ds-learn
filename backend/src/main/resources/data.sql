INSERT INTO tb_user (name, email, password) VALUES ('Alex Brown', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Maria Green', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Bob Blue', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_STUDENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_INSTRUCTOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);

INSERT INTO tb_course(name, img_Uri, img_Gray_Uri) VALUES ('Curso HMTL','https://cdn.pixabay.com/photo/2018/04/18/09/57/icon-3329995__340.jpg', 'https://upload.wikimedia.org/wikipedia/commons/1/1f/Switch-course-book-grey.svg');

INSERT INTO tb_offer(edition,start_Moment,end_Moment, course_id) VALUES ('1.0', TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', TIMESTAMP WITH TIME ZONE '2020-08-14T10:00:00Z', 1 );
INSERT INTO tb_offer(edition,start_Moment,end_Moment, course_id) VALUES ('2.0', TIMESTAMP WITH TIME ZONE '2021-07-14T10:00:00Z', TIMESTAMP WITH TIME ZONE '2021-08-14T10:00:00Z', 1 );


INSERT INTO tb_resource(title,description, position, img_uri, type, offer_id) VALUES ('Trilha HTML', 'Trilha Principal', 1 ,'https://cdn.pixabay.com/photo/2018/04/18/09/57/icon-3329995__340.jpg',1,1);
INSERT INTO tb_resource(title,description, position, img_uri, type, offer_id) VALUES ('Fórum', 'Tire suas duvidas', 2 ,'https://cdn.pixabay.com/photo/2018/04/18/09/57/icon-3329995__340.jpg',2,1);
INSERT INTO tb_resource(title,description, position, img_uri, type, offer_id) VALUES ('Lives', 'Lives Exclusivas', 3 ,'https://cdn.pixabay.com/photo/2018/04/18/09/57/icon-3329995__340.jpg',0,1);


INSERT INTO tb_section(title,description, position, img_uri,resource_id, prerequisite_id) values ('Capítulo 1', 'Descrição do Capítulo', 1,'https://cdn.pixabay.com/photo/2018/04/18/09/57/icon-3329995__340.jpg',1, null);
INSERT INTO tb_section(title,description, position, img_uri,resource_id, prerequisite_id) values ('Capítulo 2', 'Descrição do Capítulo2', 2,'https://cdn.pixabay.com/photo/2018/04/18/09/57/icon-3329995__340.jpg',1, 1);
INSERT INTO tb_section(title,description, position, img_uri,resource_id, prerequisite_id) values ('Capítulo 3', 'Descrição do Capítulo2', 3,'https://cdn.pixabay.com/photo/2018/04/18/09/57/icon-3329995__340.jpg',1, 2);

INSERT INTO tb_enrollment(user_id, offer_id, enroll_Moment,refund_Moment,available, only_Update) VALUES (1,1,TIMESTAMP WITH TIME ZONE '2020-07-15T10:00:00Z', null, true,false);
INSERT INTO tb_enrollment(user_id, offer_id, enroll_Moment,refund_Moment,available, only_Update) VALUES (2,1,TIMESTAMP WITH TIME ZONE '2020-07-15T10:00:00Z', null, true,false);


