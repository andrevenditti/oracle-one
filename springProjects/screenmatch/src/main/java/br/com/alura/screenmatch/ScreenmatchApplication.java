package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodios;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=breaking+bad&apikey=ef6f5091");
		System.out.println(json);

		//dados da serie
		ConverteDados converteDados = new ConverteDados();
		DadosSerie dadosSerie = converteDados.obertDados(json, DadosSerie.class);
		System.out.println(dadosSerie);

		//dados de um episodio consumido da API
		json = consumoApi.obterDados("https://www.omdbapi.com/?t=breaking+bad&Season=2&episode=2&apikey=ef6f5091");
		DadosEpisodios dadosEpisodios = converteDados.obertDados(json, DadosEpisodios.class);
		System.out.println(dadosEpisodios);
	}
}
