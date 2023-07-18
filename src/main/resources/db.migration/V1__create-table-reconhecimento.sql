CREATE TABLE reconhecimento (
  id  NUMERIC(19, 0) IDENTITY(1,1) PRIMARY KEY,
  deviceKey VARCHAR(255),
  personId VARCHAR(255),
  dataDeCriacao DATETIME,
  time NUMERIC(19, 0),
  ip VARCHAR(255),
  type VARCHAR(255),
  path VARCHAR(255)
);