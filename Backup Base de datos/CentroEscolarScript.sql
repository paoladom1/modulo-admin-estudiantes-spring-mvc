CREATE TABLE Usuario(
	idUsuario INTEGER PRIMARY KEY NOT NULL,
	nombre VARCHAR NOT NULL,
	apellido VARCHAR NOT NULL,
	nombreDeUsuario VARCHAR NOT NULL,
	contraseña VARCHAR NOT NULL,
	fechaDeNacimiento DATE NOT NULL,
	edad INTEGER NOT NULL,
	idDepartamento INTEGER NOT NULL,
	idMunicipio INTEGER NOT NULL,
	direccion VARCHAR,
	estado BOOLEAN NOT NULL,
	idTipoUsuario INTEGER NOT NULL
);

CREATE TABLE Departamento(
	idDepartamento INTEGER NOT NULL PRIMARY KEY,
	nombreDepartamento VARCHAR NOT NULL
);

CREATE TABLE Municipio(
	idMunicipio INTEGER PRIMARY KEY,
	nombreMunicipio VARCHAR NOT NULL
);
CREATE TABLE TipoUsuario(
	idTipoUsuario INTEGER PRIMARY KEY,
	tipoUsuario VARCHAR NOT NULL
);

CREATE TABLE CentroEscolar(
	idCentroEscolar INTEGER PRIMARY KEY,
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

CREATE TABLE CatalogoMaterias(
	codigoMateria SERIAL PRIMARY KEY,
	nombre VARCHAR NOT NULL,
	estado BOOLEAN NOT NULL
);

ALTER TABLE Materia ADD FOREIGN KEY(idAlumno)
REFERENCES Alumno(codigoEstudiante);

ALTER TABLE Materia ADD FOREIGN KEY(idCatalogo)
REFERENCES CatalogoMaterias(codigoMateria);

ALTER TABLE Usuario ADD FOREIGN KEY (idDepartamento) 
REFERENCES Departamento(idDepartamento);

ALTER TABLE Usuario ADD FOREIGN KEY (idTipoUsuario)
REFERENCES TipoUsuario(idTipoUsuario);

ALTER TABLE Usuario ADD FOREIGN KEY (idMunicipio)
REFERENCES Municipio(idMunicipio);

ALTER TABLE Alumno ADD FOREIGN KEY (idMunicipio)
REFERENCES Municipio(idMunicipio);

ALTER TABLE CentroEscolar ADD FOREIGN KEY (idMunicipio)
REFERENCES Municipio(idMunicipio);

ALTER TABLE CentroEscolar ADD FOREIGN KEY (idDepartamento)
REFERENCES Departamento(idDepartamento);

ALTER TABLE Alumno ADD FOREIGN KEY (idCentroEscolar)
REFERENCES CentroEscolar(idCentroEscolar);

ALTER TABLE AlumnoXmateria ADD FOREIGN KEY (idAlumno)
REFERENCES Alumno(codigoEstudiante);

ALTER TABLE AlumnoXmateria ADD FOREIGN KEY (idMateria)
REFERENCES Materia(idMateria);

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

INSERT INTO TipoUsuario(idTipoUsuario,tipoUsuario) VALUES (1,'Administrador');
INSERT INTO TipoUsuario(idTipoUsuario,tipoUsuario) VALUES (2,'Coordinador');

INSERT INTO Usuario(idUsuario,nombre,apellido,nombreDeUsuario,contraseña,fechaDeNacimiento,edad,
					idDepartamento,idMunicipio,estado,idTipoUsuario)
					VALUES (1,'admin','apellidoAdmin','root','root','1/1/1990',30,1,1,true,1);

INSERT INTO "centroescolar" (idcentroescolar,nombrecentroescolar,descripcion,direccion,idestado,idmunicipio,iddepartamento) VALUES (1,'Cherokee','Antofagasta','5271 Non Rd.',1,1,3),(2,'Uta','Sciacca','P.O. Box 696, 849 Ipsum St.',2,3,2),(3,'Michael','Laramie','Ap #752-2027 Elit, St.',1,5,2),(4,'Maggie','Juan Fernández','343-443 Lacus. Street',2,1,5),(5,'Desirae','Chambord','9201 Mi St.',2,2,2),(6,'Finn','Scarborough','P.O. Box 521, 1769 Et, Road',2,6,2),(7,'Camille','Villafalletto','3671 Auctor. Street',2,6,1),(8,'Brett','Ehein','P.O. Box 754, 2242 Rutrum, Avenue',1,2,1),(9,'Ahmed','Blackwood','9298 Phasellus Av.',2,3,1),(10,'Craig','Stupino','P.O. Box 313, 1102 Consectetuer Road',2,3,4);
INSERT INTO "centroescolar" (idcentroescolar,nombrecentroescolar,descripcion,direccion,idestado,idmunicipio,iddepartamento) VALUES (11,'Aristotle','Mespelare','983-7133 Fermentum St.',1,4,2),(12,'Susan','Minitonas','Ap #945-4266 Leo. Street',1,1,5),(13,'Melvin','Malegaon','9648 Neque St.',1,2,2),(14,'Maya','Burhaniye','733-4595 Euismod Road',1,4,4),(15,'Ryder','Muradiye','P.O. Box 439, 9346 Ut, Rd.',2,5,2),(16,'Arden','Macclesfield','P.O. Box 129, 8583 Cursus Ave',1,5,4),(17,'Clare','Lowell','Ap #362-3710 Convallis Rd.',1,2,2),(18,'Josephine','Llanelli','P.O. Box 784, 789 Libero. Road',2,4,5),(19,'Dacey','Opole','P.O. Box 924, 4111 Phasellus Av.',1,3,2),(20,'Veronica','Randazzo','P.O. Box 656, 199 Iaculis, Rd.',1,3,4);


<!--INSERT INTO Materia(idmateria, nombremateria,anio,ciclo,descripcion) VALUES (1,'matematica 1',1,1,'matematicas');
<!--INSERT INTO Materia(idmateria, nombremateria,anio,ciclo,descripcion) VALUES (2,'fisica 1',1,1,'fisica');
<!--INSERT INTO Materia(idmateria, nombremateria,anio,ciclo,descripcion) VALUES (3,'programacion',1,2,'programacion estructurada');
<!--INSERT INTO Materia(idmateria, nombremateria,anio,ciclo,descripcion) VALUES (4,'POO',1,1,'Programacion Orientada a Objetos');
<!--INSERT INTO Materia(idmateria, nombremateria,anio,ciclo,descripcion) VALUES (5,'Programacion N capas',1,1,'Programacion N capas');

INSERT INTO CatalogoMaterias(nombre, estado)
							values('Calculo 1', true),
								  ('Calculo 2', false),
								  ('Programacion n Capas', false),
								  ('Fisica 1', true),
								  ('Programacion Web', false),
								  ('Fisica 2', true),
								  ('Precalculo', false)
SELECT * FROM alumno;

INSERT INTO "alumno" (codigoestudiante,nombreestudiante,apellidoestudiante,carnetminoridad,fechanacimiento,edad,direccion,telefonofijo,telefonocelular,institucionprocedencia,nombrepadre,nombremadre,idcentroescolar) VALUES (1,'Benjamin','Webster','16630624 -4754','2000/09/19',19,'2152 Ut, Road','2237-8300','7075-6751','Jacqueline','Hayden Gibson','Kameko Madden',10),(2,'Dalton','Randall','16090314 -1257','1996/06/10',27,'Ap #230-9457 Risus Rd.','2145-5701','7614-1160','Sydnee','Carson Blackburn','Adria Whitehead',12),(3,'Xander','Woodard','16850430 -3853','2002/09/18',23,'2420 Diam Road','2781-2702','7301-8959','Caleb','Armando Jordan','Althea Cole',15),(4,'Cole','Solis','16350729 -3821','1991/07/19',19,'995 Tempus St.','2900-5613','7153-3314','Inga','Evan Holloway','Cassidy Cameron',15),(5,'Jasmine','Castro','16440920 -8867','1997/10/13',16,'Ap #923-6777 Nullam Rd.','2021-9396','7101-2637','Allegra','Abel Salazar','Colleen Rowe',6),(6,'Nita','Palmer','16711028 -0562','1998/12/18',21,'1359 Parturient St.','2456-3662','7437-3336','Aaron','Ryder Richmond','Karly Sherman',11),(7,'Keegan','Thornton','16900118 -0927','1991/05/16',24,'5334 Odio. Ave','2698-4912','7753-9934','Ross','Edan Valentine','Leandra Reid',5),(8,'Holly','Briggs','16350216 -4266','1996/06/23',15,'Ap #661-3341 At Av.','2384-2678','7037-7282','Merritt','Malachi Macias','Madeson Sears',20),(9,'Denton','Reid','16880114 -7359','1996/11/11',19,'429-4744 Aliquet Rd.','2795-4010','7720-9759','Ahmed','Ivan Roth','Daphne Flynn',5),(10,'Tate','Estrada','16440726 -9499','1993/02/14',20,'P.O. Box 266, 8200 Integer St.','2771-5526','7695-2464','Mollie','Lane Holman','Zorita Spears',6);
INSERT INTO "alumno" (codigoestudiante,nombreestudiante,apellidoestudiante,carnetminoridad,fechanacimiento,edad,direccion,telefonofijo,telefonocelular,institucionprocedencia,nombrepadre,nombremadre,idcentroescolar) VALUES (11,'Colorado','Rich','16410828 -0969','2000/10/03',24,'P.O. Box 344, 2197 Semper Road','2789-4086','7018-1905','Odessa','Oren Gregory','Venus Hutchinson',13),(12,'Amelia','Mcclure','16610617 -5596','1992/05/07',16,'369 In Road','2347-8802','7702-3411','Libby','Randall Gilliam','Simone Vazquez',6),(13,'Nissim','Lopez','16920313 -3468','1997/04/02',28,'P.O. Box 412, 7798 Iaculis, Rd.','2405-7225','7920-4708','Clio','Ahmed Ortiz','Mechelle Farmer',14),(14,'Herman','Kirby','16950522 -3520','1995/06/01',19,'1045 Eget Ave','2207-2718','7409-1710','Julian','Logan Mitchell','Velma Washington',13),(15,'Hope','Porter','16360429 -7600','2004/12/05',27,'Ap #816-9822 Hendrerit Road','2806-2314','7753-3377','Miranda','Nigel Albert','Kay Sellers',6),(16,'Rae','Sweeney','16870124 -8877','2004/05/08',28,'894 Purus St.','2245-7672','7261-2621','Astra','Octavius Mcintyre','Irene Contreras',11),(17,'Graiden','Johnston','16920809 -9326','1995/01/28',16,'4968 Sagittis Street','2866-1769','7746-4115','Clayton','Clinton Heath','Kylee Bishop',18),(18,'Andrew','Rosales','16120112 -8939','1998/09/07',25,'3739 Blandit Avenue','2533-2176','7914-1358','Samson','Preston Johns','Destiny Melton',11),(19,'Jameson','Mccormick','16890224 -3339','1995/08/31',23,'P.O. Box 251, 5110 Vitae St.','2758-6001','7924-9208','Maxwell','Gareth Ortega','Deborah Castaneda',9),(20,'Curran','Cooke','16770420 -1602','2000/05/01',18,'P.O. Box 149, 4372 Ligula. Av.','2234-5835','7850-4681','Ulric','Clayton Fuentes','Eliana Mcgee',5);

