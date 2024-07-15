package med.voll.api.dto;

import med.voll.api.model.endereco.Endereco;
import med.voll.api.model.paciente.Paciente;

public record PacienteDetalhesDTO(
        Long id,
        String nome,
        String cpf,
        String telefone,
        Endereco endereco
) {
    public PacienteDetalhesDTO(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEndereco()
        );
    }
}
