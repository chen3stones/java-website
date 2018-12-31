create table user (
  uid int primary key auto_increment,
  uname char(20) not null,
  password char(64) not null,
  email char(64) not null,
  authority int
);

create table code(
  c_id int primary key auto_increment,
  c_mail varchar(32),
  c_last_time date
);

create table article(
  a_id int primary key,
  a_author_id int,
  a_title varchar(32),
  a_text mediumtext
  foreign key(a_author_id)
  references user(uid)
);