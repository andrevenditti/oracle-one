package med.voll.api.dto;

import med.voll.api.model.paciente.Paciente;

public record PacienteListagemDTO(
        String nome,
        String email,
        String cpf
) {
    public PacienteListagemDTO(Paciente paciente) {
        this(
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf()
        );
    }
}
