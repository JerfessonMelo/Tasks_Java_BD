create table Clientes(
id integer NOT NULL AUTO_INCREMENT primary key,
nome varchar(60) NOT NULL,
telefone varchar(14),
email varchar(60),
endereco varchar(200),
cpf varchar(11) unique
);
create table Filmes(
id integer NOT NULL AUTO_INCREMENT primary key,
titulo varchar(60),
genero varchar(30),
ano year,
diretor varchar(60),
duracao integer
);
create table Funcionarios(
id integer NOT NULL AUTO_INCREMENT primary key,
nome varchar(60) NOT NULL,
telefone varchar(14),
cargo varchar(30),
email varchar(60)
);
create table Aluguel(
id integer NOT NULL AUTO_INCREMENT primary key,
id_cliente integer,
id_filme integer,
id_funcionarios integer,
data_aluguel date,
data_devolucao date,
data_devolucao_efetiva date,
valor_aluguel float,
foreign key (id_cliente) references Clientes(id),
foreign key (id_filme) references Filmes(id),
foreign key (id_funcionarios) references Funcionarios(id)
);
create table Categoria(
id integer NOT NULL AUTO_INCREMENT primary key,
descricao varchar(300)
);
create table Filme_Categoria(
id_filme integer,
id_categoria integer,
foreign key (id_filme) references Filmes(id),
foreign key (id_categoria) references Categoria(id)
);


