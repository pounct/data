DROP TABLE IF EXISTS Libros;
create table Libros (isbn varchar(10), titulo varchar(25), autor varchar(25), fecha date, precio decimal, PRIMARY KEY (isbn));