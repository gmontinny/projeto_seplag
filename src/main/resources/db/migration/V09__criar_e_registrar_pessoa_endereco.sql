CREATE TABLE IF NOT EXISTS pessoa_endereco
(
    pes_id int4 NOT NULL,
    end_id int4 NOT NULL,
    CONSTRAINT pessoa_endereco_fk FOREIGN KEY (pes_id)
        REFERENCES pessoa (pes_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT pessoa_endereco_fk_1 FOREIGN KEY (end_id)
        REFERENCES endereco (end_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

INSERT INTO pessoa_endereco (pes_id,end_id) values (1,1);
INSERT INTO pessoa_endereco (pes_id,end_id) values (2,2);
INSERT INTO pessoa_endereco (pes_id,end_id) values (3,3);

