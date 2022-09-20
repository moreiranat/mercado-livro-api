CREATE TABLE book (
	id int auto_increment primary key,
    name varchar(255) not null,
    price decimal(10, 2) not null,
    status varchar(255) not null,
    customer_id int not null,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

--customer_id: cada um dos livros vai ficar linkado com um usuario,
--customer_id: o usuario vai ter livros publicados e por isso tem que haver essa ligacao com o livro
--customer_id: referente a uma coluna de outra tabela (Forein Key (FK) ou chave estrangeira)
--customer_id: só podem ser i nseridos valores neste campo, que ja foram inseridos previamente na coluna id da tabela customer
