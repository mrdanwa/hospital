package com.accenture.hospital;

import com.accenture.hospital.paciente.Paciente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HospitalApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void testListarPacientes() throws Exception {
		// Añadimos dos pacientes antes de listar
		Paciente paciente1 = new Paciente("55555555D", "Ana Martínez", "ana.martinez@example.com", LocalDate.of(1992, 3, 10));
		Paciente paciente2 = new Paciente("66666666E", "Luis Rodríguez", "luis.rodriguez@example.com", LocalDate.of(1988, 7, 22));

		mockMvc.perform(post("/api/v1/paciente")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(paciente1)))
				.andExpect(status().isOk());

		mockMvc.perform(post("/api/v1/paciente")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(paciente2)))
				.andExpect(status().isOk());

		// Ahora realizamos la petición GET para listar los pacientes
		mockMvc.perform(get("/api/v1/paciente"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				// Verificamos que los pacientes agregados están en la lista
				.andExpect(jsonPath("$[?(@.dni == '55555555D')].nombre").value("Ana Martínez"))
				.andExpect(jsonPath("$[?(@.dni == '66666666E')].nombre").value("Luis Rodríguez"));
	}

	@Test
	void testAnadirPaciente() throws Exception {
		// Prueba para el endpoint POST /api/v1/paciente
		// Creamos un nuevo paciente y lo enviamos en el cuerpo de la petición
		Paciente paciente = new Paciente("12345678A", "Juan Pérez", "juan.perez@example.com", LocalDate.of(1990, 1, 1));

		mockMvc.perform(post("/api/v1/paciente")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(paciente)))
				.andExpect(status().isOk());
	}

	@Test
	void testBorrarPaciente() throws Exception {
		// Prueba para el endpoint DELETE /api/v1/paciente/{id}
		// Añadimos un paciente para obtener un ID válido
		Paciente paciente = new Paciente("87654321B", "María López", "maria.lopez@example.com", LocalDate.of(1985, 5, 15));

		mockMvc.perform(post("/api/v1/paciente")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(paciente)))
				.andExpect(status().isOk());

		// Obtenemos la lista de pacientes para encontrar el ID
		String respuesta = mockMvc.perform(get("/api/v1/paciente"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		Paciente[] pacientes = objectMapper.readValue(respuesta, Paciente[].class);

		Long id = null;
		for (Paciente p : pacientes) {
			if (p.getDni().equals(paciente.getDni())) {
				id = p.getId();
				break;
			}
		}

		if (id == null) {
			throw new Exception("Paciente no encontrado para eliminar");
		}

		// Borramos el paciente utilizando el ID obtenido
		mockMvc.perform(delete("/api/v1/paciente/{id}", id))
				.andExpect(status().isOk());
	}

	@Test
	void testActualizarPaciente() throws Exception {
		// Prueba para el endpoint PUT /api/v1/paciente/{id}
		// Añadimos un paciente para obtener un ID válido
		Paciente paciente = new Paciente("11223344C", "Carlos Sánchez", "carlos.sanchez@example.com", LocalDate.of(1975, 12, 31));

		mockMvc.perform(post("/api/v1/paciente")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(paciente)))
				.andExpect(status().isOk());

		// Obtenemos la lista de pacientes para encontrar el ID
		String respuesta = mockMvc.perform(get("/api/v1/paciente"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		Paciente[] pacientes = objectMapper.readValue(respuesta, Paciente[].class);

		Long id = null;
		for (Paciente p : pacientes) {
			if (p.getDni().equals(paciente.getDni())) {
				id = p.getId();
				break;
			}
		}

		if (id == null) {
			throw new Exception("Paciente no encontrado para actualizar");
		}

		// Actualizamos el nombre y el email del paciente
		String nuevoNombre = "Carlos Gómez";
		String nuevoEmail = "carlos.gomez@example.com";

		mockMvc.perform(put("/api/v1/paciente/{id}", id)
						.param("nombre", nuevoNombre)
						.param("email", nuevoEmail))
				.andExpect(status().isOk());
	}
}
