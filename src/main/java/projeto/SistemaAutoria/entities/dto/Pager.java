package projeto.SistemaAutoria.entities.dto;

import java.util.List;

public record Pager<T>(List<T> content, int number, int size, int totalPages, long totalElements) {}