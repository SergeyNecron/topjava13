DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (description, calories, user_id) VALUES
  ('Завтрак', 500, 100000),
  ('Обед', 500, 100000),
  ('Ужин', 800, 100000),
  ('Завтрак', 500, 100000),
  ('Обед', 500, 100000),
  ('Ужин', 500, 100000),
  ('Завтрак', 300, 100000);

INSERT INTO meals (datetime, description, calories, user_id) VALUES
  ('2015-08-01 22:31:24','Завтрак', 500, 100001),
  ('2017-08-01 22:31:24.196237','Обед', 500, 100001),
  ('2017-08-01 22:31:24.196237','Ужин', 500, 100001),
  ('2016-09-01 22:31:24','test', 300, 100001);