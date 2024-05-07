SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

CREATE DATABASE IF NOT EXISTS gifthub;

USE gifthub;

CREATE TABLE IF NOT EXISTS `carrinho` (
  `id_carrinho` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  PRIMARY KEY (`id_carrinho`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_produto` (`id_produto`),
  CONSTRAINT `fk_carrinho_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `fk_carrinho_produto` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE IF NOT EXISTS `categoria` (
  `id_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `descricao` text NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE IF NOT EXISTS `cliente` (
  `id_cliente` int(11) AUTO_INCREMENT,
  `nome_completo` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `dt_nasc` date DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE IF NOT EXISTS `endereco` (
  `id_endereco` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `rua` varchar(50) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `cep` varchar(20) NOT NULL,
  `pais` varchar(100) NOT NULL,
  PRIMARY KEY (`id_endereco`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `fk_endereco_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE IF NOT EXISTS `entrega` (
  `id_entrega` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `id_endereco` int(11) NOT NULL,
  `tipo_entrega` varchar(100) NOT NULL,
  `taxa` float(4,2) NOT NULL,
  PRIMARY KEY (`id_entrega`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_produto` (`id_produto`),
  KEY `id_endereco` (`id_endereco`),
  CONSTRAINT `fk_entrega_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `fk_entrega_produto` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`),
  CONSTRAINT `fk_entrega_endereco` FOREIGN KEY (`id_endereco`) REFERENCES `endereco` (`id_endereco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
