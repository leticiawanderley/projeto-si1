# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table disciplina (
  nome_da_disciplina        varchar(255),
  numero_de_creditos        integer,
  dificuldade_da_disciplina integer)
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists disciplina;

SET REFERENTIAL_INTEGRITY TRUE;

