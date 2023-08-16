create database men character set utf8 collate utf8_unicode_ci;
create table PERSON(PERSON_ID int not null auto_increment, FIRST_NAME varchar(35) NOT NULL, LAST_NAME varchar(35) NOT NULL, unique(FIRST_NAME, LAST_NAME), primary key(PERSON_ID));
insert into PERSON(FIRST_NAME, LAST_NAME) values('Mauricio','Leal');
insert into PERSON(FIRST_NAME, LAST_NAME) values('Nadia','Ulanova');
