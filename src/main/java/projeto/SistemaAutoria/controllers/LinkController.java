package projeto.SistemaAutoria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.SistemaAutoria.entities.Link;
import projeto.SistemaAutoria.entities.dto.HistoriaDto;
import projeto.SistemaAutoria.entities.dto.LinkDto;
import projeto.SistemaAutoria.services.LinkService;

import java.util.List;

@RestController
@RequestMapping("/links")
public class LinkController {

    @Autowired
    private LinkService service;

    @GetMapping()
    public ResponseEntity<List<Link>> findAll() {
        List<Link> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<Link> findById(@PathVariable long id) {
        Link obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping("/save-link")
    public ResponseEntity<Long> saveLink(@RequestBody LinkDto linkDto) {
        long id = service.saveLink(linkDto);
        return ResponseEntity.ok(id);
    }



    @GetMapping("/delete/{id}")
    public String deleteTutorial(@PathVariable Long id) {
        String message = "";
        try {
            service.deleteById(id);
            message = "deletado com sucesso";

        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }
}
