CREATE DATABASE Clientes;

use Clientes;

create table Cliente (
       user  varchar(10)  not null,
       password varchar(12) not null,  
       balance  double(10,2)  not null,
       Primary Key (user)         
     );