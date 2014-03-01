# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table aluno (
  id                        bigint not null,
  constraint pk_aluno primary key (id))
;

create table disciplina (
  nome_da_disciplina        varchar(255),
  numero_de_creditos        integer,
  dificuldade_da_disciplina integer)
;

create sequence aluno_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists aluno;

drop table if exists disciplina;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists aluno_seq;

