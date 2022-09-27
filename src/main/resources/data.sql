delete from  users_roles;
delete from  roles;
delete from  users;

INSERT INTO roles (id, name) VALUES
                                 (1, 'ROLE_ADMIN'),
                                 (2, 'ROLE_USER');

INSERT INTO users (id, name,lastName, password, email, age) VALUES
                                           (1, 'Admin','Admin', 'Admin',"1@1.ru", 18),
                                           (2, 'User', 'User','User', "2@1.ru", 28);

insert into users_roles(User_id, roles_id) values
                                            (1,1),
                                            (1,2),
                                            (2,2);