package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.PacienteAtualizaDTO;
import med.voll.api.dto.PacienteDTO;
import med.voll.api.dto.PacienteListagemDTO;
import med.voll.api.model.paciente.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PacienteDTO data) {
        repository.save(new Paciente(data));
    }
    @GetMapping
    public Page<PacienteListagemDTO> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao)
                .map(PacienteListagemDTO::new);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid PacienteAtualizaDTO data) {
        var paciente = repository.getReferenceById(data.id());
        paciente.atualizaInfos(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }
}
