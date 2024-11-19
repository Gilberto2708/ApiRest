package com.example.contactlistapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.contactlistapi.entity.Contact;
import com.example.contactlistapi.service.ContactService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ContactController {

    @Autowired
    private final ContactService contactRepository;

    @Operation(summary = "Obtener todos los contactos", description = "Devuelve una lista de todos los contactos disponibles en la base de datos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de contactos obtenida correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/getAllContacts")
    public Iterable<Contact> getAllContacts() {
        return contactRepository.getAllContacts();
    }

    @Operation(summary = "Obtener un contacto por ID", description = "Devuelve un contacto específico según su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contacto encontrado"),
        @ApiResponse(responseCode = "404", description = "Contacto no encontrado")
    })
    @GetMapping("/getById/{id}")
    public Contact getById(
        @Parameter(description = "ID del contacto a buscar", required = true) @PathVariable Integer id) {
        return contactRepository.findById(id);
    }

    @Operation(summary = "Crear un nuevo contacto", description = "Agrega un nuevo contacto a la base de datos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contacto creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping("/create")
    public Contact create(
        @Parameter(description = "Datos del nuevo contacto", required = true)@Valid @RequestBody Contact contact) {
        return contactRepository.createContact(contact);
    }

    @Operation(summary = "Actualizar un contacto existente", description = "Modifica los datos de un contacto existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contacto actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Contacto no encontrado")
    })
    @PutMapping("/update/{id}")
    public Contact update(
        @Parameter(description = "ID del contacto a actualizar", required = true) @PathVariable Integer id,
        @Parameter(description = "Datos actualizados del contacto", required = true) @RequestBody Contact contact) {
        return contactRepository.updateContact(id, contact);
    }

    @Operation(summary = "Eliminar un contacto", description = "Elimina un contacto de la base de datos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contacto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Contacto no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public void delete(
        @Parameter(description = "ID del contacto a eliminar", required = true) @PathVariable Integer id) {
        contactRepository.deleteContact(id);
    }

    @Operation(summary = "Probar la API", description = "Devuelve un mensaje de prueba para verificar que la API está funcionando.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Respuesta exitosa")
    })
    @GetMapping("/test")
    public String test() {
        return "Esta es mi primera API en Spring Boot";
    }
}
