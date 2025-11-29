package projeto.SistemaAutoria.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import projeto.SistemaAutoria.entities.Filme;
import projeto.SistemaAutoria.entities.FilmeResponse;

import java.util.List;

@Service
public class TmdbApiService {

    private final WebClient client;

    @Value("${tmdbApi}")
    private String tmdbKey;

    public TmdbApiService(WebClient client) {
        this.client = client;
    }

    public Filme getRandomPopularMovie() throws JsonProcessingException {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + tmdbKey + "&page=1";
        String jsonString =  client.get().uri(url).retrieve().bodyToMono(String.class).block();

        ObjectMapper mapper = new ObjectMapper();
        FilmeResponse resp = mapper.readValue(jsonString, FilmeResponse.class);
        List<Filme> filmes = resp.filmes;

        return filmes.get(0);
    }
}
