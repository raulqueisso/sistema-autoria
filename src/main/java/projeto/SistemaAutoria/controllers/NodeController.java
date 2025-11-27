package projeto.SistemaAutoria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.SistemaAutoria.entities.Node;
import projeto.SistemaAutoria.entities.dto.NodeDto;
import projeto.SistemaAutoria.services.NodeService;

import java.util.List;

@RestController
@RequestMapping("/nodes")
public class NodeController {

    @Autowired
    private NodeService service;

    @GetMapping()
    public ResponseEntity<List<Node>> findAll() {
        List<Node> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("sem-links/{idHistoria}")
    public ResponseEntity<List<Node>> findNodesSemLinks(@PathVariable long idHistoria) {
        List<Node> list = service.findNodeSemLinks(idHistoria);
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<Node> findById(@PathVariable long id) {
        Node obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping("/save-node")
    public ResponseEntity<Long> saveNode(@RequestBody NodeDto nodeDto) {
        long id = service.saveNode(nodeDto);
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
