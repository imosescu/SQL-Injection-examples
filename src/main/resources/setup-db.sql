CREATE DATABASE IF NOT EXISTS inject_sql_test;

CREATE USER 'injection_user'@'localhost' IDENTIFIED BY 'injection_pass';
GRANT ALL PRIVILEGES ON inject_sql_test.* TO 'injection_user'@'localhost';
FLUSH PRIVILEGES;


CREATE TABLE `t_one` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(45) DEFAULT NULL,
  `value2` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
)

insert into t_one (value, value2)
values ('v11', 'v21');
insert into t_one (value, value2)
values ('v11', 'v22');
insert into t_one (value, value2)
values ('v11', 'v23');
insert into t_one (value, value2)
values ('v11', 'v24');
insert into t_one (value, value2)
values ('v12', 'v21');
insert into t_one (value, value2)
values ('v12', 'v22');
insert into t_one (value, value2)
values ('v12', 'v23');

