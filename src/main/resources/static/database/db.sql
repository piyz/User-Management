CREATE TABLE users(
  user_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  first_name VARCHAR(255) NOT NULL ,
  last_name VARCHAR(255) NOT NULL ,
  username VARCHAR(255) NOT NULL ,
  password VARCHAR(255) NOT NULL ,
  email VARCHAR(255) NOT NULL ,
  birthday DATE ,
  active INT(11) DEFAULT NULL ,
  zip VARCHAR(255) ,
  country VARCHAR(255) ,
  city VARCHAR(255) ,
  district VARCHAR(255),
  street VARCHAR(255)
);

CREATE TABLE groups(
  group_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE user_group(
  user_id INT(11) NOT NULL ,
  group_id INT(11) NOT NULL ,
  UNIQUE (user_id, group_id),
  FOREIGN KEY (user_id) REFERENCES users(user_id),
  FOREIGN KEY (group_id) REFERENCES groups(group_id)
);

## need to add user with role admin to db
INSERT INTO groups VALUES (1, 'ADMIN');
INSERT INTO users (first_name, last_name, username, password, email, birthday, active) VALUES
  ('admin', 'admin', 'admin', '$2a$10$p2A3bfnmgDDbCUtfPEYO3Om3uOf2UY6kfEC1djhi5Yve7hg3.30/K', 'admin@mail.ru', str_to_date('07-25-2012','%m-%d-%Y'), 1);
INSERT INTO user_group VALUES (1,1);