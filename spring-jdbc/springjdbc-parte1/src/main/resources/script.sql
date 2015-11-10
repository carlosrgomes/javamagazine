DROP TABLE IF EXISTS PESSOA;
CREATE TABLE PESSOA(ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL, NOME VARCHAR(500), EMAIL VARCHAR(500));
DROP TABLE IF EXISTS TELEFONE;
CREATE TABLE TELEFONE(ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL, DDD VARCHAR(2), NUMERO VARCHAR(9), TIPO VARCHAR(10) ,ID_PESSOA INT , FOREIGN KEY(ID_PESSOA) REFERENCES PESSOA(ID));
