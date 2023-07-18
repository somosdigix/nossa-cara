CREATE TABLE refeitorio (
  id BIGINT IDENTITY(1,1) PRIMARY KEY,
  numeroDispositivo VARCHAR(255) NOT NULL,
  nome VARCHAR(255) NOT NULL,
  escola_id NUMERIC(19, 0) NOT NULL,
  FOREIGN KEY (escola_id) REFERENCES escola(id)
);