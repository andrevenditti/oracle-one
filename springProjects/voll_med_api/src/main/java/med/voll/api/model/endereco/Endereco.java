package med.voll.api.model.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
    }

    public void atualizarInfos(DadosEndereco data) {
        if(data.logradouro() != null) {
            this.logradouro = data.logradouro();
        }
        if(data.bairro() != null) {
            this.bairro = data.bairro();
        }
        if(data.cep() != null) {
            this.cep = data.cep();
        }
        if(data.cidade() != null) {
            this.cidade = data.cidade();
        }
        if(data.uf() != null) {
            this.uf = data.uf();
        }
        if(data.numero() != null) {
            this.numero = data.numero();
        }
        if(data.complemento() != null) {
            this.complemento = data.complemento();
        }
    }
}
