CREATE TABLE escola (
  id NUMERIC(19, 0) IDENTITY(1,1) PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  quantidadeAlunos INT NOT NULL
);