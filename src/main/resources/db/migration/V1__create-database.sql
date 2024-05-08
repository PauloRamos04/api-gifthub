CREATE TABLE IF NOT EXISTS carrinho (
  id_carrinho SERIAL PRIMARY KEY,
  id_cliente INT NOT NULL,
  id_produto INT NOT NULL,
  quantidade INT NOT NULL,
  CONSTRAINT fk_carrinho_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente),
  CONSTRAINT fk_carrinho_produto FOREIGN KEY (id_produto) REFERENCES produto (id_produto)
);

CREATE TABLE IF NOT EXISTS categoria (
  id_categoria SERIAL PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  descricao TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente (
  id_cliente SERIAL PRIMARY KEY,
  nome_completo VARCHAR(100) NOT NULL,
  email VARCHAR(30) NOT NULL,
  cpf VARCHAR(14) NOT NULL,
  dt_nasc DATE,
  senha VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS endereco (
  id_endereco SERIAL PRIMARY KEY,
  id_cliente INT NOT NULL,
  rua VARCHAR(50) NOT NULL,
  cidade VARCHAR(50) NOT NULL,
  estado VARCHAR(50) NOT NULL,
  cep VARCHAR(20) NOT NULL,
  pais VARCHAR(100) NOT NULL,
  CONSTRAINT fk_endereco_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
);

CREATE TABLE IF NOT EXISTS entrega (
  id_entrega SERIAL PRIMARY KEY,
  id_cliente INT NOT NULL,
  id_produto INT NOT NULL,
  id_endereco INT NOT NULL,
  tipo_entrega VARCHAR(100) NOT NULL,
  taxa DECIMAL(6,2) NOT NULL,
  CONSTRAINT fk_entrega_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente),
  CONSTRAINT fk_entrega_produto FOREIGN KEY (id_produto) REFERENCES produto (id_produto),
  CONSTRAINT fk_entrega_endereco FOREIGN KEY (id_endereco) REFERENCES endereco (id_endereco)
);
