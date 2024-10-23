CREATE SEQUENCE paciente_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE paciente (
      id BIGINT PRIMARY KEY DEFAULT NEXTVAL('paciente_sequence'),
      dni VARCHAR(50) NOT NULL,
      nombre VARCHAR(100) NOT NULL,
      email VARCHAR(100) NOT NULL,
      fecha_nacimiento DATE NOT NULL
);
