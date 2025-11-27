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
references node(id)
on delete cascade;

alter table link
add constraint link_to_node_no_destino
foreign key(node_destino_id)
references node(id)
on delete cascade;

create table historia(
id bigint auto_increment primary key,
titulo varchar(100),
node_inicial_id bigint);

alter table historia
add constraint historia_to_node_node_inicial
foreign key(node_inicial_id)
references node(id)
ON DELETE set null;

alter table Node
add constraint node_to_historia_historia_id
foreign key(historia_id)
references historia(id)
ON DELETE CASCADE;

DELIMITER $$

CREATE PROCEDURE mostrar_node_sem_links(IN v_historia_id BIGINT)
BEGIN
    SELECT *
    FROM node n
    WHERE NOT EXISTS (
              SELECT 1
              FROM link l
              WHERE l.node_origem_id = n.id
          )
      AND NOT EXISTS (
              SELECT 1
              FROM link l
              WHERE l.node_destino_id = n.id
          )
      AND n.historia_id = v_historia_id;
END $$

DELIMITER ;

create or replace view vw_relatorio_historia as
select
    h.id as historia_id,
    h.titulo,

    -- total de nodes
    count(n.id) as total_nodes,

    -- total de links
    (select count(*)
     from link l
     join node n2 on n2.id = l.node_origem_id
     where n2.historia_id = h.id) as total_links,

    -- nós sem links
    sum(
        case
            when not exists (
                select 1 from link l where l.node_origem_id = n.id
            )
            and not exists (
                select 1 from link l where l.node_destino_id = n.id
            )
            then 1 else 0
        end
    ) as nodes_sem_links
from historia h
left join node n on n.historia_id = h.id
group by h.id;

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

