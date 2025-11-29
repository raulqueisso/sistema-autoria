package projeto.SistemaAutoria.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projeto.SistemaAutoria.entities.Filme;
import projeto.SistemaAutoria.services.GoogleTranslateApiService;
import projeto.SistemaAutoria.services.TmdbApiService;

@RestController
@RequestMapping("/tmdb-API")
public class FilmeController {

    @Autowired
    private TmdbApiService service;
    private GoogleTranslateApiService translateService;

    @GetMapping(value = "")
    public ResponseEntity<Filme> getFilme() throws JsonProcessingException {
        Filme filme =  service.getRandomPopularMovie();
        filme.setResumo(translateService.traduzir(filme.getResumo()));

        return ResponseEntity.ok(filme);
    }
}
