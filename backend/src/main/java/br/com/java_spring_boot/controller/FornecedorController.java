package br.com.java_spring_boot.controller;

import br.com.java_spring_boot.model.Fornecedor;
import br.com.java_spring_boot.service.FornecedorService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedor")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @GetMapping("/listar")
    private ResponseEntity<Object> listarFornecedors() {
        List<Fornecedor> fornecedores = fornecedorService.listar();

        if (!fornecedores.isEmpty()) {
            return ResponseEntity.ok().body(fornecedores);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedores não encontrados.");
        }
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscarPorId/{id}")
    private ResponseEntity<Object> buscarPorIdFornecedor(@PathVariable Long id) {
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
    public ResponseEntity<Object> buscarPorNome(@PathVariable String nome) {
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
    public ResponseEntity<Object> buscarPorCNPJ(@PathVariable String cnpj) {
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
    private ResponseEntity<Object> salvarFornecedor(@RequestBody @Valid Fornecedor Fornecedor) {
        try {

            fornecedorService.salvar(Fornecedor);
            return ResponseEntity.ok().body("Fornecedor salvo com sucesso");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar Fornecedor.");
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

    @GetMapping("/buscarPorCep/{cep}")
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
