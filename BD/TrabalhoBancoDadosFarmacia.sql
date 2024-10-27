create database newfarmacia;
use newfarmacia;
CREATE TABLE endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(60),
    bairro VARCHAR(30),
    codigo_postal VARCHAR(9),
    estado VARCHAR(2),
    cidade VARCHAR(60),
    numero VARCHAR(10),
    tipo_endereco ENUM('comercial', 'residencial')
);
CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_produto VARCHAR(60) NOT NULL,
    codigo_barras VARCHAR(20) UNIQUE,
    custo_produto FLOAT,
    observaçoes TEXT,
    validade DATE
);
CREATE TABLE laboratorio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    cnpj VARCHAR(18) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(15)
);
CREATE TABLE fornecedor_farmacia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    cnpj VARCHAR(18) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(15),
    id_laboratorio INT,
    id_produto INT,
    id_endereco INT,
    FOREIGN KEY (id_laboratorio) REFERENCES laboratorio (id),
    FOREIGN KEY (id_produto) REFERENCES produto (id),
    FOREIGN KEY (id_endereco) REFERENCES endereco (id)
);
CREATE TABLE fornecedor_produto (
    id_produto INT,
    id_fornecedor INT,
    PRIMARY KEY (id_produto , id_fornecedor),
    FOREIGN KEY (id_produto) REFERENCES produto (id),
    FOREIGN KEY (id_fornecedor) REFERENCES fornecedor_farmacia (id)
);
CREATE TABLE produto_comprado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_fornecedor_farmacia INT,
    id_produto INT,
    data_compra DATE,
    preco_compra FLOAT,
    quantidade_produto_comprado INT,
    FOREIGN KEY (id_fornecedor_farmacia) REFERENCES fornecedor_farmacia (id),
    FOREIGN KEY (id_produto) REFERENCES produto (id)
);
CREATE TABLE estoque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT UNIQUE,
    quantidade_disponivel INT,
    FOREIGN KEY (id_produto) REFERENCES produto (id)
);
CREATE TABLE acerto_estoque(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT,
    tipo_movimentacao ENUM('entrada', 'saida'),
    quantidade INT,
    data_movimentacao DATE,
    FOREIGN KEY (id_produto) REFERENCES produto(id)
);
CREATE TABLE Atendente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60) NOT NULL
);
CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    cpf VARCHAR(14) UNIQUE,
    id_endereco INT,
    telefone VARCHAR(15),
    data_nascimento DATE NOT NULL,
    FOREIGN KEY (id_endereco) REFERENCES endereco (id)
);
CREATE TABLE receita_medica (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    data_emissao DATE,
    medicamento_prescrito VARCHAR(30),
    modo_uso TEXT,
    FOREIGN KEY (id_cliente) REFERENCES cliente (id)
);
CREATE TABLE forma_de_pagamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_de_pagamento ENUM('Dinheiro', 'Pix', 'Cartão Credito', 'Cartão Debito', 'Transferência')
);
CREATE TABLE vendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_venda DATE,
    id_cliente INT,
    id_atendente INT,
    id_forma_de_pagamento INT,
    valor_total FLOAT,
    FOREIGN KEY (id_cliente) REFERENCES cliente (id),
    FOREIGN KEY (id_atendente) REFERENCES Atendente (id),
    FOREIGN KEY (id_forma_de_pagamento) REFERENCES forma_de_pagamento (id)
);
CREATE TABLE itens_venda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT NOT NULL,
    id_venda INT,
    quantidade INT NOT NULL,
    preco_unitario FLOAT NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES produto (id),
    FOREIGN KEY (id_venda) REFERENCES vendas(id)
);

INSERT INTO endereco (rua, bairro, codigo_postal, estado, cidade, numero, tipo_endereco) VALUES
('Rua das Flores', 'Centro', '12345-678', 'SP', 'São Paulo', '101', 'comercial'),
('Av. dos Jacarandás', 'Jardim', '23456-789', 'RJ', 'Rio de Janeiro', '202', 'comercial'),
('Rua Amapá', 'Vila Nova', '34567-890', 'MG', 'Belo Horizonte', '303', 'comercial'),
('Rua do Sol', 'Industrial', '45678-901', 'BA', 'Salvador', '404', 'comercial'),
('Rua das Palmeiras', 'Copacabana', '56789-012', 'RS', 'Porto Alegre', '505', 'comercial'),
('Av. das Nações', 'Centro', '67890-123', 'SC', 'Florianópolis', '606', 'comercial'),
('Rua Esperança', 'Vila Rica', '78901-234', 'PR', 'Curitiba', '707', 'comercial'),
('Av. Amazonas', 'Floresta', '89012-345', 'PE', 'Recife', '808', 'comercial'),
('Rua dos Limoeiros', 'Bonfim', '90123-456', 'CE', 'Fortaleza', '909', 'comercial'),
('Rua das Estrelas', 'Centro', '01234-567', 'PA', 'Belém', '110', 'comercial'),
('Av. Paulista', 'Bela Vista', '12345-000', 'SP', 'São Paulo', '1000', 'residencial'),
('Av. Rio Branco', 'Centro', '23456-100', 'RJ', 'Rio de Janeiro', '200', 'residencial'),
('Av. Cristóvão Colombo', 'Savassi', '34567-200', 'MG', 'Belo Horizonte', '150', 'residencial'),
('Rua Chile', 'Comércio', '45678-300', 'BA', 'Salvador', '250', 'residencial'),
('Rua da Praia', 'Centro', '56789-400', 'RS', 'Porto Alegre', '350', 'residencial'),
('Av. Hercílio Luz', 'Centro', '67890-500', 'SC', 'Florianópolis', '450', 'residencial'),
('Av. Sete de Setembro', 'Batel', '78901-600', 'PR', 'Curitiba', '550', 'residencial'),
('Rua Aurora', 'Boa Vista', '89012-700', 'PE', 'Recife', '650', 'residencial'),
('Av. Santos Dumont', 'Aldeota', '90123-800', 'CE', 'Fortaleza', '750', 'residencial'),
('Rua Gaspar Viana', 'Cidade Velha', '01234-900', 'PA', 'Belém', '850', 'residencial');

UPDATE endereco SET
	rua = 'Rua Boa vista',
    bairro = 'JK',
    codigo_postal = '34533-234',
    estado = 'BH',
    cidade = 'Belo Horizonte',
    numero = '678',
    tipo_endereco = 'residencial'
WHERE id = 1;

DELETE FROM endereco WHERE id = 1;

INSERT INTO produto (nome_produto, codigo_barras, custo_produto, observaçoes, validade) VALUES
('Paracetamol', '1234567890123', 2.50, 'Uso adulto', '2025-01-01'),
('Dipirona', '2345678901234', 3.00, 'Uso pediátrico', '2024-06-01'),
('Ibuprofeno', '3456789012345', 4.50, 'Anti-inflamatório', '2024-12-01'),
('Amoxicilina', '4567890123456', 5.00, 'Antibiótico', '2025-05-01'),
('Omeprazol', '5678901234567', 6.00, 'Antiácido', '2025-08-01'),
('Loratadina', '6789012345678', 7.00, 'Antialérgico', '2024-09-01'),
('Ranitidina', '7890123456789', 8.00, 'Antiácido', '2024-10-01'),
('AAS Infantil', '8901234567890', 1.50, 'Uso pediátrico', '2025-03-01'),
('Vitamina C', '9012345678901', 9.00, 'Suplemento', '2025-12-01'),
('Cetoconazol', '0123456789012', 10.00, 'Antifúngico', '2025-11-01'),
('Diad', '789654852626', '1.75', 'Uso Adulto', '2026-01-01'),
('Tadalafila', '548622758560', '5.75', 'Uso Adulto', '2025-12-01'),
('Avammys', '785642134856', '23.75', 'Uso Adulto', '2026-01-01'),
('Doralgina', '85641239753214', '0.75', 'Uso Adulto', '2025-12-01'),
('Losartana 50mg', '789566241322', '5.75', 'Uso Adulto', '2026-06-01'),
('Losartana 100mg', '789567421322', '6.85', 'Uso Adulto', '2026-08-01'),
('Azitromicina', '185026785123', '8.75', 'Antibiótico', '2026-12-01'),
('Dipirona Xarope', '789234567813', '5.75', 'Uso Adulto/Uso Infantil', '2025-12-01'),
('Neosoro Adulto','789856345228', 11.25, 'Uso adulto', '2024-12-01'),
('Neosoro Infantil', '789856345229', '8.25', 'Uso Infantil', '2025-06-01');

UPDATE produto SET
	nome_produto = 'acetaminofeno',
    custo_produto = '2.90',
    validade = '2026-01-01'   
WHERE id = 1;

DELETE FROM produto WHERE id = 1;


INSERT INTO laboratorio (nome, cnpj, email, telefone) VALUES
('Lab Vida', '12.345.678/0001-12', 'contato@labvida.com.br', '(11) 99999-0001'),
('Bioquímicos S.A.', '23.456.789/0002-23', 'contato@bioquimicos.com.br', '(21) 99999-0002'),
('PharmaTech', '34.567.890/0003-34', 'contato@pharmatech.com.br', '(31) 99999-0003'),
('MedLab', '45.678.901/0004-45', 'contato@medlab.com.br', '(71) 99999-0004'),
('Lab Innovare', '56.789.012/0005-56', 'contato@labinnovare.com.br', '(51) 99999-0005'),
('VitaPharma', '67.890.123/0006-67', 'contato@vitapharma.com.br', '(48) 99999-0006'),
('GenLab', '78.901.234/0007-78', 'contato@genlab.com.br', '(41) 99999-0007'),
('LabClínico', '89.012.345/0008-89', 'contato@labclinico.com.br', '(81) 99999-0008'),
('PharmaLife', '90.123.456/0009-90', 'contato@pharmalife.com.br', '(85) 99999-0009'),
('BioPharma', '01.234.567/0010-01', 'contato@biopharma.com.br', '(91) 99999-0010');

UPDATE laboratorio SET
	nome = 'lab.Stark',
    cnpj = '13.342.124/6754-01',
    email = 'Stark@Dev.com.br',
    Telefone = '4002-8922'
WHERE id = 1;

DELETE FROM laboratorio WHERE id = 1;

INSERT INTO fornecedor_farmacia (nome, cnpj, email, telefone, id_laboratorio, id_produto, id_endereco) VALUES
('Profarma', '12.345.678/0001-12', 'Profarma@email.com.br', '(87) 99999-1001', 1, 1, 1),
('NovaQuimica', '23.456.789/0002-23', 'NovaQuimica@email.com.br', '(21) 99999-1002', 2, 2, 2),
('AuraQuimica', '34.567.890/0003-34', 'AuraQuimica@email.com.br', '(31) 99999-1003', 3, 3, 3),
('Atacadão S. A', '45.678.901/0004-45', 'Atacadão@email.com.br', '(71) 99999-1004', 4, 4, 4),
('Cimed', '56.789.012/0005-56', 'Cimed@email.com.br', '(51) 99999-1005', 5, 5, 5),
('ABS Produtos', '67.890.123/0006-67', 'ABS@email.com.br', '(48) 99999-1006', 6, 6, 6),
('EuroFarma', '78.901.234/0007-78', 'EuroFarma@email.com.br', '(41) 99999-1007', 7, 7, 7),
('Plena Distribuidora', '89.012.345/0008-89', 'PlenaDistribuidora@email.com.br', '(81) 99999-1008', 8, 8, 8),
('Plena Mais Distribuidora', '90.123.456/0009-90', 'PlenaMaisDistribuidora@email.com.br', '(85) 99999-1009', 9, 9, 9),
('Total Comercio Distribuidora', '01.234.567/0010-01', 'TotalComercioDistribuidora@email.com.br', '(91) 99999-1010', 10, 10, 10);

INSERT INTO produto_comprado(id_fornecedor_farmacia, id_produto, data_compra, preco_compra, quantidade_produto_comprado ) VALUES 
(1, 1, '2024-09-23', 2.75, 200),
(2, 2, '2024-09-23', 4.55, 120),
(2, 3, '2024-09-23', 5.25, 60),
(3, 4, '2024-09-23', 6.75, 120),
(4, 5, '2024-09-23', 9.98, 40),
(6, 6, '2024-09-23', 15.98, 15),
(6, 7, '2024-09-23', 2.98, 10),
(10, 8, '2024-09-23', 5.75, 60),
(5, 9, '2024-09-23', 6.84, 3),
(7, 10, '2024-09-23', 7.89, 10),
(8, 11, '2024-09-23', 22.98, 60),
(9, 12, '2024-09-23', 5.55, 120),
(6, 13, '2024-03-23', 0.98, 300),
(3, 14, '2024-03-23', 7.85, 200),
(9, 15, '2024-03-23', 10.01, 5),
(4, 16, '2024-03-23', 22.05, 60),
(6, 17, '2024-03-23', 55.10, 10),
(1, 18, '2024-03-23', 0.75, 60),
(3, 19, '2024-03-23', 5.75, 60),
(1, 20, '2024-09-23', 3.55, 180);

INSERT INTO estoque(id_produto, quantidade_disponivel) VALUES 
(1, 200),
(2, 120),
(3, 60),
(4, 120),
(5, 40),
(6, 15),
(7, 10),
(8, 60),
(9, 3),
(10, 10),
(11, 60),
(12, 120),
(13, 300),
(14, 200),
(15, 5),
(16, 60),
(17, 10),
(18,60),
(19, 60),
(20, 180);

UPDATE estoque SET
	quantidade_disponivel = '120'
WHERE id = 1;

DELETE FROM estoque WHERE id = 1 OR quantidade_disponivel = 0;


INSERT INTO acerto_estoque(id_produto, tipo_movimentacao, quantidade, data_movimentacao) VALUES 
(1, 'entrada', 25, '2024-09-23'),
(2, 'entrada', 15, '2024-09-23'),
(3, 'saida', 33, '2024-09-23'),
(4, 'entrada', 15, '2024-09-23'),
(5, 'saida', 1, '2024-09-23'),
(6, 'saida', 10, '2024-09-23'),
(7, 'entrada', 50, '2024-09-23'),
(8, 'entrada', 10, '2024-09-23'),
(9, 'saida', 3, '2024-09-23'),
(10, 'saida', 2, '2024-09-23'),
(11, 'saida', 6, '2024-09-23'),
(12, 'entrada', 8, '2024-09-23'),
(13, 'entrada', 60, '2024-09-23'),
(14, 'saida', 22, '2024-09-23'),
(15, 'saida', 10, '2024-09-23'),
(16, 'saida', 10, '2024-09-23'),
(17, 'entrada', 25, '2024-09-23'),
(18, 'saida', 5, '2024-09-23'),
(19, 'saida', 30, '2024-09-23'),
(20, 'entrada', 90, '2024-09-23');

INSERT INTO Atendente (nome) VALUES
('Carlos Silva'),
('Ana Souza'),
('Paula Oliveira'),
('Lucas Santos'),
('Maria Fernandes'),
('João Lima'),
('Camila Reis'),
('Renato Carvalho'),
('Beatriz Ramos'),
('José Costa');

UPDATE Atendente SET
	nome = 'Anna Souza'
WHERE id = 2;

DELETE FROM Atendente WHERE id = 2;

INSERT INTO Cliente (nome, cpf, telefone, data_nascimento, id_endereco) VALUES
('Thiago Almeida', '123.456.789-00', '(11) 99999-1001', '2000-10-22', 11),
('Gabriela Martins', '234.567.890-11', '(11) 99999-1001', '1980-08-01', 12),
('Talles Pacheco', '345.678.901-22', '(11) 99999-1001', '1985-01-01', 13),
('Juliana Pereira', '456.789.012-33', '(11) 99999-1001', '1988-10-25', 14),
('Rodrigo Santos', '567.890.123-44', '(11) 99999-1001', '1997-01-31', 15),
('Fernanda Souza', '678.901.234-55', '(11) 99999-1001', '2003-01-25', 16),
('Lucas Oliveira', '789.012.345-66', '(11) 99999-1001', '1995-01-12', 17),
('Patrícia Lima', '890.123.456-77', '(11) 99999-1001', '1999-12-31', 18),
('Gustavo Fernandes', '901.234.567-88', '(11) 99999-1001', '2000-06-21', 19),
('Mariana Costa', '012.345.678-99', '(11) 99999-1001', '1986-12-30', 20);

UPDATE cliente SET
	nome = 'Thiago Almeida Ramos',
    telefone = '(42)99999-1466',
    data_nascimento = '2001-10-22',
    id_endereco = '12'
WHERE id = 1;

UPDATE cliente SET
	id_endereco = '15',
    telefone = '(60) 10101-1001'
WHERE nome = 'Thiago Almeida Ramos';

DELETE FROM cliente WHERE telefone = '(60) 10101-1001';

DELETE FROM cliente WHERE id = 1;

INSERT INTO receita_medica (id_cliente, data_emissao, medicamento_prescrito, modo_uso) VALUES
(1, '2024-09-22', 'Paracetamol', 'Tomar 3x ao dia se febre'),
(2, '2024-09-22', 'Losartana 50mg', 'Tomar 1 Comp 1x ao dia'),
(4, '2024-09-22', 'AAS infantil', 'Tomar 1 comp ate 4x ao dia'),
(3, '2024-09-22', 'Neosoro Adulto', 'Um Janto Se necessario, maximo 3x ao dia'),
(4, '2024-09-22', 'Azitromicina', 'Tomar 1 Comp por dia durante 3 dias'),
(5, '2024-09-22', 'Losartana 100mg', 'Tomar 1 Comp 1x ao dia'),
(8, '2024-09-22', 'Loratadina', 'Tomar 1 Comp 1x ao dia'),
(5, '2024-09-22', 'Omeprazol', 'Tomar 1 Comp em jejum'),
(6, '2024-09-22', 'Doralgina', 'Tomar 1 Comp 2x ao dia'),
(6, '2024-09-22', 'Dipirona Xarope', 'Tomar 8ml 2x ao dia'),
(1, '2024-09-22', 'Azitromicina', 'Tomar 1 Comp 1x ao dia durante 5 dias'),
(3, '2024-09-22', 'Vitamica C', 'Diluir 1 Comp 1 ou 2 vezes ao dia em 100ml de água'),
(7, '2024-09-22', 'Vitamina C', 'Diluir 1 Comp 1x ao dia em 100ml de água'),
(10, '2024-09-22', 'Avammys', 'Um jato 3x ao dia'),
(2, '2024-09-22', 'Cetoconazol', 'Aplicar 2x ao dia na região afetada'),
(8, '2024-09-22', 'Amoxicilina', 'Tomar 1 Comp 3x ao dia'),
(9, '2024-09-22', 'Ibuprofeno', 'Tomar 1 Comp 3x ao dia'),
(1, '2024-09-22', 'Dipirona', 'Tomar 1 Comp 1x Ao dia'),
(3, '2024-09-22', 'Tadalafina', 'Tomar 1 hora antes'),
(10, '2024-09-22', 'Dipirona Xarope', 'Tomar 10ml 3x ao dia');

INSERT INTO forma_de_pagamento (tipo_de_pagamento) VALUES 
('Dinheiro'),
('Pix'),
('Cartão Credito'),
('Cartão Debito'),
('Transferência');

INSERT INTO Vendas (data_venda, id_atendente, id_cliente, id_forma_de_pagamento) VALUES
('2024-01-01', 1, 1, '1'),
('2024-02-01', 2, 2, 3),
('2024-03-01', 3, 3, 2),
('2024-04-01', 4, 4, 3),
('2024-05-01', 5, 5, 4),
('2024-06-01', 6, 6, 1),
('2024-07-01', 7, 7, 2),
('2024-08-01', 8, 8, 3),
('2024-09-01', 9, 9, 5),
('2024-10-01', 10, 10, 1);

INSERT INTO itens_venda (id_venda, id_produto, quantidade, preco_unitario) VALUES
(1, 1, 3, '6.98'),
(2, 3, 1, '9.98'),
(3, 5, 2, '11.98'),
(4, 12, 4, '39.98'),
(5, 15, 1, '15.98'),
(6, 9, 3, '21.98'),
(7, 6, 2, '29.98'),
(8, 17, 3, '98.98'),
(9, 8, 10, '103.98'),
(10, 20, 1, 19.98);


SELECT DISTINCT nome_produto
FROM produto;

SELECT nome_produto, custo_produto FROM produto
WHERE custo_produto > 10;

SELECT nome_produto, custo_produto FROM produto
WHERE custo_produto < 9;

SELECT nome_produto, custo_produto FROM produto
ORDER BY nome_produto ASC;

SELECT nome_produto, custo_produto FROM produto
ORDER BY nome_produto DESC;

SELECT nome_produto, custo_produto FROM produto
ORDER BY custo_produto DESC
LIMIT 3;

SELECT nome_produto, custo_produto FROM produto
ORDER BY custo_produto ASC
LIMIT 3;

SELECT COUNT(*) AS total_produtos
FROM produto;

SELECT SUM(quantidade_disponivel) AS total_estoque
FROM estoque;


SELECT DISTINCT nome FROM cliente;

SELECT nome, telefone, data_nascimento FROM cliente
WHERE data_nascimento < '1990-01-01';

SELECT nome, telefone, data_nascimento FROM cliente
WHERE data_nascimento > '2000-01-01';

SELECT nome,cpf,data_nascimento FROM cliente
WHERE data_nascimento = '2000-10-22';

SELECT nome, cpf FROM cliente
ORDER BY nome, cpf ASC;

SELECT nome, data_nascimento FROM cliente
ORDER BY nome, data_nascimento DESC;

SELECT COUNT(*) AS total_pessoas FROM cliente;

SELECT DISTINCT tipo_endereco FROM endereco;

SELECT cidade FROM endereco
ORDER BY cidade;

SELECT cidade, rua, estado FROM endereco
WHERE cidade = 'Belo Horizonte';

SELECT DISTINCT estado FROM endereco;

SELECT DISTINCT nome FROM Atendente;

SELECT DISTINCT nome FROM Laboratorio;

SELECT id ,nome, cnpj, telefone FROM Laboratorio
ORDER BY id ASC;

SELECT DISTINCT nome FROM fornecedor_farmacia;

SELECT id ,nome, cnpj, telefone, id_produto, id_endereco FROM fornecedor_farmacia
ORDER BY id DESC;

SELECT * FROM endereco WHERE id = 1;

SELECT * FROM laboratorio WHERE id = 5;

SELECT * FROM cliente WHERE id = 10;

SELECT * FROM produto WHERE id = 20;

SELECT * FROM estoque WHERE id = 40;

-- DROP DATABASE newfarmacia;