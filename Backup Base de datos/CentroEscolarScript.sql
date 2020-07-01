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
	codigoEstudiante INTEGER PRIMARY KEY,
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
	idCentroEscolar INTEGER NOT NULL,
	idMunicipio INTEGER NOT NULL
);

CREATE TABLE AlumnoXmateria(
	idAlumno INTEGER NOT NULL,
	idMateria INTEGER NOT NULL,
	notaMateria INTEGER NOT NULL
);

CREATE TABLE Materia(
	idMateria INTEGER PRIMARY KEY,
	nombreMateria VARCHAR NOT NULL,
	anio INTEGER NOT NULL,
	ciclo INTEGER NOT NULL,
	descripcion VARCHAR
);

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

