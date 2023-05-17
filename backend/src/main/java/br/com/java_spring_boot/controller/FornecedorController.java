package br.com.java_spring_boot.controller;

import br.com.java_spring_boot.model.Fornecedor;
import br.com.java_spring_boot.service.FornecedorService;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedor")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @GetMapping("/listar")
    private ResponseEntity<Object> listarFornecedors() {
        List<Fornecedor> Fornecedors = fornecedorService.listar();

        if (!Fornecedors.isEmpty()) {
            return ResponseEntity.ok().body(Fornecedors);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedores não encontrados.");
        }
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Object> buscarPorIdFornecedor(@PathVariable Long id) {
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


    @PostMapping("/salvar")
    public ResponseEntity<Object> salvarFornecedor(@RequestBody Fornecedor Fornecedor) {
        try {

            fornecedorService.salvar(Fornecedor);
            return ResponseEntity.ok().body("Fornecedor salvo com sucesso");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar Fornecedor.");
        }

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarFornecedor(@PathVariable Long id, @RequestBody Fornecedor fornecedorAtualizado) {
        Optional<Fornecedor> fornecedorOptional = fornecedorService.buscarPorId(id);

        try {
            if (fornecedorOptional.isPresent()) {

                Fornecedor fornecedor = fornecedorOptional.get();
                fornecedor.setNome(fornecedorAtualizado.getNome());
                fornecedor.setEmail(fornecedorAtualizado.getEmail());
                fornecedor.setCEP(fornecedorAtualizado.getCEP());

                fornecedorService.atualizar(fornecedor);
                return ResponseEntity.ok().body("Fornecedor atualizado com sucesso");

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar Fornecedor.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarFornecedor(@PathVariable Long id) {
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
}
