use deliveryFood;

CREATE TABLE provincia(
  id smallint NOT NULL AUTO_INCREMENT,
  nome varchar(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cidade (
  id smallint NOT NULL AUTO_INCREMENT,
  nome varchar(40) NOT NULL,
  provincia_id smallint NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT  FOREIGN KEY (provincia_id) REFERENCES provincia (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE cozinha (
  id bigint NOT NULL AUTO_INCREMENT,
  nome varchar(40) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE forma_pagamento(
  id tinyint NOT NULL AUTO_INCREMENT,
  descricao varchar(20) DEFAULT NULL,
  PRIMARY KEY (id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE permissao(
  id tinyint NOT NULL AUTO_INCREMENT,
  descricao varchar(30) DEFAULT NULL,
  nome varchar(30) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE restaurante (
  id bigint NOT NULL AUTO_INCREMENT,
  endereco_bairro varchar(50) DEFAULT NULL,
  endereco_municipio varchar(50) DEFAULT NULL,
  endereco_rua varchar(50) DEFAULT NULL,
  nome varchar(50) NOT NULL,
  taxa_entrega decimal(19,2) NOT NULL,
  cozinha_id bigint NOT NULL,
  endereco_cidade_id smallint DEFAULT NULL,

  PRIMARY KEY (id),
  CONSTRAINT FK_cozinha_id FOREIGN KEY (cozinha_id) REFERENCES cozinha(id),
  CONSTRAINT FK_cidade_id FOREIGN KEY (endereco_cidade_id) REFERENCES cidade (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE produto(
  id bigint NOT NULL AUTO_INCREMENT,
  ativo bit(1) NOT NULL,
  nome varchar(50) DEFAULT NULL,
  restaurante_id bigint DEFAULT NULL,

  PRIMARY KEY (id),
  KEY FK_restaurante_id (restaurante_id),
  CONSTRAINT FK_restaurante_id FOREIGN KEY (restaurante_id) REFERENCES restaurante(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE restaurante_forma_pagamento (
  restaurante_id bigint NOT NULL,
  forma_pagamento_id tinyint NOT NULL,

  CONSTRAINT FK_forma_pagamento_id FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento(id),
 CONSTRAINT FOREIGN KEY (restaurante_id) REFERENCES restaurante (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE restaurante_produtos (
  restaurante_id bigint NOT NULL,
  produtos_id bigint NOT NULL,
  
  CONSTRAINT FK_produtos_is FOREIGN KEY (produtos_id) REFERENCES produto(id),
  CONSTRAINT FOREIGN KEY (restaurante_id) REFERENCES restaurante (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario (
  id bigint NOT NULL AUTO_INCREMENT,
  email varchar(30) DEFAULT NULL,
  nome varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE grupo(
  id tinyint NOT NULL AUTO_INCREMENT,
  nome varchar(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_grupos (
  usuario_id bigint NOT NULL,
  grupos_id tinyint NOT NULL,

  CONSTRAINT FK_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuario (id),
  CONSTRAINT FK_grupo_id FOREIGN KEY (grupos_id) REFERENCES grupo(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE grupo_permissao (
  grupo_id tinyint NOT NULL,
  permissao_id tinyint NOT NULL,
  
  CONSTRAINT  FOREIGN KEY (permissao_id) REFERENCES permissao (id),
  CONSTRAINT FOREIGN KEY (grupo_id) REFERENCES grupo(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;