INSERT INTO Role VALUES(NULL, 'Admin');
INSERT INTO Role VALUES(NULL, 'User');

INSERT INTO User VALUES(NULL, 'Alan', 'Smith', '$2a$10$SKww238GdhtdxsBXxbf7JeI2dSHC5IkiACM1yZtA1pwaD2puWQDN2', 'admin1@miu.edu');
INSERT INTO User VALUES(NULL, 'Helen', 'Pearson', '$2a$10$SKww238GdhtdxsBXxbf7JeI2dSHC5IkiACM1yZtA1pwaD2puWQDN2', 'user1@miu.edu');
INSERT INTO User VALUES(NULL, 'Robin', 'Plevin', '$2a$10$SKww238GdhtdxsBXxbf7JeI2dSHC5IkiACM1yZtA1pwaD2puWQDN2', 'user2@miu.edu');

INSERT INTO User_Roles VALUES(1, 1);
INSERT INTO User_Roles VALUES(2, 2);
INSERT INTO User_Roles VALUES(2, 3);