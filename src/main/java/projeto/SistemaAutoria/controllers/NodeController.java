package projeto.SistemaAutoria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.SistemaAutoria.entities.Node;
import projeto.SistemaAutoria.entities.dto.NodeDto;
import projeto.SistemaAutoria.entities.dto.Pager;
import projeto.SistemaAutoria.services.NodeService;

import java.util.List;

@RestController
@RequestMapping("/nodes")
public class NodeController {

    @Autowired
    private NodeService service;

    public ResponseEntity<List<Node>> findAll() {
        List<Node> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping
    public ResponseEntity<Pager<Node>> findAll(
            @PageableDefault() Pageable pageable,
            @RequestParam(required = false) String searchQuery,
            @RequestParam(required = true) long idHistoria
    ) {
        Pager<Node> list = service.findAll(pageable, searchQuery, idHistoria);
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
    public ResponseEntity<String> deleteTutorial(@PathVariable Long id) {
        String message = "";
        try {
            service.deleteById(id);
            message = "deletado com sucesso";

        } catch (Exception e) {
            message = e.getMessage();
        }
        return ResponseEntity.ok(message);
    }
}
