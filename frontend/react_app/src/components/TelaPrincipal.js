import React, { useState } from 'react';
import CadastrarEmpresa from './CadastrarEmpresa';
import CadastrarFornecedor from './CadastrarFornecedor';
import BuscarFornecedor from './BuscarFornecedor';
import BuscarEmpresa from './BuscarEmpresa';

function TelaPrincipal() {
  const [mostrarEmpresaCadastro, setMostrarEmpresaCadastro] = useState(false);
  const [mostrarFornecedorCadastro, setMostrarFornecedorCadastro] = useState(false);
  const [mostrarBuscarFornecedor, setMostrarBuscarFornecedor] = useState(false);
  const [mostrarBuscarEmpresa, setMostrarBuscarEmpresa] = useState(false);

  const handleEmpresaCadastroClick = () => {
    setMostrarEmpresaCadastro(true);
    setMostrarFornecedorCadastro(false);
    setMostrarBuscarFornecedor(false);
    setMostrarBuscarEmpresa(false);
  };

  const handleFornecedorCadastroClick = () => {
    setMostrarEmpresaCadastro(false);
    setMostrarFornecedorCadastro(true);
    setMostrarBuscarFornecedor(false);
    setMostrarBuscarEmpresa(false);
  };

  const handleBuscarFornecedorClick = () => {
    setMostrarEmpresaCadastro(false);
    setMostrarFornecedorCadastro(false);
    setMostrarBuscarFornecedor(true);
    setMostrarBuscarEmpresa(false);
  };

  const handleBuscarEmpresaClick = () => {
    setMostrarEmpresaCadastro(false);
    setMostrarFornecedorCadastro(false);
    setMostrarBuscarFornecedor(false);
    setMostrarBuscarEmpresa(true);
  };

  return (
    <div className="tela-principal">
      <h1>Tela Principal</h1>
      <div className="botoes-container">
        <button className="botao-cadastro" onClick={handleEmpresaCadastroClick}>
          Cadastrar Empresa
        </button>
        <button className="botao-cadastro" onClick={handleFornecedorCadastroClick}>
          Cadastrar Fornecedor
        </button>
        <button className="botao-cadastro" onClick={handleBuscarEmpresaClick}>
          Buscar Empresa
        </button>
        <button className="botao-cadastro" onClick={handleBuscarFornecedorClick}>
          Buscar Fornecedor
        </button>
      </div>
      {mostrarEmpresaCadastro && <CadastrarEmpresa />}
      {mostrarFornecedorCadastro && <CadastrarFornecedor />}
      {mostrarBuscarEmpresa && <BuscarEmpresa />}
      {mostrarBuscarFornecedor && <BuscarFornecedor />}
    </div>
  );
}

export default TelaPrincipal;