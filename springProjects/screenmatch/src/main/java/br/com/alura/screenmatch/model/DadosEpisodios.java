package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodios(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Episode") int numeroEpisodio,
        @JsonAlias("imdbRating")String avaliacao,
        @JsonAlias("Released") String dataDeLancamento
) {
}
