package projeto.SistemaAutoria.entities.dto;

public record NodeDto(
        long id,
        String nome,
        String conteudo,
        int maximoAtivacoes,
        long historiaId,
        String historiaTitulo
) {
}
