package projeto.SistemaAutoria.controllers;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.SistemaAutoria.entities.Historia;
import projeto.SistemaAutoria.entities.Node;
import projeto.SistemaAutoria.entities.dto.HistoriaDto;
import projeto.SistemaAutoria.services.HistoriaService;
import projeto.SistemaAutoria.services.NodeService;

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


    @PostMapping("/save-historia")
    public ResponseEntity<Long> saveHistoria(@RequestBody HistoriaDto historiaDto) {
        long id = service.saveHistoria(historiaDto);
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
