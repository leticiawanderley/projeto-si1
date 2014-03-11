# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table aluno (
  id                        bigint not null,
  name                      varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_aluno primary key (id))
;

create table disciplina (
  id                        bigint not null,
  nome_da_disciplina        varchar(255),
  numero_de_creditos        integer,
  dificuldade_da_disciplina integer,
  constraint pk_disciplina primary key (id))
;

create table periodo (
  id                        bigint not null,
  periodo                   integer,
  dificuldade_do_periodo    integer,
  constraint pk_periodo primary key (id))
;

create table user (
  id                        bigint not null,
  name                      varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

create sequence aluno_seq;

create sequence disciplina_seq;

create sequence periodo_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists aluno;

drop table if exists disciplina;

drop table if exists periodo;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists aluno_seq;

drop sequence if exists disciplina_seq;

drop sequence if exists periodo_seq;

drop sequence if exists user_seq;

