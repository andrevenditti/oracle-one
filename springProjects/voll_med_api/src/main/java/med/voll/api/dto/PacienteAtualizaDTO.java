package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.endereco.DadosEndereco;

public record PacienteAtualizaDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
