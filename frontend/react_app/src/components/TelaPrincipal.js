import React, { useState } from 'react';
import CadastrarEmpresa from './CadastrarEmpresa';
import CadastrarFornecedor from './CadastrarFornecedor';

function TelaPrincipal() {
  const [mostrarEmpresaCadastro, setMostrarEmpresaCadastro] = useState(false);
  const [mostrarFornecedorCadastro, setMostrarFornecedorCadastro] = useState(false);

  const handleEmpresaCadastroClick = () => {
    setMostrarEmpresaCadastro(true);
    setMostrarFornecedorCadastro(false);
  };

  const handleFornecedorCadastroClick = () => {
    setMostrarEmpresaCadastro(false);
    setMostrarFornecedorCadastro(true);
  };

  return (
    <div className="tela-principal">
      <h1>Tela de Cadastro</h1>
      <div className="botoes-container">
        <button className="botao-cadastro" onClick={handleEmpresaCadastroClick}>
          Cadastrar Empresa
        </button>
        <button className="botao-cadastro" onClick={handleFornecedorCadastroClick}>
          Cadastrar Fornecedor
        </button>
      </div>
      {/* Renderiza o formulário de cadastro da empresa se mostrarEmpresaCadastro for true */}
      {mostrarEmpresaCadastro && <CadastrarEmpresa />}
      {/* Renderiza o formulário de cadastro do fornecedor se mostrarFornecedorCadastro for true */}
      {mostrarFornecedorCadastro && <CadastrarFornecedor />}
    </div>
  );
}

export default TelaPrincipal;