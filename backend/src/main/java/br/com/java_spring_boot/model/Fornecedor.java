package br.com.java_spring_boot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
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
    @CNPJ(message = "CNPJ Inválido")
    private String cnpj;
    @CPF(message = "CPF Inválido")
    private String cpf;
    @Column(nullable = false)
    private String nome;
    @Email(message = "E-Mail Inválido")
    private String email;
    @Column(nullable = false)
    private String cep;
    private String rg;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private Boolean pessoaFisica = false;


    @ManyToMany(mappedBy = "fornecedores")
    private Set<Empresa> empresas;
}
