create database tecas;
use tecas;

create table users(
id MEDIUMINT NOT NULL AUTO_INCREMENT,
     nome varcharacter(50) not null,
	 cpf varchar(11) not null unique,
	 dtnc varchar(12) not null,
	 senha varchar(50) not null,
     PRIMARY KEY (id)
);

describe users;

insert into users(nome, cpf, dtnc, senha, perfil)
values ('Sarah Foster', '1234567891011', '26/11/2000', '1234','admin');
insert into users(nome, cpf, dtnc, senha)
values ('Andre Rocha', '12345678912', '06/09/1991', '1234');
insert into users(nome, cpf, dtnc, senha)
values ('teca', '1234', '06/09/1991', '1234');
insert into users(nome, cpf, dtnc, senha, perfil)
values ('teste', '123', '06/09/1991', '1234', "user");

select * from users;


create table clientes(
	id_cliente INT NOT NULL AUTO_INCREMENT,
     nome_cliente varcharacter(50) not null,
	 cpf_cliente varchar(11) not null,
     telefone_cliente varchar(11) not null,
     PRIMARY KEY (id_cliente)
);

select * from clientes;

create table os(
os INT primary key AUTO_INCREMENT,
     dataOS timestamp default current_timestamp,
     tipo varchar(15) not null,
     situacao varchar(20) not null,
     imei1 varchar(50) not null,
     imei2 varchar(50) not null,
     entrega varchar(500) not null,
     senha varchar(20) not null,
	 equipamento varchar(150) not null,
     defeito varchar(150) not null,
     valor decimal(10,2),
     id_cliente int not null,
     foreign key(id_cliente) references clientes(id_cliente)
);
alter table os add column outros varchar(500) not null after entrega;
describe os;
insert into os(equipamento, defeito, servico, tecnico, valor, id_cliente)
values ('J6', 'Conector de carga com mau contato, entrada de fone com mau contato e tela trincada', 'Troca do conector de carga, limpeza de conector do fone e troca da tela', 'Tequinha Guga', '500.92', '3');

select * from os;

select
O.os, equipamento, defeito, situacao, valor,
C.nome_cliente, telefone_cliente
from os as O
inner join clientes as C
on (O.id_cliente = C.id_cliente);

alter table users add column perfil varchar(20) not null;
update users set perfil="admin" where id=1;
update users set perfil="user" where id=2;
update users set perfil="admin" where id=3;

select * from clientes order by nome_cliente;

-- relatório 2 (serviços)
select 
os.os as OS,date_format(os.dataOS,'%d/%m/%Y') as data,
os.equipamento,os.defeito as defeito, os.situacao as status, os.valor,
clientes.nome_cliente as cliente,clientes.telefone_cliente as fone
from os inner join clientes on os.id_cliente = clientes.id_cliente
order by situacao;

-- impressão da OS
select 
os.os as OS,date_format(os.dataOS,'%d/%m/%Y - %H:%i') as data,
os.tipo as tipo_OS,os.equipamento,os.defeito,os.valor,
clientes.nome_cliente as cliente,clientes.telefone_cliente as fone
from os inner join clientes on os.id_cliente = clientes.id_cliente
where os=1;
