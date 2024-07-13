package br.com.alura.screenmatch.dto;

import java.time.LocalDate;

public record EpisodioDTO(
        Long id,
        Integer temporada,
        String titulo,
        Integer numeroEpisodio
) {
}
