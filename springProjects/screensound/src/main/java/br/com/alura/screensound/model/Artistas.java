package br.com.alura.screensound.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "artistas")
public class Artistas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoArtista tipo;
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musicas> musicas = new ArrayList<>();

    public Artistas(){}
    public Artistas(String nome, TipoArtista tipoArtista) {
        this.nome = nome;
        this.tipo = tipoArtista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoArtista getTipo() {
        return tipo;
    }

    public void setTipo(TipoArtista tipo) {
        this.tipo = tipo;
    }

    public List<Musicas> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musicas> musicas) {
        this.musicas = musicas;
    }

    @Override
    public String toString() {
        return  "Artista='" + nome + '\''+
                ", tipo=" + tipo +
                ", musicas=" + musicas;
    }
}
