CREATE SEQUENCE endereco_sequence
  start 1
  increment 1;

CREATE TABLE IF NOT EXISTS endereco
(
    end_id int4 NOT NULL DEFAULT nextval('endereco_sequence'),
    end_tipo_logradouro varchar(50) NOT NULL,
    end_logradouro varchar(200) NOT NULL,
    end_numero int4,
    end_bairro varchar(100) NOT NULL,
    cid_id int4 NOT NULL,
    CONSTRAINT endereco_pk PRIMARY KEY (end_id),
    CONSTRAINT endereco_fk FOREIGN KEY (cid_id)
        REFERENCES cidade (cid_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);  



INSERT INTO endereco (end_tipo_logradouro,end_logradouro,end_numero,end_bairro,cid_id) values ('Avenida','Rua Brasil',201,'Campo Velho',1);
INSERT INTO endereco (end_tipo_logradouro,end_logradouro,end_numero,end_bairro,cid_id) values ('Quadra','Rua Fortaleza',520,'Jardim Paulista',1);
INSERT INTO endereco (end_tipo_logradouro,end_logradouro,end_numero,end_bairro,cid_id) values ('Bloco','Santa Malia',125,'Manga',2);

