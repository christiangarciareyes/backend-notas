package com.utp.test.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utp.test.backend.domain.entity.Nota;
import com.utp.test.backend.domain.entity.Usuario;
import com.utp.test.backend.domain.entity.dto.NotaDTO;
import com.utp.test.backend.security.TokenUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private static TokenUtils tokenUtils;
    
    private static String JWT_TOKEN_TEST;
    
    @BeforeAll
    public static void setUp() {
    	
        // Se obtendrá el token JWT de prueba
    	
    	JWT_TOKEN_TEST = generarTokenJWT();
    	
    }

    // PRUEBAS UNITARIAS
    
    @Test
    public void testCrearListarNotas() throws Exception {
    	
        // Se creará una nueva nota
    	
        NotaDTO notaDTO = new NotaDTO();
        notaDTO.setTitulo("Titulo");
        notaDTO.setContenido("Contenido");
        notaDTO.setIdUsuario(1L);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = objectMapper.writeValueAsString(notaDTO);

        // Se creará la nota
        
        MvcResult crearNotaResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/notas")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
        		        .header("Authorization", "Bearer " + JWT_TOKEN_TEST))
                .andReturn();

        // Se verificará que la creación sea exitosa
        
        assertEquals(201, crearNotaResult.getResponse().getStatus());
        Nota notaCreada = objectMapper.readValue(crearNotaResult.getResponse().getContentAsString(), Nota.class);
        assertNotNull(notaCreada.getId());
        assertEquals("Titulo", notaCreada.getTitulo());
        assertEquals("Contenido", notaCreada.getContenido());
        assertEquals(1L, notaCreada.getIdUsuario());

        // Se listarán las notas
        
        MvcResult listarNotasResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/notas")
                        .contentType(MediaType.APPLICATION_JSON)
		                .header("Authorization", "Bearer " + JWT_TOKEN_TEST))
                .andReturn();

        // Se verificará que la lista no esté vacía
        
        assertEquals(200, listarNotasResult.getResponse().getStatus());
        List<Nota> listaNotas = objectMapper.readValue(listarNotasResult.getResponse().getContentAsString(), List.class);
        assertEquals(1, listaNotas.size());

    }

    @Test
    public void testCrearNotaTituloVacio() throws Exception {
    	
        // Se intentará crear una nota con título vacío
    	
        NotaDTO notaDTO = new NotaDTO();
        notaDTO.setTitulo("");
        notaDTO.setContenido("Contenido");
        notaDTO.setIdUsuario(1L);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = objectMapper.writeValueAsString(notaDTO);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/notas")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
		                .header("Authorization", "Bearer " + JWT_TOKEN_TEST))
                .andReturn();

        // Se verificará que la solicitud sea rechazada (400 Bad Request)
        
        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    public void testListarNotasVacias() throws Exception {
    	
        // Se listarán las notas cuando no hay ninguna creada
    	
        MvcResult listarNotasResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/notas")
                        .contentType(MediaType.APPLICATION_JSON)
		                .header("Authorization", "Bearer " + JWT_TOKEN_TEST))
                .andReturn();

        // Se verificará que la lista esté vacía
        
        assertEquals(200, listarNotasResult.getResponse().getStatus());
        List<Nota> listaNotas = objectMapper.readValue(listarNotasResult.getResponse().getContentAsString(), List.class);
        assertTrue(listaNotas.isEmpty());
    }
    
    // PRUEBAS DE ACEPTACIÓN
    
    @Test
    public void testListarNotas() {
        // Se configurará la URL base del servicio REST
    	RestAssured.baseURI = "http://localhost:8145";
    	Response response = given()
    	        .header("Authorization", "Bearer " + JWT_TOKEN_TEST)
    	    .when()
    	        .get("/notas/api/notas");

        // Se verificará que la respuesta tenga un código de estado HTTP 200 OK
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        // Se verificará que la respuesta contenga una lista de notas
        List<Nota> notas = response.jsonPath().getList(".", Nota.class);
        
    }
    
    @Test
    public void testListarUsuarios() {
        // Se configurará la URL base del servicio REST
    	RestAssured.baseURI = "http://localhost:8145";
    	Response response = given()
    	        .header("Authorization", "Bearer " + JWT_TOKEN_TEST)
    	    .when()
    	        .get("/notas/api/usuarios");

        // Se verificará que la respuesta tenga un código de estado HTTP 200 OK
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        // Se verificará que la respuesta contenga una lista de usuarios
        List<Usuario> usuarios = response.jsonPath().getList(".", Usuario.class);
        
        // Se verificará que haya 2 usuarios en la lista
        assertEquals(2, usuarios.size());
    }
    
    @Test
    public void testListarNotasUsuario() {
        // Se configurará la URL base del servicio REST
    	RestAssured.baseURI = "http://localhost:8145";
    	Response response = given()
    	        .header("Authorization", "Bearer " + JWT_TOKEN_TEST)
    	    .when()
    	        .get("/notas/api/usuarios/1");

        // Se verificará que la respuesta tenga un código de estado HTTP 200 OK
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        // Se verificará que la respuesta contenga una lista de notas
        List<Nota> notas = response.jsonPath().getList(".", Nota.class);
        
    }
    
    @Test
    public void testCrearNota() {
        RestAssured.baseURI = "http://localhost:8145";
        String requestBody = "{ \"titulo\": \"Titulo\", \"contenido\": \"Contenido\", \"idUsuario\": 1 }";

        Response response = given()
            .header("Authorization", "Bearer " + JWT_TOKEN_TEST)
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/notas/api/notas");

        // Se verificará que la respuesta tenga un código de estado HTTP 201 CREATED
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());

        // Se verificará que la respuesta contenga la nueva nota creada
        Nota nuevaNota = response.getBody().as(Nota.class);
        assertEquals("Titulo", nuevaNota.getTitulo());
        assertEquals("Contenido", nuevaNota.getContenido());
        assertEquals(1, nuevaNota.getIdUsuario());
    }
    
    private static String generarTokenJWT() {

    	return tokenUtils.createToken("Christian", "admin");
    	
    }

}


