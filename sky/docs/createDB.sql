
create database sky;

create user 'sky'@'%' identified by '123456';

grant all privileges on sky.* to 'sky'@'%';

flush privileges;
