-- Banco de exemplo da loja, usado nas aulas 5.4 a 5.9.
-- Apaga e recria todas as tabelas com os dados originais.
-- Funciona no PostgreSQL. Para rodar no psql:  \i loja.sql

DROP TABLE IF EXISTS itens_pedido, pedidos, clientes, produtos, categorias CASCADE;

CREATE TABLE categorias (
    id    INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome  VARCHAR(60) NOT NULL UNIQUE
);

CREATE TABLE produtos (
    id            INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome          VARCHAR(100) NOT NULL,
    preco         DECIMAL(10, 2) NOT NULL CHECK (preco > 0),
    estoque       INTEGER NOT NULL DEFAULT 0 CHECK (estoque >= 0),
    categoria_id  INTEGER REFERENCES categorias (id)
);

CREATE TABLE clientes (
    id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    cidade      VARCHAR(60),
    nascimento  DATE,
    criado_em   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE pedidos (
    id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cliente_id  INTEGER NOT NULL REFERENCES clientes (id),
    data        DATE NOT NULL DEFAULT CURRENT_DATE,
    status      VARCHAR(20) NOT NULL DEFAULT 'ABERTO'
                CHECK (status IN ('ABERTO', 'PAGO', 'ENVIADO', 'CANCELADO'))
);

CREATE TABLE itens_pedido (
    pedido_id       INTEGER REFERENCES pedidos (id) ON DELETE CASCADE,
    produto_id      INTEGER REFERENCES produtos (id),
    quantidade      INTEGER NOT NULL CHECK (quantidade > 0),
    preco_unitario  DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (pedido_id, produto_id)
);

INSERT INTO categorias (nome) VALUES ('Informática'), ('Móveis'), ('Livros');

INSERT INTO produtos (nome, preco, estoque, categoria_id) VALUES
    ('Teclado',        149.90, 30, 1),
    ('Mouse',           59.90, 50, 1),
    ('Monitor',        899.00, 10, 1),
    ('Cadeira gamer', 1299.00,  5, 2),
    ('Mesa',           499.00,  8, 2),
    ('Livro de Java',   89.90, 20, 3),
    ('Webcam',         199.90,  0, NULL);

INSERT INTO clientes (nome, email, cidade, nascimento) VALUES
    ('Ana Lima',    'ana@email.com',   'Rio de Janeiro', '1994-03-15'),
    ('Bruno Reis',  'bruno@email.com', 'São Paulo',      '1988-07-02'),
    ('Carla Dias',  'carla@email.com', 'Rio de Janeiro', '2001-11-20'),
    ('Diego Alves', 'diego@email.com', 'Recife',         NULL),
    ('Elisa Moura', 'elisa@email.com', NULL,             '1999-01-30');

INSERT INTO pedidos (cliente_id, data, status) VALUES
    (1, '2026-09-01', 'PAGO'),
    (1, '2026-09-15', 'ENVIADO'),
    (2, '2026-09-20', 'PAGO'),
    (3, '2026-10-01', 'ABERTO'),
    (4, '2026-10-05', 'CANCELADO'),
    (2, '2026-10-10', 'PAGO');

INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES
    (1, 1, 1,  149.90), (1, 2, 2,   59.90),
    (2, 3, 1,  899.00),
    (3, 4, 1, 1299.00), (3, 2, 1,   59.90),
    (4, 6, 2,   89.90),
    (5, 5, 1,  499.00),
    (6, 1, 1,  149.90), (6, 6, 1,   89.90);
