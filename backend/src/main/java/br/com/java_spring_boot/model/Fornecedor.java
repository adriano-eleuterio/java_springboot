package br.com.java_spring_boot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

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
    @Column(unique = true)
    private String nome;
    private String email;
    @Column(unique = true)
    private String CEP;


    @ManyToMany(mappedBy = "fornecedores")
    private List<Empresa> empresas;
}
