package br.edu.ifba.phonebook.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = "/contacts")
@Tag(name = "Contacts")
public class ContactControllerImpl implements ContactController {
    
    @Autowired
    private ContactService service;

    @Override
    @PostMapping
    @Transactional
    public ResponseEntity<ContactDtoResponse> save(
            @Valid
            @RequestBody ContactDtoRequest data,
            UriComponentsBuilder builder
    ){
		var dataSaved = service.save(data);
        var uri = builder.path("/contacts/{id}").buildAndExpand(dataSaved.id()).toUri();
    	return  ResponseEntity.created(uri).body(dataSaved);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ContactDtoResponse>> find(
            @RequestParam(required = false)
            String name,
            @PageableDefault(page = 0, size = 10, sort = {"name"})
            Pageable pageable
    ){
        var data = service.find(name, pageable);
        return ResponseEntity.ok().body(data);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<ContactDtoResponse> findById(
            @Parameter(description = "Contact Id to be searched")
            @PathVariable Long id
    ) {
        var data = service.findById(id);
        return ResponseEntity.ok().body(data);
    }

    @Override
    @PutMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<ContactDtoResponse> update(
            @PathVariable Long id,
            @RequestBody ContactDtoRequest data
    ) {
        service.findById(id);
        var dataUpdated = service.update(id, data);
        return ResponseEntity.ok().body(dataUpdated);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<ContactDtoResponse> deleteById(
            @PathVariable Long id
    ) {
        var data = service.findById(id);
        service.deleteById(id);
        return ResponseEntity.ok().body(data);
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
