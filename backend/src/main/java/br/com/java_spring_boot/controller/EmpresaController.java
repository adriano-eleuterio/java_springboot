package br.com.java_spring_boot.controller;

import br.com.java_spring_boot.model.Empresa;
import br.com.java_spring_boot.repository.EmpresaRepository;
import br.com.java_spring_boot.service.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresa")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmpresaController {

    private final EmpresaService empresaService;

    @GetMapping("/listar")
    private ResponseEntity<List<Empresa>> listarEmpresas(){
        List<Empresa> empresas = empresaService.listarEmpresas();
        return ResponseEntity.ok().body(empresas);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Empresa> salvarEmpresa(@RequestBody Empresa empresa) {
        empresaService.salvarEmpresa(empresa);
        return ResponseEntity.ok().body(empresa);
    }
}
