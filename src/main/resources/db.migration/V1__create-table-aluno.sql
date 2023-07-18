CREATE TABLE aluno (
  id BIGINT IDENTITY(1,1) PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  turma VARCHAR(255) NOT NULL,
  turno VARCHAR(255) NOT NULL,
  personId VARCHAR(255) NOT NULL,
  etapaDeEnsino_id BIGINT,
  escola_id NUMERIC(19, 0) NOT NULL,
  FOREIGN KEY (etapaDeEnsino_id) REFERENCES etapa_de_ensino(id),
  FOREIGN KEY (escola_id) REFERENCES escola(id)
);
