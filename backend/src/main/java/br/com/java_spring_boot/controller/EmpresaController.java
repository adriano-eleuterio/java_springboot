package br.com.java_spring_boot.controller;

import br.com.java_spring_boot.model.Empresa;
import br.com.java_spring_boot.service.EmpresaService;
import br.com.java_spring_boot.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(FornecedorService fornecedorService, EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/listar")
    private ResponseEntity<Object> listarEmpresas() {
        List<Empresa> empresas = empresaService.listar();

        if (!empresas.isEmpty()) {
            return ResponseEntity.ok().body(empresas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresas não encontradas.");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Object> buscarEmpresaPorId(@PathVariable Long id) {
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscarPorCnpj/{cnpj}")
    public ResponseEntity<Object> buscarEmpresaPorCnpj(@PathVariable String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");
        Optional<Empresa> empresaOptional = Optional.ofNullable(empresaService.buscarPorCnpj(cnpj));
        try {
            if (empresaOptional.isPresent()) {
                Empresa empresa = empresaOptional.get();
                return ResponseEntity.ok().body(empresa);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar Empresa.");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/salvar")
    public ResponseEntity<Object> salvarEmpresa(@RequestBody @Valid Empresa empresa) {

        empresa.setCnpj(empresa.getCnpj().replaceAll("[^0-9]", ""));
        empresa.setCep(empresa.getCep().replaceAll("[^0-9]", ""));

        try {

            empresaService.salvar(empresa);
            return ResponseEntity.ok().body("Empresa salva com sucesso");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar empresa.");
        }

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarEmpresa(@PathVariable Long id,
            @RequestBody @Valid Empresa empresaAtualizada) {
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
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
