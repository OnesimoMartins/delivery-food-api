CREATE TABLE restaurante_usuario_responsavel(
restaurante_id  bigint NOT NULL,
usuario_id BIGINT NOT NULL,
 FOREIGN KEY ( restaurante_id) REFERENCES restaurante(id),
 FOREIGN KEY( usuario_id) REFERENCES usuario(id)

);
ALTER TABLE permissao drop column descricao  ;
alter table permissao add  descricao varchar(200) not null;