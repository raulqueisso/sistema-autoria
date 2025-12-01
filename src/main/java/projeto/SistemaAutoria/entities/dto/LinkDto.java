package projeto.SistemaAutoria.entities.dto;

public record LinkDto(
        long id,
        long nodeOrigemId,
        String nodeOrigemNome,
        long nodeDestinoId,
        String nodeDestinoNome
) {
}
