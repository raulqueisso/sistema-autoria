package projeto.SistemaAutoria.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Filme {

    @JsonProperty("title")
    private String titulo;

    @JsonProperty("vote_average")
    private float nota;

    @JsonProperty("overview")
    private String resumo;

    private boolean traduzido;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public boolean getTraduzido() {
        return traduzido;
    }

    public void setTraduzido(boolean traduzido) {
        this.traduzido = traduzido;
    }

    public Filme() {
        this.traduzido = false;
    }

    public Filme(String titulo, float nota, String resumo) {
        this.titulo = titulo;
        this.nota = nota;
        this.resumo = resumo;
        this.traduzido = false;
    }

    public Filme(String titulo, float nota, String resumo,boolean traduzido) {
        this.titulo = titulo;
        this.nota = nota;
        this.resumo = resumo;
        this.traduzido = traduzido;
    }
}
