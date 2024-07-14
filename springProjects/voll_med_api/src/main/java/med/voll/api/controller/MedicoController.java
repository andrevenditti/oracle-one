package med.voll.api.controller;

import med.voll.api.dto.MedicosDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @PostMapping
    public void cadastrar(@RequestBody MedicosDTO json) {
        System.out.println(json);
    }
}
