import React, { useState } from 'react';

function FornecedorCadastro() {
  const [cnpj, setCnpj] = useState('');
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [cep, setCep] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    const fornecedor = {
      cnpj: cnpj,
      nome: nome,
      email: email,
      cep: cep
    };

    try {
      const response = await fetch('http://localhost:8080/fornecedor/salvar', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(fornecedor)
      });

      if (response.ok) {
        alert('Fornecedor cadastrado com sucesso');
        // Limpar os campos após o cadastro
        setCnpj('');
        setNome('');
        setEmail('');
        setCep('');
      } else {
        const errorMessage = await response.text();
        alert(`Erro ao cadastrar fornecedor: ${errorMessage}`);
      }
    } catch (error) {
      console.error(error);
      alert('Erro ao cadastrar fornecedor');
    }
  };

  return (
    <div className="form-container">
      <h1 className="form-title">Cadastrar Fornecedor</h1>
      <form onSubmit={handleSubmit} className="form-content">
        <div className="form-item">
          <label htmlFor="cnpj" className="form-label">CNPJ:</label>
          <input type="text" id="cnpj" name="cnpj" value={cnpj} onChange={(e) => setCnpj(e.target.value)} required className="form-input" />
        </div>
        <div className="form-item">
          <label htmlFor="nome" className="form-label">Nome:</label>
          <input type="text" id="nome" name="nome" value={nome} onChange={(e) => setNome(e.target.value)} required className="form-input" />
        </div>
        <div className="form-item">
          <label htmlFor="email" className="form-label">Email:</label>
          <input type="email" id="email" name="email" value={email} onChange={(e) => setEmail(e.target.value)} required className="form-input" />
        </div>
        <div className="form-item">
          <label htmlFor="cep" className="form-label">CEP:</label>
          <input type="text" id="cep" name="cep" value={cep} onChange={(e) => setCep(e.target.value)} required className="form-input" />
        </div>
        <button type="submit" className="form-button">Cadastrar</button>
      </form>
    </div>
  );
}

export default FornecedorCadastro;