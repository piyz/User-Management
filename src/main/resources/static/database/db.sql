CREATE TABLE users(
  user_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  first_name VARCHAR(255) NOT NULL ,
  last_name VARCHAR(255) NOT NULL ,
  username VARCHAR(255) NOT NULL ,
  password VARCHAR(255) NOT NULL ,
  email VARCHAR(255) NOT NULL ,
  birthday DATE NOT NULL ,
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