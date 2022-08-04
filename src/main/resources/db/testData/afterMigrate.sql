
set foreign_key_checks=0;
delete from cozinha;
delete from restaurante;
delete from provincia;
delete from cidade;
delete from forma_pagamento;
delete from usuario;
delete from permissao;
delete from produto;
delete from grupo;
delete from usuario_grupos;
delete from item_pedido;
delete from pedido;
delete from restaurante_forma_pagamento;

set foreign_key_checks=1;
alter table cozinha auto_increment=1;
alter table restaurante auto_increment=1;
alter table provincia auto_increment=1;
alter table cidade auto_increment=1;
alter table forma_pagamento auto_increment=1;
alter table usuario auto_increment=1;
alter table permissao auto_increment=1;
alter table produto auto_increment=1;
alter table grupo auto_increment=1;

insert into cozinha(nome) value ('Chinesa');
insert into cozinha(nome) value ('Italiana');
insert into cozinha(nome) value ('Francesa');
insert into cozinha(nome) value ('Russa');

insert into forma_pagamento(descricao) value('Dinheiro');
insert into forma_pagamento(descricao) value('Cartão de crédito');
insert into forma_pagamento(descricao) value('Cartão de débito');

insert into provincia(nome) value('Malange');
insert into provincia(nome) value('Cuanza-norte');
insert into provincia(nome) value('Luanda');
insert into provincia(nome) value('Huíla');

insert into cidade(nome,provincia_id) value('Dondo',2);
insert into cidade(nome,provincia_id) value('Cacuso',1);
insert into cidade(nome,provincia_id) value('Kilamba',3);
insert into cidade(nome,provincia_id) value('Lubango',4);
insert into cidade(nome,provincia_id) value('Sequele',1);

insert into restaurante(nome,taxa_entrega,cozinha_id,data_criacao,ultima_actualizacao) value('são joão',2000,1,utc_timestamp,utc_timestamp);
insert into restaurante(nome,taxa_entrega,cozinha_id,data_criacao,ultima_actualizacao) value('The cooker',1500,3,utc_timestamp,utc_timestamp);
insert into restaurante(nome,taxa_entrega,cozinha_id,data_criacao,ultima_actualizacao) value('sabores',0,2,utc_timestamp,utc_timestamp);
insert into restaurante(nome,taxa_entrega,cozinha_id,data_criacao,ultima_actualizacao) value('sabores da banda',0,3,utc_timestamp,utc_timestamp);
insert into restaurante(nome,taxa_entrega,cozinha_id,data_criacao,ultima_actualizacao) value('Grafenas',800,2,utc_timestamp,utc_timestamp);
insert into restaurante(nome,taxa_entrega,cozinha_id,endereco_bairro,endereco_municipio,endereco_rua,endereco_cidade_id ,data_criacao,ultima_actualizacao) value('Kapri',1200,2,'Maxinde','Malange','rua 15',1,utc_timestamp,utc_timestamp);


insert into restaurante_forma_pagamento(restaurante_id,forma_pagamento_id) values(1,1) ,(1,2) ,(1,3),(2,1),(3,2),(3,1),(4,2),(6,1);

INSERT INTO produto(nome,ativo,restaurante_id) VALUES ('Macarrão',TRUE ,1),('Mufete',true,2),('Bacalhau com natas',true,2),
('Caldeirada',true,2),('Carne de boi',true,3);

INSERT INTO grupo(nome) VALUES ('Gerente'),('Entregador'),('Cliente');

INSERT INTO permissao(nome,descricao) VALUES ('contratador','pode contratar novos func')
,('cancelar pedido','pode blblbk novos func');

insert into usuario(email,nome) values('leodalena34@Gmail.com','leodalena Silvestre'),('KatilaPedro@Hotmail.co.ao','Katila junior');

insert into usuario_grupos(usuario_id,grupos_id) values(1,3),(2,3);

insert into pedido (usuario_id, codigo, data_comfirmacao, data_criacao, data_entrega, 
endereco_bairro, endereco_cidade_id, endereco_municipio, endereco_rua, forma_pagamento_id, 
restaurante_id, status_pedido, sub_total, taxa_entrega, valor_total) 
VALUEs(1,'ye78wqyiw','2020-10-12','2020-10-12','2020-10-12',
'kikagil',1,'Mutamba','mabor',1,1,'COMFIRMADO',0,0,7000),

(2,'ye78wRTEw','2020-10-13','2020-10-13','2020-10-13',
'kikagil',1,'Mutamba','mabor',1,2,'ENTREGUE',0,500,1500),

(1,'yWQUIWw','2021-01-19','2021-01-19','2021-01-19',
'kikagil',1,'Mutamba','mabor',1,3,'ENTREGUE',0,1500,10000),

(2,'ywiroeryiw','2021-11-23','2021-11-23','2021-11-23',
'kikagil',1,'Mutamba','mabor',1,1,'ENTREGUE',0,700,5000),

(1,'yplkniw','2020-10-12','2020-10-12','2020-10-12',
'kikagil',1,'Mutamba','mabor',1,1,'ENTREGUE',0,1700,3000);
