package br.com.java_spring_boot.controller;

import br.com.java_spring_boot.model.Empresa;
import br.com.java_spring_boot.model.Fornecedor;
import br.com.java_spring_boot.service.EmpresaService;
import br.com.java_spring_boot.service.FornecedorService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    private final FornecedorService fornecedorService;
    private final EmpresaService empresaService;

    @Autowired
    public FornecedorController(FornecedorService fornecedorService, EmpresaService empresaService) {
        this.fornecedorService = fornecedorService;
        this.empresaService = empresaService;
    }

    @GetMapping("/listar")
    private ResponseEntity<Object> listarFornecedores() {
        List<Fornecedor> fornecedores = fornecedorService.listar();

        if (!fornecedores.isEmpty()) {
            return ResponseEntity.ok().body(fornecedores);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedores não encontrados.");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscarPorId/{id}")
    private ResponseEntity<Object> buscarFornecedorPorId(@PathVariable Long id) {
        Optional<Fornecedor> FornecedorOptional = fornecedorService.buscarPorId(id);
        try {
            if (FornecedorOptional.isPresent()) {

                Fornecedor Fornecedor = FornecedorOptional.get();
                return ResponseEntity.ok().body(Fornecedor);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar Fornecedor.");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscarPorNome/{nome}")
    public ResponseEntity<Object> buscarFornecedorPorNome(@PathVariable String nome) {
        Optional<Fornecedor> fornecedorOptional = Optional.ofNullable(fornecedorService.buscarPorNome(nome));

        try {
            if (fornecedorOptional.isPresent()) {

                Fornecedor fornecedor = fornecedorOptional.get();
                return ResponseEntity.ok().body(fornecedor);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar Fornecedor.");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscarPorCnpj/{cnpj}")
    public ResponseEntity<Object> buscarFornecedorPorCnpj(@PathVariable String cnpj) {

        Optional<Fornecedor> fornecedorOptional = Optional.ofNullable(fornecedorService.buscarPorCnpj(cnpj));
        try {

            if (fornecedorOptional.isPresent()) {
                Fornecedor fornecedor = fornecedorOptional.get();
                return ResponseEntity.ok().body(fornecedor);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar Fornecedor.");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/salvar")
    private ResponseEntity<Object> salvarFornecedor(@RequestBody @Valid Fornecedor fornecedor) {

        fornecedor.setCnpj(fornecedor.getCnpj().replaceAll("[^0-9]", ""));

        try {

            fornecedorService.salvar(fornecedor);
            return ResponseEntity.ok().body("Fornecedor salvo com sucesso");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar Fornecedor.");
        }

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/fornecedorEmpresa/{empresaId}/salvar")
    private ResponseEntity<Object> salvarFornecedorComEmpresa(@PathVariable Long empresaId, @RequestBody @Valid Fornecedor fornecedor) {

        fornecedor.setCnpj(fornecedor.getCnpj().replaceAll("[^0-9]", ""));

        Optional<Empresa> empresaOptional = empresaService.buscarPorId(empresaId);

        try {
            if (empresaOptional.isPresent()) {

                Empresa empresa = empresaOptional.get();
                Set<Empresa> empresas = new HashSet<>();
                empresas.add(empresa);
                fornecedor.setEmpresas(empresas);
                fornecedorService.salvar(fornecedor);
                return ResponseEntity.ok().body("Fornecedor vinculado à Empresa");

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada.");
            }
        } catch (
                Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao vincular Fornecedor à Empresa.");
        }
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/atualizar/{id}")
    private ResponseEntity<Object> atualizarFornecedor(@PathVariable Long id, @RequestBody @Valid Fornecedor fornecedorAtualizado) {
        Optional<Fornecedor> fornecedorOptional = fornecedorService.buscarPorId(id);

        try {
            if (fornecedorOptional.isPresent()) {

                Fornecedor fornecedor = fornecedorOptional.get();
                fornecedor.setNome(fornecedorAtualizado.getNome());
                fornecedor.setEmail(fornecedorAtualizado.getEmail());
                fornecedor.setCep(fornecedorAtualizado.getCep());

                fornecedorService.atualizar(fornecedor);
                return ResponseEntity.ok().body("Fornecedor atualizado com sucesso");

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar Fornecedor.");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    private ResponseEntity<Object> deletarFornecedor(@PathVariable Long id) {
        try {
            Optional<Fornecedor> FornecedorOptional = fornecedorService.buscarPorId(id);

            if (FornecedorOptional.isPresent()) {

                Fornecedor Fornecedor = FornecedorOptional.get();
                fornecedorService.deletar(Fornecedor);
                return ResponseEntity.ok().body("Fornecedor deletado com sucesso.");

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar Fornecedor.");
        }
    }

    @GetMapping("/buscarCep/{cep}")
    public ResponseEntity<Object> buscarPorCEP(@PathVariable String cep) {
        String apiUrl = "https://viacep.com.br/ws/" + cep + "/json/"; //cep.la

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.getForEntity(apiUrl, Object.class);

        return response;
    }

    /*
    @GetMapping("/buscarPorCEP/{cep}")
    public ResponseEntity<Object> buscarPorCEP(@PathVariable String cep) {
        String apiUrl = "https://viacep.com.br/ws/" + cep + "/json/";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.getForEntity(apiUrl, Object.class);

        return response;
    }
    */
}
