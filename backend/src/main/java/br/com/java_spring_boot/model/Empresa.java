package br.com.java_spring_boot.model;

import br.com.java_spring_boot.service.FornecedorService;
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
@Table(name = "tbl_empresa")
public class Empresa implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEmpresa;
    @Column(unique = true)
    private String cnpj;
    private String nomeFantasia;
    private String CEP;

    @ManyToMany
    @JoinTable(name = "empresa_fornecedor",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_id"))
    private List<Fornecedor> fornecedores;
}
