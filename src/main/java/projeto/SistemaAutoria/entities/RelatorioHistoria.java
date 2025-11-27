package projeto.SistemaAutoria.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RelatorioHistoria {

    @Id
    private Long historiaId;

    private String titulo;
    private Long totalNodes;
    private Long totalLinks;
    private Long nodesSemLinks;

    public Long getHistoriaId() {
        return historiaId;
    }

    public void setHistoriaId(Long historiaId) {
        this.historiaId = historiaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getTotalNodes() {
        return totalNodes;
    }

    public void setTotalNodes(Long totalNodes) {
        this.totalNodes = totalNodes;
    }

    public Long getTotalLinks() {
        return totalLinks;
    }

    public void setTotalLinks(Long totalLinks) {
        this.totalLinks = totalLinks;
    }

    public Long getNodesSemLinks() {
        return nodesSemLinks;
    }

    public void setNodesSemLinks(Long nodesSemLinks) {
        this.nodesSemLinks = nodesSemLinks;
    }

}