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

    @OneToOne
    @JoinColumn(name = "node_inicial_id")
    private Node nodeInicial;

    @OneToMany(mappedBy = "historia")
    private Set<Node> nodes;

    public Historia() {
    }

    public Historia(long id, String titulo, Node nodeInicial, Set<Node> nodes) {
        this.id = id;
        this.titulo = titulo;
        this.nodeInicial = nodeInicial;
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

    public Node getNodeInicial() {
        return nodeInicial;
    }

    public void setNodeInicial(Node nodeInicial) {
        this.nodeInicial = nodeInicial;
    }

    public Set<Node> getNodes() {
        return nodes;
    }
}
