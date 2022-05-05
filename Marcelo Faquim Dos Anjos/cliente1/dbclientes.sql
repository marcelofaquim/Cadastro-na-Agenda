create database dbclientes;
show databases;

use dbclientes;

create table clientes(
idcon int primary key auto_increment,
nome varchar(50) not null,
fone varchar(50) not null,
email varchar(50) not null,
endereco varchar(50) not null,
bairro varchar (50) not null,
obs blob
);

show tables;

describe clientes;

/*CRUD CREATE*/

insert into clientes(nome,fone,email,endereco,bairro) values("testenome","testefone","emailTeste","enderecoTeste", "testebairro");

/*CRUD SELECT ALL*/
select*from clientes;


