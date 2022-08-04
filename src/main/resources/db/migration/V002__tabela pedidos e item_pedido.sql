CREATE TABLE pedido(
id BIGINT NOT NULL AUTO_INCREMENT, 
usuario_id BIGINT not NULL,

taxa_entrega DECIMAL(19,2) NULL,
valor_total  DECIMAL(19,2) NOT NULL,
sub_total DECIMAL(19,2) NOT NULL,

data_comfirmacao TIMESTAMP NULL,
data_entrega TIMESTAMP NULL,
data_cancelamento TIMESTAMP NULL,
data_criacao TIMESTAMP NULL, 

status_pedido VARCHAR(15) NOT NULL DEFAULT 'CRIADO',

endereco_bairro varchar(50) not NULL,
endereco_municipio varchar(50) not NULL,
endereco_rua varchar(50) not NULL,
endereco_cidade_id smallint not NULL,
restaurante_id BIGINT NOT NULL,
forma_pagamento_id tinyint not null,

CONSTRAINT FOREIGN KEY(usuario_id) REFERENCES usuario(id),   
CONSTRAINT FOREIGN KEY(forma_pagamento_id) REFERENCES forma_pagamento(id),  
CONSTRAINT  FOREIGN KEY(restaurante_id) REFERENCES restaurante(id),   
CONSTRAINT  FOREIGN KEY(endereco_cidade_id) REFERENCES cidade(id),
PRIMARY KEY(id)
);

CREATE TABLE item_pedido(
id  BIGINT NOT NULL AUTO_INCREMENT,
quantidade INT NOT NULL DEFAULT 1,
preco_unitario Decimal(19,2) NOT NULL ,
preco_total Decimal(19,2) NOT NULL ,
observacao VARCHAR (200) NULL,
produto_id BIGINT NOT NULL,
pedido_id BIGINT not null,
constraint FK_pedido_id foreign key(pedido_id) references pedido(id),
CONSTRAINT FK_produto_id FOREIGN KEY(produto_id) REFERENCES produto(id),
PRIMARY KEY(id)
);





