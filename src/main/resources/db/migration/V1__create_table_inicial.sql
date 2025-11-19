create table node(
id  bigint auto_increment primary key,
historia_id bigint,
nome varchar(100),
conteudo text,
maximo_ativacoes int);

create table link(
id bigint auto_increment primary key,
node_origem_id bigint,
node_destino_id bigint);

alter table link
add constraint link_to_node_no_origem
foreign key(node_origem_id)
references node(id);
alter table link
add constraint link_to_node_no_destino
foreign key(node_destino_id)
references node(id);

create table historia(
id bigint auto_increment primary key,
titulo varchar(100),
node_inicial_id bigint);

alter table historia
add constraint historia_to_node_node_inicial
foreign key(node_inicial_id)
references node(id);

alter table Node
add constraint node_to_historia_historia_id
foreign key(historia_id)
references historia(id);

insert into historia(titulo) values("os três porquinhos");

insert into node(historia_id, nome, conteudo, maximo_ativacoes) values
(1, 'inicio', 'Lobo: Os porquinhos estão de mudança!', 3),
(1, 'qual porco', 'Lobo: Qual porquinho eu vou pegar agora?', 3),
(1, 'ir atrás do porquinho da casa de tijolos', 'Lobo: Uma casa de tijolos? Será que eu consigo derrubar isso?', 3),
(1, 'ir atrás do porquinho da casa de palha', 'Lobo: “Uma casa de palha? Ridículo!”', 3),
(1, 'ir atrás do porquinho da casa de palha', 'Lobo: Uma casa de madeira? Talvez dê trabalho pra derrubar.', 3);

insert into link(node_origem_id, node_destino_id) values
(1,2),
(2,3),
(2,4),
(2,5);