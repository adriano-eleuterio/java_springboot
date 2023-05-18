import React, { useState } from 'react';

function EmpresaCadastro() {
  const [cnpj, setCnpj] = useState('');
  const [nomeFantasia, setNomeFantasia] = useState('');
  const [cep, setCep] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    const empresa = {
      cnpj: cnpj,
      nomeFantasia: nomeFantasia,
      cep: cep
    };

    try {
      const response = await fetch('http://localhost:8080/empresa/salvar', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(empresa)
      });

      if (response.ok) {
        alert('Empresa cadastrada com sucesso');
        // Limpar os campos após o cadastro
        setCnpj('');
        setNomeFantasia('');
        setCep('');
      } else {
        const errorMessage = await response.text();
        alert(`Erro ao cadastrar empresa: ${errorMessage}`);
      }
    } catch (error) {
      console.error(error);
      alert('Erro ao cadastrar empresa');
    }
  };

  return (
    <div className="form-container">
      <h1 className="form-title">Cadastrar Empresa</h1>
      <form onSubmit={handleSubmit} className="form-content">
        <div className="form-item">
          <label htmlFor="cnpj" className="form-label">CNPJ:</label>
          <input type="text" id="cnpj" name="cnpj" value={cnpj} onChange={(e) => setCnpj(e.target.value)} required className="form-input" />
        </div>
        <div className="form-item">
          <label htmlFor="nomeFantasia" className="form-label">Nome Fantasia:</label>
          <input type="text" id="nomeFantasia" name="nomeFantasia" value={nomeFantasia} onChange={(e) => setNomeFantasia(e.target.value)} required className="form-input" />
        </div>
        <div className="form-item">
          <label htmlFor="cep" className="form-label">CEP:</label>
          <input type="text" id="cep" name="cep" value={cep} onChange={(e) => setCep(e.target.value)} required className="form-input" />
        </div>
        <div>
          <button type="submit" className="form-button">Cadastrar</button>
        </div>
      </form>
    </div>
  );
}

export default EmpresaCadastro;