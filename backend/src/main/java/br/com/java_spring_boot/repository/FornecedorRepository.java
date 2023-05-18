package br.com.java_spring_boot.repository;

import br.com.java_spring_boot.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    Fornecedor findByNome(String nome);
    Fornecedor findByCnpj(String cnpj);

}
