CREATE TABLE IF NOT EXISTS servidor_temporario
(
    pes_id int4 NOT NULL,
    st_data_admissao date NOT NULL,
    st_data_demissao date NULL,
    CONSTRAINT servidor_temporario_fk FOREIGN KEY (pes_id)
        REFERENCES pessoa (pes_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

INSERT INTO servidor_temporario (pes_id,st_data_admissao,st_data_demissao) values (1,'2008-03-08','2022-03-08');
INSERT INTO servidor_temporario (pes_id,st_data_admissao,st_data_demissao) values (2,'2008-05-10','2022-05-10');
INSERT INTO servidor_temporario (pes_id,st_data_admissao,st_data_demissao) values (3,'2008-02-05','2022-02-05');

