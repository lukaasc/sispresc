/*
mysql --host=35.185.106.136 --user=admin --password
senha = asdf*/


SET FOREIGN_KEY_CHECKS = 0; 
SET @tables = NULL;
SELECT GROUP_CONCAT(table_schema, '.', table_name) INTO @tables
  FROM information_schema.tables 
  WHERE table_schema = 'sisprescdb';

SET @tables = CONCAT('DROP TABLE ', @tables);
PREPARE stmt FROM @tables;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
SET FOREIGN_KEY_CHECKS = 1;

show tables;

create table user(
  username varchar(40) primary key,
  password  varchar(10),
  role varchar(40),
  name varchar(40),
  lastname varchar(40)
);
create table paciente(
  nome varchar(40),
  sobrenome varchar(40),
  data_nasc date,
  sexo varchar(10),
  cpf varchar(15) primary key
);
create table medicamento(
  id int primary key,
  nome varchar(400)
  );

create table internacao(
  id int primary key AUTO_INCREMENT,
  cpf varchar(15),
  data_intern date,
  leito int,
  posto int,
  setor int,
  med_responsavel varchar(40),
  id_prescricao  int,
  situacao varchar(40),
  foreign key(cpf) references paciente(cpf),
  foreign key(med_responsavel) references user(username),
  foreign key(id_prescricao) references prescricao(id)
);

create table prescricao(
  id int primary key AUTO_INCREMENT,
  id_internacao int,
  med_responsavel varchar(40),
  situacao varchar(40),
  data_criacao date,
  observacao varchar(4000),
  foreign key(med_responsavel) references user(username)
);

create table medicamento_prescricao(
  id_medicamento int,
  id_prescricao int,
  foreign key (id_medicamento) references medicamento(id),
  foreign key (id_prescricao) references prescricao(id),
  primary key(id_medicamento, id_prescricao)
);

insert into  user (username, password, role, name, lastname) values ('mmachado', 'machado99', 'medico', 'Marcelo', 'Machado');
insert into  user (username, password, role, name, lastname) values ('luisc', '334411', 'farmaceutico', 'Luis', 'Cardoso');
insert into  user (username, password, role, name, lastname) values ('betas', 'asdf1234', 'medico', 'Roberta', 'Souza');
insert into  user (username, password, role, name, lastname) values ('fernanda1', 'ferlopes', 'farmaceutico', 'Fernanda', 'Lopes');

insert into paciente (nome, sobrenome, data_nasc, sexo, cpf) values ('Juliano', 'Fraga', STR_TO_DATE('05/05/1989', '%d/%m/%Y'), 'M', '4561238094583');
insert into paciente (nome, sobrenome, data_nasc, sexo, cpf) values ('Julia', 'Martins', STR_TO_DATE('30/03/1994', '%d/%m/%Y'), 'F','5468741365987');
insert into paciente (nome, sobrenome, data_nasc, sexo, cpf) values ('Ricardo', 'Fonseca', STR_TO_DATE('14/07/1962', '%d/%m/%Y'), 'M', '3698741259654');
insert into paciente (nome, sobrenome, data_nasc, sexo, cpf) values ('Patricia', 'Silva', STR_TO_DATE('09/12/2000', '%d/%m/%Y'), 'F', '3698547895412');

insert into medicamento(id, nome) values (44567, 'ASPIRINA 500 MG COM CT 10 ENV X 2');
insert into medicamento(id, nome) values (38345, 'IQUEGO - PARACETAMOL 500 MG COM CX ENV KRAFT POLIET X 500 (EMB. HOSP');
insert into medicamento(id, nome) values (28644, 'TORAGESIC 10 MG COM SUB- LING CT FR VD AMB X');
insert into medicamento(id, nome) values (96494, 'ISOFARMA - SOLUÇÃO DE SULFATO DE MAGNESIO 100 MG/ML SOL INJ CX 100 AMP PLAS TRANS PE X 10 ML');

insert into prescricao(situacao, med_responsavel, data_criacao, observacao) values ('Ativa', 'mmachado', STR_TO_DATE('15/03/2017', '%d/%m/%Y'), 'Tomar X vezes ao dia durante Y dias');
insert into prescricao(situacao, med_responsavel, data_criacao, observacao) values ('Ativa', 'mmachado', STR_TO_DATE('09/05/2017', '%d/%m/%Y'), 'Tomar X vezes ao dia durante Y dias');
insert into prescricao(situacao, med_responsavel, data_criacao, observacao) values ('Ativa', 'betas', STR_TO_DATE('01/04/2017', '%d/%m/%Y'), 'Tomar X vezes ao dia durante Y dias');
insert into prescricao(situacao, med_responsavel, data_criacao, observacao) values ('Ativa', 'betas', STR_TO_DATE('23/07/2016', '%d/%m/%Y'), 'Tomar X vezes ao dia durante Y dias');
insert into prescricao(situacao, med_responsavel, data_criacao, observacao) values ('Ativa', 'mmachado', STR_TO_DATE('10/10/2015', '%d/%m/%Y'), 'Tomar X vezes ao dia durante Y dias');

insert into medicamento_prescricao(id_medicamento, id_prescricao) values (44567, 1);
insert into medicamento_prescricao(id_medicamento, id_prescricao) values (38345, 1);
insert into medicamento_prescricao(id_medicamento, id_prescricao) values (96494, 2);
insert into medicamento_prescricao(id_medicamento, id_prescricao) values (28644, 3);
insert into medicamento_prescricao(id_medicamento, id_prescricao) values (96494, 4);
insert into medicamento_prescricao(id_medicamento, id_prescricao) values (38345, 5);

insert into internacao (id_prescricao, cpf, data_intern, leito, posto, setor, med_responsavel, situacao) values (1, '4561238094583', STR_TO_DATE('15/03/2017', '%d/%m/%Y'), 11, 14, 3, 'betas', 'Fechada');
insert into internacao (id_prescricao, cpf, data_intern, leito, posto, setor, med_responsavel, situacao) values (2, '5468741365987', STR_TO_DATE('09/05/2017', '%d/%m/%Y'), 3, 14, 1, 'mmachado', 'Fechada');
insert into internacao (id_prescricao, cpf, data_intern, leito, posto, setor, med_responsavel, situacao) values (3, '3698741259654', STR_TO_DATE('01/04/2017', '%d/%m/%Y'), 7, 13, 2, 'betas', 'Fechada');
insert into internacao (id_prescricao, cpf, data_intern, leito, posto, setor, med_responsavel, situacao) values (4, '3698741259654', STR_TO_DATE('24/07/2016', '%d/%m/%Y'), 6, 12, 1, 'betas', 'Fechada');
insert into internacao (id_prescricao, cpf, data_intern, leito, posto, setor, med_responsavel, situacao) values (null, '3698547895412', STR_TO_DATE('10/12/2016', '%d/%m/%Y'), 6, 13, 1, 'mmachado', 'Aberta');
insert into internacao (id_prescricao, cpf, data_intern, leito, posto, setor, med_responsavel, situacao) values (5, '5468741365987', STR_TO_DATE('09/10/2015', '%d/%m/%Y'), 3, 14, 1, 'mmachado', 'Fechada');


select concat(u.name, ' ', u.lastname) medico, concat(pac.nome, ' ', pac.sobrenome) paciente, inter.situacao sitinternacao, inter.data_intern, presc.situacao prescsituacao, presc.data_criacao, m.nome, presc.observacao 
from prescricao presc
inner join user u on u.username = presc.med_responsavel
inner join internacao inter on inter.id_prescricao = presc.id
inner join medicamento_prescricao mp on mp.id_prescricao = presc.id
inner join medicamento m on m.id = mp.id_medicamento
inner join paciente pac on pac.cpf = inter.cpf;

select * from prescricao;
select * from internacao;
