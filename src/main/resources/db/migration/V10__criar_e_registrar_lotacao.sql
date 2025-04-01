CREATE SEQUENCE lotacao_sequence
  start 1
  increment 1;

CREATE TABLE IF NOT EXISTS lotacao
(
    lot_id int4 NOT NULL DEFAULT nextval('lotacao_sequence'),
    pes_id int4 NOT NULL,
    unid_id int4 NOT NULL,
    lot_data_lotacao date NOT NULL,
    lot_data_remocao date NULL,
    lot_portaria varchar(100) NOT NULL,    
    CONSTRAINT lotacao_pk PRIMARY KEY (lot_id),
    CONSTRAINT lotacao_fk FOREIGN KEY (pes_id)
        REFERENCES pessoa (pes_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT lotacao_fk_1 FOREIGN KEY (unid_id)
        REFERENCES unidade (unid_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

INSERT INTO lotacao (pes_id,unid_id,lot_data_lotacao,lot_data_remocao,lot_portaria) values (1,1,'2008-04-08','2022-04-08','AAA123');
INSERT INTO lotacao (pes_id,unid_id,lot_data_lotacao,lot_data_remocao,lot_portaria) values (2,2,'2008-04-09','2022-04-09','AAA124');
INSERT INTO lotacao (pes_id,unid_id,lot_data_lotacao,lot_data_remocao,lot_portaria) values (3,1,'2008-04-08','2022-04-08','AAA125');

