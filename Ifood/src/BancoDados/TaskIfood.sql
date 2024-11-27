CREATE DATABASE ifood;
USE ifood;
-- drop database ifood;
CREATE TABLE endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(60),
    bairro VARCHAR(30),
    numero VARCHAR(10),
    cidade VARCHAR(60),
    estado VARCHAR(30),
    pontoReferencia VARCHAR(60),
    complemento VARCHAR(60),
    cep VARCHAR(11),
    tipo_endereco VARCHAR(30)
);

CREATE TABLE categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    descricao TEXT,
    tipo_categoria VARCHAR(30)
);

CREATE TABLE restaurante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    telefone VARCHAR(15),
    hora_funcionamento TIME,
    id_endereco_restaurante INT,
    is_retirada BOOLEAN,
    id_categoria INT,
    valorMinimoEntrega FLOAT,
    status INT,
    dataCreat TIME,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id),
    FOREIGN KEY (id_endereco_restaurante) REFERENCES endereco(id)
);

CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    preco FLOAT,
    id_categoria INT,
    id_restaurante INT,
    dataCreat TIME,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id),
    FOREIGN KEY (id_restaurante) REFERENCES restaurante(id)
);

CREATE TABLE promocao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60),
    codigo VARCHAR(30),
    valor FLOAT,
    validade DATE,
    tipo VARCHAR(30)
);

CREATE TABLE acompanhamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    descricao TEXT,
    valor FLOAT
);

CREATE TABLE produtoAcompanhamento (
    id_produto INT, 
    id_acompanhamento INT,
    PRIMARY KEY(id_produto, id_acompanhamento),
    FOREIGN KEY (id_produto) REFERENCES produto(id),
    FOREIGN KEY (id_acompanhamento) REFERENCES acompanhamento(id)
);

CREATE TABLE formaPagamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    formaPagamento VARCHAR(30)
);

CREATE TABLE restaurantePagamento (
    id_restaurante INT,
    id_forma_pagamento INT,
    PRIMARY KEY (id_restaurante, id_forma_pagamento),
    FOREIGN KEY (id_restaurante) REFERENCES restaurante(id),
    FOREIGN KEY (id_forma_pagamento) REFERENCES formaPagamento(id)
);

CREATE TABLE historicoDePagamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dataPagamento DATE,
    id_forma_pagamento INT,
    valor FLOAT,
    FOREIGN KEY (id_forma_pagamento) REFERENCES formaPagamento(id)
);

CREATE TABLE statusEntrega (
    id INT AUTO_INCREMENT PRIMARY KEY,
    statusEntrega ENUM ('Em produção', 'Em Rota Entrega', 'Entregue')
);

CREATE TABLE pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dataPedido DATE,
    id_restaurante INT,
    valor FLOAT,
    taxaEntrega FLOAT,
    id_promocao INT,
    id_forma_pagamento INT,
    observacoes TEXT,
    troco FLOAT,
    id_status_entrega INT,
    id_endereco INT,
    FOREIGN KEY (id_restaurante) REFERENCES restaurante(id),
    FOREIGN KEY (id_promocao) REFERENCES promocao(id),
    FOREIGN KEY (id_forma_pagamento) REFERENCES formaPagamento(id),
    FOREIGN KEY (id_status_entrega) REFERENCES statusEntrega(id),
    FOREIGN KEY (id_endereco) REFERENCES endereco(id)
);

CREATE TABLE historicoDeEntregas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_status_entrega INT,
    id_pedido INT,
    data_hora DATETIME, 
    FOREIGN KEY (id_status_entrega) REFERENCES statusEntrega(id),
    FOREIGN KEY (id_pedido) REFERENCES pedido(id)
);

CREATE TABLE avaliacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nota INT,
    id_pedido INT,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id)
);

CREATE TABLE pedidoProduto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT,
    id_produto INT,
    quantidade INT,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id),
    FOREIGN KEY (id_produto) REFERENCES produto(id)
);

CREATE TABLE pedidoProdutoAcompanhamento (
    id_pedido_produto INT,
    id_acompanhamento INT,
    PRIMARY KEY (id_pedido_produto, id_acompanhamento),
    FOREIGN KEY (id_pedido_produto) REFERENCES pedidoProduto(id),
    FOREIGN KEY (id_acompanhamento) REFERENCES acompanhamento(id)
);

INSERT INTO endereco (rua, bairro, numero, cidade, estado, pontoReferencia, complemento, cep, tipo_endereco) VALUES
('Rua das Flores', 'Centro', '123', 'São Paulo', 'SP', 'Próximo ao mercado', 'Apto 201', '01000-000', 'Residencial'),
('Avenida Paulista', 'Bela Vista', '456', 'São Paulo', 'SP', 'Em frente ao shopping', '', '01310-000', 'Comercial'),
('Rua Verde', 'Vila Nova', '78', 'Rio de Janeiro', 'RJ', 'Próximo à praça', '', '21000-000', 'Residencial'),
('Rua Azul', 'Jardim Paulista', '89', 'São Paulo', 'SP', 'Ao lado da farmácia', 'Casa', '01500-000', 'Residencial'),
('Avenida Laranja', 'Centro', '102', 'Campinas', 'SP', '', 'Sala 205', '13000-000', 'Comercial'),
('Rua Amarela', 'Santa Clara', '66', 'São Paulo', 'SP', 'Perto do colégio', '', '01100-000', 'Residencial'),
('Travessa Vermelha', 'Centro', '45', 'Curitiba', 'PR', '', 'Galpão', '80000-000', 'Comercial'),
('Rua Roxa', 'Morumbi', '50', 'São Paulo', 'SP', '', '', '05600-000', 'Residencial'),
('Rua Cinza', 'Bairro Alto', '12', 'Belo Horizonte', 'MG', '', '', '31000-000', 'Residencial'),
('Avenida Negra', 'Centro', '500', 'Fortaleza', 'CE', 'Próximo à praia', 'Cobertura', '60100-000', 'Residencial');

INSERT INTO categoria (nome, descricao, tipo_categoria) VALUES
('Lanches', 'Lanches rápidos e saborosos', 'Alimentação'),
('Bebidas', 'Bebidas geladas e refrescantes', 'Bebida'),
('Sobremesas', 'Doces e sobremesas variadas', 'Sobremesa'),
('Massas', 'Pratos com massas italianas', 'Alimentação'),
('Salgados', 'Salgados variados', 'Alimentação'),
('Petiscos', 'Opções de aperitivos', 'Alimentação'),
('Carnes', 'Pratos à base de carnes', 'Alimentação'),
('Veganos', 'Comida vegana e saudável', 'Alimentação'),
('Frutos do Mar', 'Pratos com frutos do mar', 'Alimentação'),
('Cafés', 'Cafés e bebidas quentes', 'Bebida');

INSERT INTO restaurante (nome, telefone, hora_funcionamento, id_endereco_restaurante, is_retirada, id_categoria, valorMinimoEntrega) VALUES
('Burguer Express', '11999999999', '10:00:00', null, TRUE, 1, 15.00),
('Drink Station', '11988888888', '11:00:00', null, FALSE, 2, 10.00),
('Massas Gourmet', '21977777777', '09:00:00', null, TRUE, 4, 20.00),
('Casa dos Doces', '31966666666', '12:00:00', null, FALSE, 3, 5.00),
('Petiscos & Cia', '41955555555', '16:00:00', null, TRUE, 6, 8.00),
('Veggie Delícia', '51944444444', '11:00:00', null, TRUE, 8, 12.00),
('Carne & Grelha', '61933333333', '18:00:00', null, FALSE, 7, 25.00),
('Mar e Sol', '71922222222', '10:00:00', null, FALSE, 9, 30.00),
('Salgados Mil', '81911111111', '08:00:00', null, TRUE, 5, 6.00),
('Café Mania', '91900000000', '07:00:00', null, FALSE, 10, 7.00);

INSERT INTO produto (nome, preco, id_categoria, id_restaurante) VALUES
('Hambúrguer Clássico', 22.00, 1, 1),
('Milkshake de Morango', 12.00, 2, 1),
('Cheeseburguer Bacon', 27.00, 1, 1),
('Refrigerante Lata', 6.00, 2, 1),
('Batata Frita Grande', 18.00, 6, 1),
('Salada Caesar', 20.00, 8, 1),
('Frango Empanado', 25.00, 7, 1),
('Torta de Maçã', 15.00, 3, 1),
('Café Cappuccino', 10.00, 10, 1),
('Água Mineral', 5.00, 2, 1),
('Pastel de Carne', 8.00, 5, 2),
('Pastel de Queijo', 8.50, 5, 2),
('Refrigerante 600ml', 7.00, 2, 2),
('Porção de Pastéis', 25.00, 6, 2),
('Coxinha Grande', 7.00, 5, 2),
('Esfiha de Carne', 6.50, 5, 2),
('Quibe Frito', 7.00, 5, 2),
('Caldo de Cana', 8.00, 2, 2),
('Torta de Limão', 12.00, 3, 2),
('Bolo de Cenoura', 14.00, 3, 2),
('Pizza de Mussarela', 35.00, 4, 3),
('Pizza de Calabresa', 38.00, 4, 3),
('Pizza Marguerita', 40.00, 4, 3),
('Pizza Portuguesa', 42.00, 4, 3),
('Pizza de Frango', 37.00, 4, 3),
('Pizza de Peperoni', 45.00, 4, 3),
('Pizza Vegana', 40.00, 4, 3),
('Pizza Quatro Queijos', 43.00, 4, 3),
('Pizza de Atum', 41.00, 4, 3),
('Refrigerante 2L', 12.00, 2, 3),
('Bolo de Chocolate', 18.00, 3, 4),
('Bolo de Morango', 20.00, 3, 4),
('Bolo de Abacaxi', 22.00, 3, 4),
('Bolo de Limão', 19.00, 3, 4),
('Cupcake de Chocolate', 10.00, 3, 4),
('Cupcake de Baunilha', 10.00, 3, 4),
('Torta de Nozes', 25.00, 3, 4),
('Torta de Coco', 23.00, 3, 4),
('Bolo Red Velvet', 30.00, 3, 4),
('Brownie com Sorvete', 18.00, 3, 4),
('Coxinha', 6.00, 5, 5),
('Bolinho de Bacalhau', 7.00, 5, 5),
('Porção de Batatas', 15.00, 6, 5),
('Porção de Calabresa', 20.00, 6, 5),
('Porção de Frango', 25.00, 6, 5),
('Porção de Carne', 28.00, 6, 5),
('Refrigerante 600ml', 6.50, 2, 5),
('Cerveja Lata', 8.00, 2, 5),
('Água Tônica', 5.50, 2, 5),
('Caipirinha', 15.00, 2, 5),
('Salada Vegana', 18.00, 8, 6),
('Smoothie de Morango', 12.00, 2, 6),
('Batata Doce Assada', 14.00, 6, 6),
('Hambúrguer Vegetariano', 22.00, 1, 6),
('Wrap de Frango', 20.00, 8, 6),
('Suco Natural', 10.00, 2, 6),
('Frango Grelhado', 28.00, 7, 6),
('Bolo Integral', 16.00, 3, 6),
('Cookies Veganos', 8.00, 3, 6),
('Chá Gelado', 7.00, 10, 6),
('Carne Assada', 40.00, 7, 7),
('Feijoada Completa', 35.00, 7, 7),
('Arroz e Feijão', 18.00, 7, 7),
('Frango à Parmegiana', 38.00, 7, 7),
('Escondidinho de Carne', 32.00, 7, 7),
('Salada de Batata', 15.00, 8, 7),
('Torresmo', 12.00, 6, 7),
('Bolinho de Feijoada', 10.00, 5, 7),
('Doce de Leite', 8.00, 3, 7),
('Guaraná Natural', 6.00, 2, 7),
('Camarão ao Alho', 50.00, 9, 8),
('Moqueca de Peixe', 45.00, 9, 8),
('Peixe Frito', 40.00, 9, 8),
('Arroz de Marisco', 38.00, 9, 8),
('Bolinho de Camarão', 15.00, 5, 8),
('Lula à Dorê', 35.00, 9, 8),
('Lagosta Grelhada', 70.00, 9, 8),
('Caldo de Camarão', 20.00, 9, 8),
('Coquetel de Frutas', 10.00, 2, 8),
('Pudim de Coco', 12.00, 3, 8),
('Coxinha', 6.00, 5, 9),
('Bolovo', 7.00, 5, 9),
('Pastel de Pizza', 8.00, 5, 9),
('Espetinho de Carne', 10.00, 6, 9),
('Porção de Mandioca', 12.00, 6, 9),
('Porção de Polenta', 14.00, 6, 9),
('Cerveja Long Neck', 10.00, 2, 9),
('Suco de Laranja', 7.00, 2, 9),
('Açaí na Tigela', 15.00, 3, 9),
('Sorvete de Chocolate', 10.00, 3, 9),
('Café Expresso', 7.00, 10, 10),
('Café com Leite', 8.00, 10, 10),
('Chá de Camomila', 6.00, 10, 10),
('Pão de Queijo', 5.00, 5, 10),
('Bolo de Milho', 10.00, 3, 10),
('Torrada com Geleia', 12.00, 3, 10),
('Sanduíche Natural', 15.00, 8, 10),
('Capuccino', 10.00, 10, 10),
('Cookie de Chocolate', 8.00, 3, 10),
('Água com Gás', 5.00, 2, 10);


INSERT INTO promocao (nome, codigo, valor, validade, tipo) VALUES
('Desconto 10%', 'DESC10', 10.00, '2024-12-31', 'Desconto'),
('Frete Grátis', 'FRETEGRATIS', 5.00, '2024-11-30', 'Frete'),
('Cupom VIP', 'VIP20', 20.00, '2024-12-15', 'Desconto'),
('Promo Bolo', 'BOLO5', 5.00, '2024-11-25', 'Produto'),
('Frete Reduzido', 'FRETERED', 3.00, '2024-12-01', 'Frete'),
('Desconto Verão', 'VERAO15', 15.00, '2025-01-31', 'Desconto'),
('Promo 2x1', 'DOIS1', 30.00, '2024-12-05', 'Produto'),
('Salgado Grátis', 'SALGFREE', 6.00, '2024-11-20', 'Produto'),
('Bebida Free', 'BEBFREE', 5.00, '2024-12-10', 'Produto'),
('Café na Faixa', 'CAFEFREE', 7.00, '2024-11-29', 'Produto');

INSERT INTO statusEntrega (statusEntrega) VALUES 
('Em produção'),
('Em Rota Entrega'),
('Entregue');

INSERT INTO acompanhamento (nome, descricao, valor) VALUES
('Batata Frita', 'Porção média de batatas fritas', 8.00),
('Molho Especial', 'Molho da casa', 3.00),
('Salada Simples', 'Porção de salada com tomate e alface', 5.00),
('Arroz', 'Porção de arroz branco', 4.00),
('Feijão', 'Porção de feijão preto', 4.00),
('Queijo Extra', 'Fatias de queijo adicional', 2.00),
('Bacon Crocante', 'Porção de bacon crocante', 6.00),
('Pão de Alho', 'Porção de pães de alho assados', 7.00),
('Farofa', 'Porção de farofa temperada', 5.00),
('Molho Picante', 'Molho de pimenta caseiro', 3.00);

INSERT INTO formaPagamento (formaPagamento) VALUES
('Cartão de Crédito'),
('Cartão de Débito'),
('Pix'),
('Dinheiro'),
('Transferência Bancária');
