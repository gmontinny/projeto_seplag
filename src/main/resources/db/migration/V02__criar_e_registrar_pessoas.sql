CREATE SEQUENCE pessoa_sequence
  start 1
  increment 1;


CREATE TABLE pessoa (
	pes_id int4 NOT NULL DEFAULT nextval('pessoa_sequence'),
	pes_nome varchar(200) NOT NULL,
	pes_data_nascimento date NOT NULL,
	pes_sexo varchar(9) NOT NULL,
	pes_mae varchar(200) NULL,
	pes_pai varchar(200) NULL,
	CONSTRAINT pessoa_pk PRIMARY KEY (pes_id)
);


INSERT INTO pessoa (pes_nome,pes_data_nascimento,pes_sexo,pes_mae,pes_pai) values ('Giovanny','1976-04-13','M','Marlucia','José');
INSERT INTO pessoa (pes_nome,pes_data_nascimento,pes_sexo,pes_mae,pes_pai) values ('Giulyanno','1986-03-23','M','Carla','João');
INSERT INTO pessoa (pes_nome,pes_data_nascimento,pes_sexo,pes_mae,pes_pai) values ('Francisca','1980-05-16','F','Fatima','André');
INSERT INTO pessoa (pes_nome,pes_data_nascimento,pes_sexo,pes_mae,pes_pai) values ('Maria','1977-08-05','F','Lurdez','Antonio');
INSERT INTO pessoa (pes_nome,pes_data_nascimento,pes_sexo,pes_mae,pes_pai) values ('Carlos','1978-08-21','M','Claudia','Fernando');
