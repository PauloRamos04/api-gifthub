CREATE TABLE IF NOT EXISTS produto (
    id_produto SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco NUMERIC(10, 2) NOT NULL,
    estoque INT NOT NULL,
    categoria VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS endereco (
  id_endereco SERIAL PRIMARY KEY,
  rua VARCHAR(50) NOT NULL,
  cidade VARCHAR(50) NOT NULL,
  estado VARCHAR(50) NOT NULL,
  cep VARCHAR(20) NOT NULL,
  pais VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
  id_users SERIAL PRIMARY KEY,
  nome_completo VARCHAR(100) NOT NULL,
  login VARCHAR(100) NOT NULL,
  email VARCHAR(30) NOT NULL,
  cpf VARCHAR(14) NOT NULL,
  dt_nasc DATE,
  endereco_id INT,  -- Adicionando a coluna para referenciar o ID do endereço
  cargo TEXT NOT NULL,
  senha VARCHAR(100),
  CONSTRAINT fk_users_endereco FOREIGN KEY (endereco_id) REFERENCES endereco (id_endereco)
);



CREATE TABLE IF NOT EXISTS carrinho(
    id_carrinho SERIAL PRIMARY KEY,
    id_users INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    CONSTRAINT fk_carrinho_users FOREIGN KEY (id_users) REFERENCES users (id_users),
    CONSTRAINT fk_carrinho_produto FOREIGN KEY (id_produto) REFERENCES produto (id_produto)
);


-- Alter column "cargo" in table "users" to smallint
ALTER TABLE users
ALTER COLUMN cargo TYPE smallint USING CASE WHEN cargo = 'admin' THEN 1 ELSE 0 END;

