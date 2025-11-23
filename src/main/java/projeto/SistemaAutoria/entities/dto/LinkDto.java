package projeto.SistemaAutoria.entities.dto;

public record LinkDto(
        long id,
        long nodeOrigemId,
        long nodeDestinoId
) {
}
