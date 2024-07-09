package br.com.alura.TabelaFipe.principal;

import br.com.alura.TabelaFipe.model.DadosVeiculos;
import br.com.alura.TabelaFipe.service.ConsumoApi;
import br.com.alura.TabelaFipe.service.ConverteDados;

import java.util.Comparator;
import java.util.Scanner;

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
            endereco = URL_PRINCIPAL + "carros/marcas";
            System.out.println(endereco);
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_PRINCIPAL + "motos/marcas";
            System.out.println(endereco);
        } else if (opcao.toLowerCase().contains("cam")) {
            endereco = URL_PRINCIPAL + "caminhoes/marcas";
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
    }
}
