package projeto.SistemaAutoria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "node")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String conteudo;
    private int maximoAtivacoes;

    @JsonIgnore
    @OneToMany(mappedBy = "nodeOrigem")
    private Set<Link> links;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "historia_id")
    private Historia historia;

    public Node() {
    }

    public Node(long id, String nome, String conteudo, int maximoAtivacoes, Set<Link> links, Historia historia) {
        this.id = id;
        this.nome = nome;
        this.conteudo = conteudo;
        this.maximoAtivacoes = maximoAtivacoes;
        this.links = links;
        this.historia = historia;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getMaximoAtivacoes() {
        return maximoAtivacoes;
    }

    public void setMaximoAtivacoes(int maximoAtivacoes) {
        this.maximoAtivacoes = maximoAtivacoes;
    }

    public Set<Link> getLinks() {
        return links;
    }

    public Historia getHistoria() {
        return historia;
    }

    public void setHistoria(Historia historia) {
        this.historia = historia;
    }
}
