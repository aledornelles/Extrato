CREATE TYPE tipo_pessoa as ENUM ('PF', 'PJ');
CREATE TABLE pessoa(
	id UUID PRIMARY KEY NOT NULL,
	nome VARCHAR(100) NOT NULL,
	documento VARCHAR(18) NOT NULL UNIQUE,
	tipo tipo_pessoa NOT NULL
)

CREATE TYPE tipo_conta as ENUM ('corrente', 'salario', 'poupanca');
CREATE TABLE conta (
	id UUID PRIMARY KEY NOT NULL,
	numero INTEGER NOT NULL UNIQUE,
	saldo DECIMAL(15,2) NOT NULL DEFAULT(0.0),
	tipo tipo_conta NOT NULL,
	pessoa_id UUID REFERENCES pessoa(id)
)