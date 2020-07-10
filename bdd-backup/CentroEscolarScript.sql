CREATE EXTENSION pgcrypto;

CREATE TABLE users(
	username varchar NOT NULL PRIMARY KEY,
	password varchar NOT NULL,
	enabled BOOLEAN NOT NULL,
	nombre VARCHAR NOT NULL,
	apellido VARCHAR NOT NULL,
	fechaDeNacimiento DATE NOT NULL,
	edad INTEGER NOT NULL,
	idDepartamento INTEGER NOT NULL,
	idMunicipio INTEGER NOT NULL,
	direccion VARCHAR
);

CREATE TABLE authorities(
	authority_id serial primary key not null,
	username varchar not null,
	authority varchar not null
);

create unique index ix_auth_username on authorities (username, authority);

CREATE TABLE Departamento(
	idDepartamento INTEGER NOT NULL PRIMARY KEY,
	nombreDepartamento VARCHAR NOT NULL
);

CREATE TABLE Municipio(
	idMunicipio INTEGER PRIMARY KEY,
	nombreMunicipio VARCHAR NOT NULL
);


CREATE TABLE CentroEscolar(
	idCentroEscolar SERIAL PRIMARY KEY,
	nombreCentroEscolar VARCHAR NOT NULL,
	descripcion VARCHAR NOT NULL,
	direccion VARCHAR NOT NULL,
	estado BOOLEAN NOT NULL,
	idMunicipio INTEGER NOT NULL,
	idDepartamento INTEGER NOT NULL
);
CREATE TABLE Alumno(
	codigoEstudiante SERIAL PRIMARY KEY,
	nombreEstudiante VARCHAR NOT NULL,
	apellidoEstudiante VARCHAR NOT NULL,
	carnetMinoridad VARCHAR(9),
	fechaNacimiento DATE NOT NULL,
	edad INTEGER NOT NULL,
	direccion VARCHAR NOT NULL,
	telefonoFijo VARCHAR(9),
	telefonoCelular VARCHAR(9),
	nombrePadre VARCHAR,
	nombreMadre VARCHAR,
	idCentroEscolar INTEGER NOT NULL
);
CREATE TABLE Materia(
	idMateria SERIAL PRIMARY KEY,
	anio INTEGER NOT NULL,
	ciclo INTEGER NOT NULL,
	nota FLOAT NOT NULL,
	resultado VARCHAR NOT NULL,
	idCatalogo INTEGER NOT NULL,
	idAlumno INTEGER NOT NULL
);

alter table authorities add foreign key(username) 
references users(username) on delete cascade on update cascade;

CREATE TABLE CatalogoMaterias(
	codigoMateria SERIAL PRIMARY KEY,
	nombre VARCHAR NOT NULL,
	estado BOOLEAN NOT NULL
);

ALTER TABLE Materia ADD FOREIGN KEY(idAlumno)
REFERENCES Alumno(codigoEstudiante);

ALTER TABLE Materia ADD FOREIGN KEY(idCatalogo)
REFERENCES CatalogoMaterias(codigoMateria);

ALTER TABLE users ADD FOREIGN KEY (idDepartamento) 
REFERENCES Departamento(idDepartamento);

ALTER TABLE users ADD FOREIGN KEY (idMunicipio)
REFERENCES Municipio(idMunicipio);

ALTER TABLE CentroEscolar ADD FOREIGN KEY (idMunicipio)
REFERENCES Municipio(idMunicipio);

ALTER TABLE CentroEscolar ADD FOREIGN KEY (idDepartamento)
REFERENCES Departamento(idDepartamento);

ALTER TABLE Alumno ADD FOREIGN KEY (idCentroEscolar)
REFERENCES CentroEscolar(idCentroEscolar);

INSERT INTO Municipio (idMunicipio,nombreMunicipio) VALUES (1,'San Salvador');
INSERT INTO Municipio (idMunicipio,nombreMunicipio) VALUES (2,'Santa Tecla');
INSERT INTO Municipio (idMunicipio,nombreMunicipio) VALUES (3,'La Libertad');
INSERT INTO Municipio (idMunicipio,nombreMunicipio) VALUES (4,'San Ignacio');
INSERT INTO Municipio (idMunicipio,nombreMunicipio) VALUES (5,'San Miguel');
INSERT INTO Municipio (idMunicipio,nombreMunicipio) VALUES (6,'Santa Ana');

INSERT INTO Departamento(idDepartamento,nombreDepartamento) VALUES (1,'San Salvador');
INSERT INTO Departamento(idDepartamento,nombreDepartamento) VALUES (2,'La Libertad');
INSERT INTO Departamento(idDepartamento,nombreDepartamento) VALUES (3,'Chalatenango');
INSERT INTO Departamento(idDepartamento,nombreDepartamento) VALUES (4,'San Miguel');
INSERT INTO Departamento(idDepartamento,nombreDepartamento) VALUES (5,'Santa Ana');

insert into users (username, password, enabled, nombre, apellido, fechaDeNacimiento, edad, idDepartamento, idMunicipio, direccion) values ('admin', crypt('administrador', gen_salt('bf')), true, 'admin', 'admin', '1/1/1990', 30, 1, 1, 'Santa Tecla');

insert into users (username, password, enabled, nombre, apellido, fechaDeNacimiento, edad, idDepartamento, idMunicipio, direccion) values ('coordinador', crypt('coordinador', gen_salt('bf')), true, 'coord', 'coord', '1/1/1990', 30, 1, 1, 'San Salvador');

INSERT INTO authorities(username, authority) VALUES ('admin','ADMIN');
INSERT INTO authorities(username, authority) VALUES ('coordinador', 'COORD');

INSERT INTO "centroescolar" (nombrecentroescolar,descripcion,direccion,estado,idmunicipio,iddepartamento) VALUES ('Cherokee','Antofagasta','5271 Non Rd.',true,1,3),('Uta','Sciacca','P.O. Box 696, 849 Ipsum St.',false,3,2),('Michael','Laramie','Ap #752-2027 Elit, St.',true,5,2),('Maggie','Juan Fernández','343-443 Lacus. Street',false,1,5),('Desirae','Chambord','9201 Mi St.',false,2,2),('Finn','Scarborough','P.O. Box 521, 1769 Et, Road',false,6,2),('Camille','Villafalletto','3671 Auctor. Street',false,6,1),('Brett','Ehein','P.O. Box 754, 2242 Rutrum, Avenue',true,2,1),('Ahmed','Blackwood','9298 Phasellus Av.',false,3,1),('Craig','Stupino','P.O. Box 313, 1102 Consectetuer Road',false,3,4);
INSERT INTO "centroescolar" (nombrecentroescolar,descripcion,direccion,estado,idmunicipio,iddepartamento) VALUES ('Aristotle','Mespelare','983-7133 Fermentum St.',true,4,2),('Susan','Minitonas','Ap #945-4266 Leo. Street',true,1,5),('Melvin','Malegaon','9648 Neque St.',true,2,2),('Maya','Burhaniye','733-4595 Euismod Road',true,4,4),('Ryder','Muradiye','P.O. Box 439, 9346 Ut, Rd.',false,5,2),('Arden','Macclesfield','P.O. Box 129, 8583 Cursus Ave',true,5,4),('Clare','Lowell','Ap #362-3710 Convallis Rd.',false,2,2),('Josephine','Llanelli','P.O. Box 784, 789 Libero. Road',false,4,5),('Dacey','Opole','P.O. Box 924, 4111 Phasellus Av.',true,3,2),('Veronica','Randazzo','P.O. Box 656, 199 Iaculis, Rd.',true,3,4);



INSERT INTO CatalogoMaterias(nombre, estado)
							values('Calculo 1', true),
								  ('Calculo 2', false),
								  ('Programacion n Capas', false),
								  ('Fisica 1', true),
								  ('Programacion Web', false),
								  ('Fisica 2', true),
								  ('Precalculo', false),
								  ('CDM', true),
                                  ('EMA', false),
                                  ('Analisis de Sistemas', false),
                                  ('Analisis Numerico', true),
                                  ('Artefactos', false),
                                  ('Algebra', true),
                                  ('Elementos', false);

INSERT INTO "alumno" (nombreestudiante,apellidoestudiante,carnetminoridad,fechanacimiento,edad,direccion,telefonofijo,telefonocelular,nombrepadre,nombremadre,idcentroescolar) VALUES ('Benjamin','Webster','1663062-4','2000/09/19',19,'2152 Ut, Road','22378300','70756751','Hayden Gibson','Kameko Madden',10),('Dalton','Randall','1609031-7','1996/06/10',27,'Ap #230-9457 Risus Rd.','21455701','76141160','Carson Blackburn','Adria Whitehead',12),('Xander','Woodard','1685043-3','2002/09/18',23,'2420 Diam Road','27812702','73018959','Armando Jordan','Althea Cole',15),('Cole','Solis','1635382-1','1991/07/19',19,'995 Tempus St.','29005613','71533314','Evan Holloway','Cassidy Cameron',15),('Jasmine','Castro','1644090-7','1997/10/13',16,'Ap #923-6777 Nullam Rd.','20219396','71012637','Abel Salazar','Colleen Rowe',6),('Nita','Palmer','1671102-2','1998/12/18',21,'1359 Parturient St.','24563662','74373336','Ryder Richmond','Karly Sherman',11),('Keegan','Thornton','1690018-7','1991/05/16',24,'5334 Odio. Ave','26984912','77539934','Edan Valentine','Leandra Reid',5),('Holly','Briggs','1635021-6','1996/06/23',15,'Ap #661-3341 At Av.','23842678','70377282','Malachi Macias','Madeson Sears',20),('Denton','Reid','1688011-9','1996/11/11',19,'429-4744 Aliquet Rd.','27954010','77209759','Ivan Roth','Daphne Flynn',5),('Tate','Estrada','1644072-9','1993/02/14',20,'P.O. Box 266, 8200 Integer St.','27715526','76952464','Lane Holman','Zorita Spears',6);
INSERT INTO "alumno" (nombreestudiante,apellidoestudiante,carnetminoridad,fechanacimiento,edad,direccion,telefonofijo,telefonocelular,nombrepadre,nombremadre,idcentroescolar) VALUES ('Colorado','Rich','1641082-9','2000/10/03',24,'P.O. Box 344, 2197 Semper Road','27894086','70181905','Oren Gregory','Venus Hutchinson',13),('Amelia','Mcclure','1661061-6','1992/05/07',16,'369 In Road','23478802','77023411','Randall Gilliam','Simone Vazquez',6),('Nissim','Lopez','1692033-3','1997/04/02',28,'P.O. Box 412, 7798 Iaculis, Rd.','24057225','79204708','Ahmed Ortiz','Mechelle Farmer',14),('Herman','Kirby','1695052-0','1995/06/01',19,'1045 Eget Ave','22072718','74091710','Logan Mitchell','Velma Washington',13),('Hope','Porter','1636042-7','2004/12/05',27,'Ap #816-9822 Hendrerit Road','28062314','77533377','Nigel Albert','Kay Sellers',6),('Rae','Sweeney','1687012-7','2004/05/08',28,'894 Purus St.','22457672','72612621','Octavius Mcintyre','Irene Contreras',11),('Graiden','Johnston','1692080-6','1995/01/28',16,'4968 Sagittis Street','28661769','77464115','Clinton Heath','Kylee Bishop',18),('Andrew','Rosales','1612011-9','1998/09/07',25,'3739 Blandit Avenue','25332176','79141358','Preston Johns','Destiny Melton',11),('Jameson','Mccormick','1689022-9','1995/08/31',23,'P.O. Box 251, 5110 Vitae St.','27586001','79249208','Gareth Ortega','Deborah Castaneda',9),('Curran','Cooke','1677042-2','2000/05/01',18,'P.O. Box 149, 4372 Ligula. Av.','22345835','78504681','Clayton Fuentes','Eliana Mcgee',5);

insert into "materia" (anio, ciclo, nota, resultado, idCatalogo,idAlumno) values (2018, 2, 9.0, 'APROBADO', 1, 1),
(2019, 1, 4.0, 'REPROBADO', 4, 1),
(2018, 2, 7.0, 'APROBADO', 9, 2),
(2017, 2, 6.0, 'REPROBADO', 5, 2),
(2017, 1, 7.0, 'APROBADO', 14, 3),
(2018, 3, 9.0, 'APROBADO', 13, 3),
(2018, 2, 7.0, 'APROBADO', 1, 4),
(2010, 2, 8.0, 'APROBADO', 2, 4),
(2012, 1, 5.5, 'REPROBADO', 7, 5),
(2015, 2, 6.7, 'APROBADO', 5, 5),
(2018, 2, 3.6, 'REPROBADO', 4, 6),
(2019, 1, 6.6, 'APROBADO', 7, 6),
(2018, 2, 5.0, 'REPROBADO', 6, 7);