CREATE TABLE local_de_entrada (
  id  NUMERIC(19, 0) IDENTITY(1,1) PRIMARY KEY,
  numeroDispositivo VARCHAR(255) NOT NULL,
  nome VARCHAR(255) NOT NULL,
  escola_id NUMERIC(19, 0),
  FOREIGN KEY (escola_id) REFERENCES escola(id)
);