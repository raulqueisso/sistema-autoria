package projeto.SistemaAutoria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.SistemaAutoria.entities.Link;
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
}
