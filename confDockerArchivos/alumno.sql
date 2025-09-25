USE acda;

CREATE TABLE Alumnos (
    AlumnoID INT AUTO_INCREMENT,
    Nombre VARCHAR(50),
    Apellidos VARCHAR(50),
    F_nacimiento DATE,
    PRIMARY KEY (AlumnoID)
);

INSERT INTO Alumnos (Nombre, Apellidos, F_nacimiento) VALUES
('Juan', 'Pérez', '1985-06-15'),
('María', 'López', '1990-09-23'),
('Carlos', 'Ramírez', '1978-12-04'),
('Lucía', 'Gómez', '1995-01-16'),
('Ana', 'Martínez', '1988-03-30');