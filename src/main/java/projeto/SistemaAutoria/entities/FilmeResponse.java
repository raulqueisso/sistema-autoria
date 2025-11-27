package projeto.SistemaAutoria.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmeResponse {
    @JsonProperty("page")
    public int pagina;

    @JsonProperty("results")
    public List<Filme> filmes;
}