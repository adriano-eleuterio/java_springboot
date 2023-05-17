package br.com.java_spring_boot.service;

import br.com.java_spring_boot.model.Fornecedor;
import br.com.java_spring_boot.repository.FornecedorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    
    public List<Fornecedor> listar() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarPorId(Long id) {
        return fornecedorRepository.findById(id);
    }

    public void salvar(Fornecedor Fornecedor) {
        fornecedorRepository.save(Fornecedor);
    }

    public void atualizar(Fornecedor Fornecedor) {
        fornecedorRepository.save(Fornecedor);
    }

    public void deletar(Fornecedor Fornecedor) {
        fornecedorRepository.delete(Fornecedor);
    }
}
