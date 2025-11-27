package projeto.SistemaAutoria.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.SistemaAutoria.entities.AuditLog;
import projeto.SistemaAutoria.entities.RelatorioHistoria;
import projeto.SistemaAutoria.services.RelatorioHistoriaService;

import java.util.List;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioHistoriaService service;



    @GetMapping("/historia")
    public ResponseEntity<List<RelatorioHistoria>> findAll() {
        List<RelatorioHistoria> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/historia/{id}")
    public ResponseEntity<RelatorioHistoria> findById(@PathVariable long id) {
        RelatorioHistoria obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("audit")
    public ResponseEntity<List<AuditLog>> findAllAudit(){
        List<AuditLog> list = service.findAllAudit();
        return ResponseEntity.ok(list);
    }
}
