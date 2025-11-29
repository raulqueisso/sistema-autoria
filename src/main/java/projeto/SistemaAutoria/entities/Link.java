package projeto.SistemaAutoria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "link")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "node_origem_id")
    private Node nodeOrigem;

    @ManyToOne
    @JoinColumn(name = "node_destino_id")
    private Node nodeDestino;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Node getNodeOrigem() {
        return nodeOrigem;
    }

    public void setNodeOrigem(Node nodeOrigem) {
        this.nodeOrigem = nodeOrigem;
    }

    public Node getNodeDestino() {
        return nodeDestino;
    }

    public void setNodeDestino(Node nodeDestino) {
        this.nodeDestino = nodeDestino;
    }

    public Link() {
    }

    public Link(long id, Node nodeOrigem, Node nodeDestino) {
        this.id = id;
        this.nodeOrigem = nodeOrigem;
        this.nodeDestino = nodeDestino;
    }
}
