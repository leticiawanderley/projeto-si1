# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table aluno)
;

create table disciplina (
  id                        bigint not null,
  nome_da_disciplina        varchar(255),
  numero_de_creditos        integer,
  dificuldade               integer,
  constraint pk_disciplina primary key (id))
;

create sequence disciplina_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists aluno;

drop table if exists disciplina;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists disciplina_seq;

