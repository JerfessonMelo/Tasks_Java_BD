create table livros(
id integer NOT NULL AUTO_INCREMENT primary key,
titulo varchar(60),
autor varchar(100),
editora varchar(60),
ano_publicacao integer,
preco float
);
create table cliente(
id integer NOT NULL AUTO_INCREMENT primary key,
nome varchar(100) NOT NULL,
email varchar(60),
endereco varchar(200)
);
create table pedidos(
id integer NOT NULL AUTO_INCREMENT primary key,
id_cliente integer,
data_pedido varchar(10),
total float,
foreign key (id_cliente) references cliente(id)
);

