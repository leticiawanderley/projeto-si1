# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table aluno (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  plano_do_aluno_id         bigint,
  constraint pk_aluno primary key (email))
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
  numero_do_periodo         integer,
  dificuldade_do_periodo    integer,
  validador_do_periodo      integer,
  constraint ck_periodo_validador_do_periodo check (validador_do_periodo in (0,1,2)),
  constraint pk_periodo primary key (id))
;

create table plano (
  id                        bigint not null,
  periodo_atual             integer,
  constraint pk_plano primary key (id))
;

create table usuario (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_usuario primary key (email))
;


create table listaDePreRequisitos (
  disciplina_codigo              bigint not null,
  requisito_codigo               bigint not null,
  constraint pk_listaDePreRequisitos primary key (disciplina_codigo, requisito_codigo))
;

create table periodo_disciplina (
  periodo_id                     bigint not null,
  disciplina_id                  bigint not null,
  constraint pk_periodo_disciplina primary key (periodo_id, disciplina_id))
;

create table plano_periodo (
  plano_id                       bigint not null,
  periodo_id                     bigint not null,
  constraint pk_plano_periodo primary key (plano_id, periodo_id))
;
create sequence aluno_seq;

create sequence disciplina_seq;

create sequence periodo_seq;

create sequence plano_seq;

create sequence usuario_seq;

alter table aluno add constraint fk_aluno_planoDoAluno_1 foreign key (plano_do_aluno_id) references plano (id) on delete restrict on update restrict;
create index ix_aluno_planoDoAluno_1 on aluno (plano_do_aluno_id);



alter table listaDePreRequisitos add constraint fk_listaDePreRequisitos_disci_01 foreign key (disciplina_codigo) references disciplina (id) on delete restrict on update restrict;

alter table listaDePreRequisitos add constraint fk_listaDePreRequisitos_disci_02 foreign key (requisito_codigo) references disciplina (id) on delete restrict on update restrict;

alter table periodo_disciplina add constraint fk_periodo_disciplina_periodo_01 foreign key (periodo_id) references periodo (id) on delete restrict on update restrict;

alter table periodo_disciplina add constraint fk_periodo_disciplina_discipl_02 foreign key (disciplina_id) references disciplina (id) on delete restrict on update restrict;

alter table plano_periodo add constraint fk_plano_periodo_plano_01 foreign key (plano_id) references plano (id) on delete restrict on update restrict;

alter table plano_periodo add constraint fk_plano_periodo_periodo_02 foreign key (periodo_id) references periodo (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists aluno;

drop table if exists disciplina;

drop table if exists listaDePreRequisitos;

drop table if exists periodo;

drop table if exists periodo_disciplina;

drop table if exists plano;

drop table if exists plano_periodo;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists aluno_seq;

drop sequence if exists disciplina_seq;

drop sequence if exists periodo_seq;

drop sequence if exists plano_seq;

drop sequence if exists usuario_seq;

