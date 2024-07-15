package med.voll.api.model.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.PacienteAtualizaDTO;
import med.voll.api.dto.PacienteDTO;
import med.voll.api.model.endereco.Endereco;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private Endereco endereco;

    public Paciente(PacienteDTO data) {
        this.nome = data.nome();
        this.email = data.email();
        this.cpf = data.cpf();
        this.telefone = data.telefone();
        this.endereco = new Endereco(data.endereco());
    }

    public void atualizaInfos(PacienteAtualizaDTO data) {
        if(data.nome() != null) {
            this.nome = data.nome();
        }
        if(data.telefone() != null) {
            this.telefone = data.telefone();
        }
        if(data.endereco() != null) {
            this.endereco.atualizarInfos(data.endereco());
        }
    }
}
