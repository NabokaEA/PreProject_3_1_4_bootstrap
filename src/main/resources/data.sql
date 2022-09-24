delete from  users_roles;
delete from  roles;
delete from  users;

INSERT INTO roles (id, role) VALUES
                                 (1, 'ROLE_ADMIN'),
                                 (2, 'ROLE_USER');

INSERT INTO users (id, name, password) VALUES
                                           (1, 'Admin','Admin'),
                                           (2, 'User', 'User');

insert into users_roles(User_id, roles_id) values
                                            (1,1),
                                            (1,2),
                                            (2,2);