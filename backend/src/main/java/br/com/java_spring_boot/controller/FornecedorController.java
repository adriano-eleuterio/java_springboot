package br.com.java_spring_boot.controller;

import br.com.java_spring_boot.model.Empresa;
import br.com.java_spring_boot.model.Fornecedor;
import br.com.java_spring_boot.service.EmpresaService;
import br.com.java_spring_boot.service.FornecedorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fornecedor")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @GetMapping("/listar")
    public ResponseEntity<List<Fornecedor>> listarEmpresas(){
        List<Fornecedor> fornecedores = fornecedorService.listarFornecedores();

        return ResponseEntity.ok().body(fornecedores);
    }

}
