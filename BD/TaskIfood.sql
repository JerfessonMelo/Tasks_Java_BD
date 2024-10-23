CREATE TABLE endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(60),
    bairro VARCHAR(30),
    numero VARCHAR(10),
    tipo_endereco ENUM('casa', 'apartamento')
);
create table cliente (
	id int auto_increment primary key,
	nome varchar(60)not null,
	cpf varchar(14) unique,
    telefone varchar(15),
    data_nascimento date not null,
    id_endereco int,
    foreign key (id_endereco) references endereco(id)
);
create table categoria (
	id int auto_increment primary key,
    nome varchar(60) not null
);
create table produtos (
	id int auto_increment primary key,
    nome varchar(60) not null,
    codigo_barras varchar(20) unique,
    custo_produto float,
    descricao_produto text,
    categoria_produto int,
    foreign key (categoria_produto) references categoria(id)
);
create table promocao (
	id int auto_increment primary key,
	id_produto int,
    foreign key (id_produto) references produtos(id)
);
create table acompanhamento (
	id int auto_increment primary key,
    nome varchar(60) not null
);
create table produtoAcompanhamento (
	id int auto_increment primary key,
    custo_unitario float,
    quantidade int,
    acompanhamento_id int,
    produto_id int,
    foreign key (acompanhamento_id) references acompanhamento(id),
    foreign key (produto_id) references produtos(id)
);
create table clientePagamento (
	id int auto_increment primary key,
    formaDePagamento enum('pix','dinheiro','cartao')
);
create table restaurantePagamento (
	id int auto_increment primary key,
    formaDePagamentoAceita enum('pix','dinheiro','cartao')
);
create table historicoDePagamento (
	id int auto_increment primary key,
	formaDePagamento enum('pix','dinheiro','cartao'),
    cliente_id int,
    pagamento_id int,
	dataDePagamento date not null,
    foreign key (pagamento_id) references clientePagamento(id),
    foreign key (cliente_id) references cliente(id)
);
create table restaurante (
	id int auto_increment primary key,
	nome varchar(60) not null,
    cnpj varchar(60) not null,
    telefone varchar(15),
    id_pagamentoAceito int,
    foreign key (id_pagamento_aceito) references restaurante_pagamento(id)
);
create table restauranteProduto (
	id int auto_increment primary key,
	restaurante_id int,
    produto_id int,
    foreign key (restaurante_id) references restaurante(id),
    foreign key (produto_id) references produtos(id)
);
create table carrinho (
	id int auto_increment primary key,
    cliente_id int,
    foreign key (cliente_id) references cliente(id)
);
create table historicoDeEntregas (
	id int auto_increment primary key,
    entrega_id int,
    foreign key (entrega_id) references status_entrega(id)
);
create table produtoCarrinho (
	id int auto_increment primary key,
	quantidade int not null,
    carrinho_id int,
    restauranteProduto_id int,
    observacao varchar(60),
	foreign key (restauranteProduto_id) references restauranteProduto(id),
    foreign key (carrinho_id) references carrinho(id)
);
create table pedido (
	id int auto_increment primary key,
	id_endereco int,
    id_carrinho int,
    id_forma_pagamento int,
    foreign key (id_endereco) references endereco(id),
    foreign key (id_carrinho) references carrinho(id),
    foreign key (id_forma_pagamento) references cliente_pagamento(id)
);
create table statusEntrega (
	id int auto_increment primary key,
    status enum('preparo','saiu','entregue'),
    pedido_id int,
    data_de_entrega date not null,
    foreign key (pedido_id) references pedido(id)
);
