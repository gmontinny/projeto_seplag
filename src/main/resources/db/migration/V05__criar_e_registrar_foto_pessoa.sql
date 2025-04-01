CREATE SEQUENCE foto_pessoa_sequence
  start 1
  increment 1;


CREATE TABLE IF NOT EXISTS foto_pessoa
(
    fp_id int4 NOT NULL DEFAULT nextval('foto_pessoa_sequence'),
    pes_id int4 NOT NULL,
    fp_data date NOT NULL,
    fp_bucket varchar(50) NULL,
    fp_hash varchar(50) NULL,
    CONSTRAINT foto_pessoa_pk PRIMARY KEY (fp_id),
    CONSTRAINT foto_pessoa_fk FOREIGN KEY (pes_id)
        REFERENCES pessoa (pes_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

INSERT INTO foto_pessoa (pes_id,fp_data,fp_bucket,fp_hash) values (1,'2022-05-05','servidor','AWS4-HMAC-SHA256&X-Amz-');
INSERT INTO foto_pessoa (pes_id,fp_data,fp_bucket,fp_hash) values (2,'2022-03-02','servidor','AWS4-HMAC-SHA256&X-Amz-');
INSERT INTO foto_pessoa (pes_id,fp_data,fp_bucket,fp_hash) values (3,'2022-02-01','servidor','AWS4-HMAC-SHA256&X-Amz-');

