package br.edu.ifba.phonebook.controller;

import java.io.IOException;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

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
import kong.unirest.Unirest;

@RestController
@RequestMapping(path = "/contacts")
public class ContactController {
    
    @Autowired
    private ContactService service;

    @PostMapping
    public ResponseEntity<ContactDtoResponse> save(@RequestBody ContactDtoRequest data){
		var dataDto = service.save(data);
    	return new ResponseEntity<ContactDtoResponse>(dataDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ContactDtoResponse>> find(@RequestParam(required = false) String name){
        var data = service.find(name).get();
        var isExists = data.isEmpty();
        return isExists ? 
            ResponseEntity.notFound().build() : 
            ResponseEntity.ok().body(data);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ContactDtoResponse> findById(@PathVariable Long id) {
        return service.findById(id)
            .map(record -> ResponseEntity.ok().body(record))
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ContactDtoResponse> update(@PathVariable Long id, @RequestBody ContactDtoRequest data) {
        return service.findById(id)
        .map(record -> {
            var dataSaved = service.update(id, data);
            return ResponseEntity.ok().body(dataSaved);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ContactDtoResponse> delete(@PathVariable Long id) {
        return service.findById(id)
        .map(record -> {
            var data = record;
            service.deleteById(id);
            return ResponseEntity.ok().body(data);
        }).orElse(ResponseEntity.notFound().build());
    }

    public Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("C:\\Users\\aluno.ssa\\eclipse-workspace\\spring-phonebook\\phonebook\\src\\main\\resources\\application.properties");
		props.load(file);
		return props;

	}

    /*contact.numbers().stream().forEach(n -> {
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
