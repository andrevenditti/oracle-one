package br.com.alura.TabelaFipe.principal;

import br.com.alura.TabelaFipe.model.DadosVeiculos;
import br.com.alura.TabelaFipe.model.DetalhesVeiculos;
import br.com.alura.TabelaFipe.model.ModelosVeiculos;
import br.com.alura.TabelaFipe.service.ConsumoApi;
import br.com.alura.TabelaFipe.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura =  new Scanner(System.in);
    private final String URL_PRINCIPAL = "https://parallelum.com.br/fipe/api/v1/";
    private String endereco;
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    public void exibeMenu() {
        var menu = """
                *******Opcoes*******
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consultar:
                """;
        System.out.println(menu);
        var opcao = leitura.nextLine();

        if(opcao.toLowerCase().contains("car")) {
            endereco = URL_PRINCIPAL + "carros/marcas/";
            System.out.println(endereco);
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_PRINCIPAL + "motos/marcas/";
            System.out.println(endereco);
        } else if (opcao.toLowerCase().contains("cam")) {
            endereco = URL_PRINCIPAL + "caminhoes/marcas/";
            System.out.println(endereco);
        } else  {
            System.out.println("Opção inválida!");
            exibeMenu();
        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);

        var marcas = conversor.obterLista(json, DadosVeiculos.class);
        marcas.stream()
                .sorted(Comparator.comparing(DadosVeiculos::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");
        var codigoMarca = leitura.nextLine();
        endereco = endereco + codigoMarca + "/modelos/";
        json = consumo.obterDados(endereco);

        var modeloLista = conversor.obterDados(json, ModelosVeiculos.class);
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(DadosVeiculos::codigo))
                .forEach(System.out::println);

        System.out.println("Digite o trecho do nome do carro a ser buscado: ");
        var nomeVeiculo = leitura.nextLine();

        List<DadosVeiculos> modelosfiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("Modelos filtrados");
        modelosfiltrados.forEach(System.out::println);

        System.out.println("Digite, por favor, o codigo do modelo para buscar os valores de avaliacao: ");
        var codigoModelo = leitura.nextLine();

        endereco = endereco + codigoModelo + "/anos/";
        json = consumo.obterDados(endereco);
        List<DadosVeiculos> anos = conversor.obterLista(json, DadosVeiculos.class);
        anos.stream()
                .sorted(Comparator.comparing(DadosVeiculos::codigo))
                .forEach(v -> {
                            var enderecoAnos = endereco + v.codigo();
                            var jsonAnos = consumo.obterDados(enderecoAnos);
                            var detalhesVeiculo = conversor.obterDados(jsonAnos, DetalhesVeiculos.class);
                            System.out.println(detalhesVeiculo);
                        }
                        );

    }
}
