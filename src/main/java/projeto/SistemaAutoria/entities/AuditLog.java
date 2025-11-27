package projeto.SistemaAutoria.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tabela;

    private String operacao;

    @Column(name = "registro_id")
    private Long registroId;

    // MySQL JSON -> pode mapear como String + @Lob
    @Lob
    @Column(columnDefinition = "json")
    private String dadosAntes;

    @Lob
    @Column(columnDefinition = "json")
    private String dadosDepois;

    @Column(name = "data_evento")
    private LocalDateTime dataEvento;

    // -----------------------
    // Constructors
    // -----------------------
    public AuditLog() {}

    public AuditLog(String tabela, String operacao, Long registroId, String dadosAntes, String dadosDepois, LocalDateTime dataOperacao) {
        this.tabela = tabela;
        this.operacao = operacao;
        this.registroId = registroId;
        this.dadosAntes = dadosAntes;
        this.dadosDepois = dadosDepois;
        this.dataEvento = dataOperacao;
    }

    // -----------------------
    // Getters / Setters
    // -----------------------

    public Long getId() {
        return id;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Long getRegistroId() {
        return registroId;
    }

    public void setRegistroId(Long registroId) {
        this.registroId = registroId;
    }

    public String getDadosAntes() {
        return dadosAntes;
    }

    public void setDadosAntes(String dadosAntes) {
        this.dadosAntes = dadosAntes;
    }

    public String getDadosDepois() {
        return dadosDepois;
    }

    public void setDadosDepois(String dadosDepois) {
        this.dadosDepois = dadosDepois;
    }

    public LocalDateTime getDataOperacao() {
        return dataEvento;
    }

    public void setDataOperacao(LocalDateTime dataOperacao) {
        this.dataEvento = dataOperacao;
    }
}
