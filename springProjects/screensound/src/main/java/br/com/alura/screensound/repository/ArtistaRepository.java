package br.com.alura.screensound.repository;

import br.com.alura.screensound.model.Artistas;
import br.com.alura.screensound.model.Musicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artistas, Long> {
    Optional<Artistas> findByNomeContainingIgnoreCase(String nomeArtista);

    @Query("Select m from Artistas a JOIN a.musicas m WHERE a.nome ILIKE %:nomeArtista%")
    List<Musicas> buscaMusicasPorArtistas(String nomeArtista);
}
