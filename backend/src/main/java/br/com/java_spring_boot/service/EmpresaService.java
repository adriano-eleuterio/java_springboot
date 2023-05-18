package br.com.java_spring_boot.service;

import br.com.java_spring_boot.model.Empresa;
import br.com.java_spring_boot.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<Empresa> listar() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> buscarPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public void salvar(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    public void atualizar(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    public void deletar(Empresa empresa) {
        empresaRepository.delete(empresa);
    }

    public Empresa buscarPorCnpj(String cnpj) {
        return empresaRepository.findByCnpj(cnpj);
    }
}
