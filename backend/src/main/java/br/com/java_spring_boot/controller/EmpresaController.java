package br.com.java_spring_boot.controller;

import br.com.java_spring_boot.model.Empresa;
import br.com.java_spring_boot.service.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private ResponseEntity<Object> listarEmpresas() {
        List<Empresa> empresas = empresaService.listar();

        if (!empresas.isEmpty()) {
            return ResponseEntity.ok().body(empresas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresas não encontradas.");
        }
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Object> buscarPorIdEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresaOptional = empresaService.buscarPorId(id);
        try {
            if (empresaOptional.isPresent()) {

                Empresa empresa = empresaOptional.get();
                return ResponseEntity.ok().body(empresa);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar empresa.");
        }
    }


    @PostMapping("/salvar")
    public ResponseEntity<Object> salvarEmpresa(@RequestBody Empresa empresa) {
        try {

            empresaService.salvar(empresa);
            return ResponseEntity.ok().body("Empresa salva com sucesso");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar empresa.");
        }

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
        Optional<Empresa> empresaOptional = empresaService.buscarPorId(id);

        try {
            if (empresaOptional.isPresent()) {

                Empresa empresa = empresaOptional.get();
                empresa.setCep(empresaAtualizada.getCep());
                empresa.setNomeFantasia(empresaAtualizada.getNomeFantasia());
                empresaService.atualizar(empresa);
                return ResponseEntity.ok().body("Empresa atualizada com sucesso");

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar empresa.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarEmpresa(@PathVariable Long id) {
        try {
            Optional<Empresa> empresaOptional = empresaService.buscarPorId(id);

            if (empresaOptional.isPresent()) {

                Empresa empresa = empresaOptional.get();
                empresaService.deletar(empresa);
                return ResponseEntity.ok().body("Empresa deletada com sucesso.");

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar empresa.");
        }
    }
}
