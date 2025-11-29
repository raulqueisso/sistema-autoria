package projeto.SistemaAutoria.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vw_relatorio_historia")
public class RelatorioHistoria {

    @Id
    @Column(name = "historia_id")
    private Long historiaId;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "total_nodes")
    private Long totalNodes;

    @Column(name = "total_links")
    private Long totalLinks;

    @Column(name = "nodes_sem_links")
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

    public RelatorioHistoria() {
    }

    public RelatorioHistoria(Long historiaId, String titulo, Long totalNodes, Long totalLinks, Long nodesSemLinks) {
        this.historiaId = historiaId;
        this.titulo = titulo;
        this.totalNodes = totalNodes;
        this.totalLinks = totalLinks;
        this.nodesSemLinks = nodesSemLinks;
    }
}