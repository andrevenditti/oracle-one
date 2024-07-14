package med.voll.api.dto;

import med.voll.api.model.endereco.DadosEndereco;
import med.voll.api.model.medico.Especialidade;

public record MedicosDTO(
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        DadosEndereco endereco
) {
}
