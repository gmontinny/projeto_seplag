CREATE SEQUENCE cidade_sequence
  start 1
  increment 1;


CREATE TABLE cidade (
	cid_id int4 NOT NULL DEFAULT nextval('cidade_sequence'),
	cid_nome varchar(200) NOT NULL,
	cid_uf varchar(2) NOT NULL,
	CONSTRAINT cidade_pk PRIMARY KEY (cid_id)
);

INSERT INTO cidade (cid_nome,cid_uf) values ('Cuiabá','MT');
INSERT INTO cidade (cid_nome,cid_uf) values ('Várzea Grande','MT');
INSERT INTO cidade (cid_nome,cid_uf) values ('Rondonópolis','MT');
INSERT INTO cidade (cid_nome,cid_uf) values ('Sinop','MT');
INSERT INTO cidade (cid_nome,cid_uf) values ('Tangará da Serra','MT');
INSERT INTO cidade (cid_nome,cid_uf) values ('Cáceres','MT');
INSERT INTO cidade (cid_nome,cid_uf) values ('Sorriso','MT');
INSERT INTO cidade (cid_nome,cid_uf) values ('Lucas do Rio Verde','MT');
INSERT INTO cidade (cid_nome,cid_uf) values ('Primavera do Leste','MT');
INSERT INTO cidade (cid_nome,cid_uf) values ('Barra do Garças','MT');
