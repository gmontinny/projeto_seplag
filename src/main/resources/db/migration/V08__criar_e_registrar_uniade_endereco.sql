CREATE TABLE IF NOT EXISTS unidade_endereco
(
    unid_id int4 NOT NULL,
    end_id int4 NOT NULL,
    CONSTRAINT unidade_endereco_fk FOREIGN KEY (unid_id)
        REFERENCES unidade (unid_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT unidade_endereco_fk_1 FOREIGN KEY (end_id)
        REFERENCES endereco (end_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

INSERT INTO unidade_endereco (unid_id,end_id) values (1,1);
INSERT INTO unidade_endereco (unid_id,end_id) values (2,2);
INSERT INTO unidade_endereco (unid_id,end_id) values (1,3);

