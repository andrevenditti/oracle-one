package med.voll.api.dto;

import med.voll.api.model.endereco.DadosEndereco;
import med.voll.api.model.endereco.Endereco;
import med.voll.api.model.medico.Especialidade;
import med.voll.api.model.medico.Medico;

public record MedicoDetalhesDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco
) {
    public MedicoDetalhesDTO(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.getEndereco()
        );
    }
}
