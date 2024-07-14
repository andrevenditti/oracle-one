package med.voll.api.dto;

import med.voll.api.model.medico.Especialidade;
import med.voll.api.model.medico.Medico;

public record MedicoListagemDTO(
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public MedicoListagemDTO(Medico medico) {
        this(
            medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade()
        );
    }
}
