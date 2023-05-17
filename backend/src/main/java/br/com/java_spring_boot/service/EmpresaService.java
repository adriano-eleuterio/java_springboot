package br.com.java_spring_boot.service;

import br.com.java_spring_boot.model.Empresa;
import br.com.java_spring_boot.repository.EmpresaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmpresaService {
    private final EmpresaRepository empresaRepository;


    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> buscarPorId(Long id) {
        return empresaRepository.findById(id);
    }
    public void salvarEmpresa (Empresa empresa){
        empresaRepository.save(empresa);
    }

    public void atualizarEmpresa (Empresa empresa){
            empresaRepository.save(empresa);
    }

    public void deletarEmpresa(Empresa empresa){
        empresaRepository.delete(empresa);
    }
}
