CREATE TABLE Cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE Produto (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(60) NOT NULL,
    valor NUMERIC(38, 2) NOT NULL,
    categoria VARCHAR(60) NOT NULL
);

CREATE TABLE Venda (
    id SERIAL PRIMARY KEY,
    observacao VARCHAR(255),
    data TIMESTAMP NOT NULL,
    total NUMERIC(38, 2) NOT NULL,
    cliente_id INTEGER NOT NULL REFERENCES Cliente(id)
);

CREATE TABLE ItemVenda (
    id SERIAL PRIMARY KEY,
    quantidade INTEGER NOT NULL,
    valor_unitario NUMERIC(38, 2) NOT NULL,
    valor_total NUMERIC(38, 2) NOT NULL,
    venda_id INTEGER NOT NULL REFERENCES Venda(id),
    produto_id INTEGER NOT NULL REFERENCES Produto(id)
);