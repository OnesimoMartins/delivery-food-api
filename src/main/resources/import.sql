insert into cozinha(nome) value ('Chinesa')
insert into cozinha(nome) value ('Italiana')
insert into cozinha(nome) value ('Francesa')

insert into forma_pagamento(descricao) value('Dinheiro')
insert into forma_pagamento(descricao) value('Cartão de crédito')
insert into forma_pagamento(descricao) value('Cartão de débito')

insert into provincia(nome) value('Malange')
insert into provincia(nome) value('cuanza-norte')

insert into cidade(nome,provincia_id) value('Dondo',2)
insert into cidade(nome,provincia_id) value('Cacuso',1)

insert into restaurante(nome,taxa_entrega,cozinha_id) value('são joão',2.000,1)
insert into restaurante(nome,taxa_entrega,cozinha_id) value('The cooker',1.500,3)
insert into restaurante(nome,taxa_entrega,cozinha_id) value('sabores',18.00,2)
insert into restaurante(nome,taxa_entrega,cozinha_id,endereco_bairro,endereco_municipio,endereco_rua,endereco_cidade_id) value('sabores da banda',3.00,2,'Maxinde','Malange','rua 15',1)


insert into restaurante_forma_pagamento(restaurante_id,forma_pagamento_id) values(1,1),(1,2),(1,3),(2,1),(3,2),(6,1);