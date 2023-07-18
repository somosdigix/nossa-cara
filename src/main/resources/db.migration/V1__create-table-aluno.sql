CREATE TABLE aluno (
  id BIGINT IDENTITY(1,1) PRIMARY KEY,
  nome VARCHAR(255),
  turma VARCHAR(255),
  turno VARCHAR(255),
  personId VARCHAR(255),
  etapaDeEnsino_id BIGINT,
  escola_id NUMERIC(19, 0),
  FOREIGN KEY (etapaDeEnsino_id) REFERENCES etapa_de_ensino(id),
  FOREIGN KEY (escola_id) REFERENCES escola(id)
);
