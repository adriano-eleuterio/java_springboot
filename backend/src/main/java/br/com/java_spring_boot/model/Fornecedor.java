package br.com.java_spring_boot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_fornecedor")
public class Fornecedor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFornecedor;
    @Column(unique = true)
    private String cnpj;
    private String cpf;
    @Column(nullable = false)
    private String nome;
    private String email;
    @Column(nullable = false)
    private String cep;
    private String rg;
    private LocalDate dataNascimento;
    private Boolean pessoaFisica = false;


    @ManyToMany(mappedBy = "fornecedores")
    private Set<Empresa> empresas;
}
