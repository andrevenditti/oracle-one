package br.com.alura.screensound.principal;

import br.com.alura.screensound.model.Artistas;
import br.com.alura.screensound.model.Musicas;
import br.com.alura.screensound.model.TipoArtista;
import br.com.alura.screensound.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final ArtistaRepository repositorio;
    private Scanner leitura = new Scanner(System.in);

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }
    public void exibeMenu() {
        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    *** Screen Sound Músicas ***                    
                                        
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                                    
                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarArtistas(){
        var cadastrarNovo = "S";

        while (cadastrarNovo.equalsIgnoreCase("s")){
            System.out.println("Informe o nome desse artista: ");
            var nome = leitura.nextLine();
            System.out.println("Informe o tipo desse artista: (solo, dupla ou banda)");
            var tipo = leitura.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artistas artista = new Artistas(nome, tipoArtista);
            repositorio.save(artista);
            System.out.println("Cadastrar novo artista? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }
    }

    private void cadastrarMusicas(){
        System.out.println("Cadastrar musica de que artista?");
        var nomeArtista = leitura.nextLine();
        Optional<Artistas> artista = repositorio.findByNomeContainingIgnoreCase(nomeArtista);
        if(artista.isPresent()) {
            System.out.println("Informe o título da musica:");
            var nomeMusica = leitura.nextLine();
            Musicas musicas = new Musicas(nomeMusica);
            musicas.setArtista(artista.get());
            artista.get().getMusicas().add(musicas);
            repositorio.save(artista.get());
        } else {
            System.out.println("Artista nao encontra");
        }
    }

    private void listarMusicas() {
        List<Artistas> artistas = repositorio.findAll();
        artistas.forEach(a -> a.getMusicas().forEach(m -> m.getTitulo()));
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Digite o nome do artista que você quer encontrar musicas?");
        var nomeArtista = leitura.nextLine();
        List<Musicas> musicas = repositorio.buscaMusicasPorArtistas(nomeArtista);
        musicas.forEach(System.out::println);
    }
}
