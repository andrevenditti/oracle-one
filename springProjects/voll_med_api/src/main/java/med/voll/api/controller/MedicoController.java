package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.MedicoAtualizaDTO;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.dto.MedicoListagemDTO;
import med.voll.api.model.medico.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoDTO data) {
        repository.save(new Medico(data));
    }
    @GetMapping
    public Page<MedicoListagemDTO> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao)
                .map(MedicoListagemDTO::new);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid MedicoAtualizaDTO data) {
        var medico = repository.getReferenceById(data.id());
         medico.atualizaInfos(data);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
