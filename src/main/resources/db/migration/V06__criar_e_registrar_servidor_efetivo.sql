CREATE TABLE IF NOT EXISTS servidor_efetivo
(
    pes_id int4 NOT NULL,
    se_matricula varchar(20) NOT NULL,
    CONSTRAINT servidor_efetivo_fk FOREIGN KEY (pes_id)
        REFERENCES pessoa (pes_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

INSERT INTO servidor_efetivo (pes_id,se_matricula) values (1,'000001');
INSERT INTO servidor_efetivo (pes_id,se_matricula) values (2,'000002');
INSERT INTO servidor_efetivo (pes_id,se_matricula) values (3,'000003');

