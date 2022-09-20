CREATE TABLE pedidos(
id bigint(20) NOT NULL AUTO_INCREMENT,
valor decimal(19,2) NOT NULL,
nomeCliente varchar(100) DEFAULT NULL,
status varchar(255) NOT NULL,
pedido_id bigint(20) NOT NULL,
forma_de_pagamentos_id bigint(20) NOT NULL
PRIMARY KEY (id)
)