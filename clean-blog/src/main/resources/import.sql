INSERT INTO user (id, username, password, name) VALUES (1, 'admin', '123456', 'xiaoniu');
INSERT INTO user (id, username, password, name)  VALUES (2, 'nuri', '123456', 'niuheng');

INSERT INTO authority (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
