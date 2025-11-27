package projeto.SistemaAutoria.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import projeto.SistemaAutoria.entities.Filme;
import projeto.SistemaAutoria.entities.FilmeResponse;

import java.util.List;

@Service
public class TmdbApiService {

    private final WebClient client;

    public TmdbApiService(WebClient client) {
        this.client = client;
    }

    public Filme getRandomPopularMovie() throws JsonProcessingException {
        int aleatorio = (int) (Math.random() * (10) + 1);
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=ec5b38eb9f18a28e8b8bfcd1b6944d6d&page=" + String.valueOf(aleatorio);
        String jsonString =  client.get().uri(url).retrieve().bodyToMono(String.class).block();
        ObjectMapper mapper = new ObjectMapper();
        FilmeResponse resp = mapper.readValue(jsonString, FilmeResponse.class);
        List<Filme> filmes = resp.filmes;
        aleatorio = (int) (Math.random() * (filmes.size() -2) + 1);
        return filmes.get(aleatorio);
    }
}
