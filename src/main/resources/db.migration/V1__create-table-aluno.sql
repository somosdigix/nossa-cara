CREATE TABLE Aluno (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  turma VARCHAR(255) NOT NULL,
  turno VARCHAR(255) NOT NULL,
  personId VARCHAR(255) NOT NULL,
  etapaDeEnsino_id BIGINT,
  escola_id NUMERIC(19, 0) NOT NULL,
  FOREIGN KEY (etapaDeEnsino_id) REFERENCES EtapaDeEnsino(id),
  FOREIGN KEY (escola_id) REFERENCES Escola(id)
);
