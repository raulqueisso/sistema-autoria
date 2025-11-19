package projeto.SistemaAutoria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.SistemaAutoria.entities.Node;
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

    @GetMapping("{id}")
    public ResponseEntity<Node> findById(@PathVariable long id) {
        Node obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }
}
