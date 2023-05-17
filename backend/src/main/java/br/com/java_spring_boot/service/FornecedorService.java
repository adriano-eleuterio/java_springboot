package br.com.java_spring_boot.service;

import br.com.java_spring_boot.model.Fornecedor;
import br.com.java_spring_boot.repository.FornecedorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;

    public List<Fornecedor> listarFornecedores(){

        return fornecedorRepository.findAll();
    }
}
