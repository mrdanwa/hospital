CREATE SEQUENCE historial_medico_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE vacuna_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE historial_medico
(
    id          BIGINT PRIMARY KEY DEFAULT NEXTVAL('historial_medico_sequence'),
    descripcion VARCHAR(255) NOT NULL,
    fecha       DATE         NOT NULL,
    paciente_id BIGINT       NOT NULL,
    CONSTRAINT fk_paciente_historial FOREIGN KEY (paciente_id) REFERENCES paciente (id) ON DELETE CASCADE
);

CREATE TABLE vacuna
(
    id          BIGINT PRIMARY KEY DEFAULT NEXTVAL('vacuna_sequence'),
    vacuna      VARCHAR(255) NOT NULL,
    fecha       DATE         NOT NULL,
    paciente_id BIGINT       NOT NULL,
    CONSTRAINT fk_paciente_vacuna FOREIGN KEY (paciente_id) REFERENCES paciente (id) ON DELETE CASCADE
);
