package br.edu.ifba.phonebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.phonebook.domain.dto.request.ContactDtoRequest;
import br.edu.ifba.phonebook.domain.dto.response.ContactDtoResponse;
import br.edu.ifba.phonebook.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/contacts")
@Tag(name = "Contacts")
public class ContactController {
    
    @Autowired
    private ContactService service;

    @PostMapping
    @Operation(summary = "Save only one contact")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201", 
                description = "Saved with success", 
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ContactDtoResponse.class)
                    )
                }    
            ),
            @ApiResponse(
                responseCode = "406", 
                description = "Not Acceptable", 
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ContactDtoResponse.class)
                    )
                }    
            )
        }
    )
    public ResponseEntity<ContactDtoResponse> save(@Parameter(description = "New contact body content to be created") @RequestBody ContactDtoRequest data){
		var dataDto = service.save(data);
    	return new ResponseEntity<>(dataDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retrieve all contacts with or without filter")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", 
                description = "Retrieval of successful contacts", 
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ContactDtoResponse.class)
                    )
                }    
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "Not found", 
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ContactDtoResponse.class)
                    )
                }    
            )
        }
    )
    public ResponseEntity<List<ContactDtoResponse>> find(@Parameter(description = "Name for contact to be found (optional)") @RequestParam(required = false) String name){
        var data = service.find(name).get();
        var isExists = data.isEmpty();
        return isExists ? 
            ResponseEntity.notFound().build() : 
            ResponseEntity.ok().body(data);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Retrieve tip by id")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", 
                description = "Retrieval of successful", 
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ContactDtoResponse.class)
                    )
                }    
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "Not found", 
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ContactDtoResponse.class)
                    )
                }    
            )
        }
    )
    public ResponseEntity<ContactDtoResponse> findById(@Parameter(description = "Contact Id to be searched") @PathVariable Long id) {
        return service.findById(id)
            .map(record -> ResponseEntity.ok().body(record))
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Update only one contact")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", 
                description = "Updated with successful", 
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ContactDtoResponse.class)
                    )
                }    
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "Not found", 
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ContactDtoResponse.class)
                    )
                }    
            )
        }
    )
    public ResponseEntity<ContactDtoResponse> update(@Parameter(description = "Contact Id to be updated") @PathVariable Long id, @Parameter(description = "Contact Elements/Body Content to be updated") @RequestBody ContactDtoRequest data) {
        return service.findById(id)
        .map(record -> {
            var dataSaved = service.update(id, data);
            return ResponseEntity.ok().body(dataSaved);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete only one contact")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", 
                description = "Deleted with successful", 
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ContactDtoResponse.class)
                    )
                }    
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "Not found", 
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ContactDtoResponse.class)
                    )
                }    
            )
        }
    )
    public ResponseEntity<ContactDtoResponse> deleteById(@Parameter(description = "Contact Id to be deleted") @PathVariable Long id) {
        return service.findById(id)
        .map(record -> {
            service.deleteById(id);
            return ResponseEntity.ok().body(record);
        }).orElse(ResponseEntity.notFound().build());
    }

    /* Teste para envio de SMS */
    /*public Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("C:\\Users\\aluno.ssa\\eclipse-workspace\\spring-phonebook\\phonebook\\src\\main\\resources\\application.properties");
		props.load(file);
		return props;

	}

    contact.numbers().stream().forEach(n -> {
        try {
            Properties prop = getProp();
            
            String route = prop.getProperty("devsms.route");
            String token = prop.getProperty("devsms.token");
            String type = prop.getProperty("devsms.type");
            String message = prop.getProperty("devsms.message");
            String number = n.telephone();
            
            String url = route + "?key=" + token + "&type=" + type + "&number=" + number + "&msg=" + message;
            Unirest.get(url).asString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ;
    });*/
}
