CREATE TABLE fornecedor(
    id_fornecedor UUID default gen_random_uuid() primary key,
    nome_fornecedor varchar(100),
    cnpj varchar(100),
    telefone varchar(100)
);

CREATE TABLE categoria(
                          id_categoria UUID default gen_random_uuid() primary key,
                          nome_categoria varchar(100)
);

CREATE TABLE produto(
                        id_produto UUID default gen_random_uuid() primary key,
                        nome_produto varchar(100),
                        id_categoria UUID,
                        qtd_em_estoque integer,
                        preco integer,
                        FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE fornece(
    id_fornecedor UUID,
    id_produto UUID,
    FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id_fornecedor),
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);


CREATE TABLE movimentacao(
    id_movimentacao UUID default gen_random_uuid() primary key,
    qtd integer,
    data timestamp,
    entrada boolean,
    id_produto UUID,
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);

