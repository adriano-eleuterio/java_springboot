package br.com.java_spring_boot.service;

import br.com.java_spring_boot.model.Fornecedor;
import br.com.java_spring_boot.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

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

    public Fornecedor buscarPorNome(String nome) {
        return fornecedorRepository.findByNome(nome);
    }

    public Fornecedor buscarPorCnpj(String cnpj) {
        return fornecedorRepository.findByCnpj(cnpj);
    }
}
