package br.com.alura.screenMatchFrases.service;

import br.com.alura.screenMatchFrases.dto.FraseDTO;
import br.com.alura.screenMatchFrases.model.Frase;
import br.com.alura.screenMatchFrases.repository.FraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FraseService {
    @Autowired
    private FraseRepository repositorio;
    public FraseDTO obterFraseAleatoria() {
        Frase frase = repositorio.buscaFraseAleatoria();
        return new FraseDTO(frase.getTitulo(),
                frase.getFrase(),
                frase.getPersonagem(),
                frase.getPoster());
    }
}
