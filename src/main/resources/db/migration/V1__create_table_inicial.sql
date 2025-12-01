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

-- relatorio historia
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


-- relatorio
-- tabela auditoria
create table audit_log (
    id              bigint auto_increment primary key,
    tabela          varchar(32) not null,       -- 'node', 'link', 'historia'
    operacao        varchar(16) not null,       -- 'INSERT', 'UPDATE', 'DELETE'
    registro_id     bigint not null,            -- ID do item modificado
    dados_antes     json null,                  -- estado anterior (quando houver)
    dados_depois    json null,                  -- estado depois (quando houver)
    data_evento     datetime default now()
);

delimiter $$
create procedure mostra_links_por_historia(r_historia_id bigint)
begin
select l.id, l.node_origem_id, l.node_destino_id from link l join node n on l.node_origem_id = n.id where
n.historia_id = r_historia_id;
end$$
delimiter ;

-- trigger delete node
create trigger tgr_node_delete
before delete on node
for each row
insert into audit_log(tabela, operacao, registro_id, dados_antes)
values ('node', 'DELETE', old.id, json_object(
    'id', old.id,
    'historia_id', old.historia_id,
    'titulo', old.nome
));

-- trigger update node
create trigger tgr_node_update
before update on node
for each row
insert into audit_log(tabela, operacao, registro_id, dados_antes, dados_depois)
values ('node', 'UPDATE', old.id,
        json_object('id', old.id, 'titulo', old.nome),
        json_object('id', new.id, 'titulo', new.nome));

-- trigger insere node

create trigger tgr_node_insert
after insert on node
for each row
insert into audit_log(tabela, operacao, registro_id, dados_depois)
values ('node', 'INSERT', new.id, json_object(
    'id', new.id,
    'historia_id', new.historia_id,
    'titulo', new.nome
));

-- trigger insere link
delimiter $$
create trigger tgr_link_insert
after insert on link
for each row
begin
    insert into audit_log(tabela, operacao, registro_id, dados_depois)
    values (
        'link',
        'INSERT',
        new.id,
        json_object(
            'id', new.id,
            'node_origem_id', new.node_origem_id,
            'node_destino_id', new.node_destino_id
        )
    );
end$$
delimiter ;

-- trigger update link
delimiter $$
create trigger tgr_link_update
before update on link
for each row
begin
    insert into audit_log(tabela, operacao, registro_id, dados_antes, dados_depois)
    values (
        'link',
        'UPDATE',
        old.id,

        json_object(
            'id', old.id,
            'node_origem_id', old.node_origem_id,
            'node_destino_id', old.node_destino_id
        ),

        json_object(
            'id', new.id,
            'node_origem_id', new.node_origem_id,
            'node_destino_id', new.node_destino_id
        )
    );
end$$
delimiter ;

-- trigger delete link
delimiter $$
create trigger tgr_link_delete
before delete on link
for each row
begin
    insert into audit_log(tabela, operacao, registro_id, dados_antes)
    values (
        'link',
        'DELETE',
        old.id,
        json_object(
            'id', old.id,
            'node_origem_id', old.node_origem_id,
            'node_destino_id', old.node_destino_id
        )
    );
end$$
delimiter ;

-- trigger insert historia
delimiter $$
create trigger tgr_historia_insert
after insert on historia
for each row
begin
    insert into audit_log(tabela, operacao, registro_id, dados_depois)
    values (
        'historia',
        'INSERT',
        new.id,
        json_object(
            'id', new.id,
            'titulo', new.titulo,
            'node_inicial_id', new.node_inicial_id
        )
    );
end$$
delimiter ;

-- trigger update historia
delimiter $$
create trigger tgr_historia_update
before update on historia
for each row
begin
    insert into audit_log(tabela, operacao, registro_id, dados_antes, dados_depois)
    values (
        'historia',
        'UPDATE',
        old.id,

        json_object(
            'id', old.id,
            'titulo', old.titulo,
            'node_inicial_id', old.node_inicial_id
        ),

        json_object(
            'id', new.id,
            'titulo', new.titulo,
            'node_inicial_id', new.node_inicial_id
        )
    );
end$$
delimiter ;

-- trigger delete historia
delimiter $$
create trigger tgr_historia_delete
before delete on historia
for each row
begin
    insert into audit_log(tabela, operacao, registro_id, dados_antes)
    values (
        'historia',
        'DELETE',
        old.id,
        json_object(
            'id', old.id,
            'titulo', old.titulo,
            'node_inicial_id', old.node_inicial_id
        )
    );
end$$
delimiter ;



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

