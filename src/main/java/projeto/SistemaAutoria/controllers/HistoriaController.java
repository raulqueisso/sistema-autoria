package projeto.SistemaAutoria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.SistemaAutoria.entities.Historia;
import projeto.SistemaAutoria.services.HistoriaService;

import java.util.List;

@RestController
@RequestMapping("/historias")
public class HistoriaController {

    @Autowired
    private HistoriaService service;

    @GetMapping
    public ResponseEntity<List<Historia>> findAll() {
        List<Historia> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<Historia> findById(@PathVariable long id) {
        Historia obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }
}
