package med.voll.api.dto;

import med.voll.api.model.endereco.DadosEndereco;

public record PacienteDTO(
        String nome,
        String email,
        String cpf,
        String telefone,
        DadosEndereco endereco
) {
}
