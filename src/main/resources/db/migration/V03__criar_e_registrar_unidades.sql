CREATE SEQUENCE unidade_sequence
  start 1
  increment 1;

CREATE TABLE unidade (
	unid_id int4 NOT NULL DEFAULT nextval('unidade_sequence'),
	unid_nome varchar(200) NOT NULL,
	unid_sigla varchar(20) NOT NULL,
	CONSTRAINT unidade_pk PRIMARY KEY (unid_id)
);


INSERT INTO unidade (unid_nome,unid_sigla) values ('Coordenadoria de Tecnologia','CT');
INSERT INTO unidade (unid_nome,unid_sigla) values ('Gerência de Investigação','GI');
INSERT INTO unidade (unid_nome,unid_sigla) values ('Superintêndencia de Inteligência','SI');

