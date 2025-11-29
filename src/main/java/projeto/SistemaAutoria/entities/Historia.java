package projeto.SistemaAutoria.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;

@Entity
@Table(name = "historia")
public class Historia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;

    @OneToMany(mappedBy = "historia", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Node> nodes;

    public Historia() {
    }

    public Historia(long id, String titulo, Set<Node> nodes) {
        this.id = id;
        this.titulo = titulo;
        this.nodes = nodes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Node> getNodes() {
        return nodes;
    }
}
