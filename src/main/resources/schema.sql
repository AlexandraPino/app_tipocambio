DROP TABLE IF EXISTS tbl_tipocambio;

CREATE TABLE tbl_tipocambio (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
moneda_origen VARCHAR(10) NOT NULL,
moneda_destino VARCHAR(10) NOT NULL,
tipo_cambio DECIMAL(20, 2) NOT NULL,
CONSTRAINT unique_tipo_cambio UNIQUE (moneda_origen, moneda_destino)
);