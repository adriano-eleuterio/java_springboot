import React, { useState, useRef } from 'react';

function BuscarFornecedor() {
  const cnpjRef = useRef('');
  const [fornecedor, setFornecedor] = useState(null);

  const handleBuscarFornecedor = async (event) => {
    event.preventDefault();
    const cnpj = cnpjRef.current.value;

    try {
      const response = await fetch(`http://localhost:8080/fornecedor/buscarPorCnpj/${cnpj}`);
      if (response.ok) {
        const fornecedor = await response.json();
        setFornecedor(fornecedor);
      } else {
        setFornecedor(null);
        const errorMessage = await response.text();
        alert(`Erro ao buscar fornecedor: ${errorMessage}`);
      }
    } catch (error) {
      console.error(error);
      setFornecedor(null);
      alert('Erro ao buscar fornecedor');
    }
  };

  return (
    <div className="form-container">
      <h1 className="form-title">Buscar Fornecedor</h1>
      <form onSubmit={handleBuscarFornecedor} className="form-content">
        <div className="form-item">
          <label htmlFor="cnpj" className="form-label">CNPJ:</label>
          <input
            type="text"
            id="cnpj"
            name="cnpj"
            defaultValue={cnpjRef.current.value}
            ref={cnpjRef}
            required
            className="form-input"
          />
        </div>
        <div>
          <button type="submit" className="form-button">Buscar Fornecedor</button>
        </div>
      </form>
      {fornecedor && (
        <div>
          <h2>Dados do Fornecedor</h2>
          <p>CNPJ: {fornecedor.cnpj}</p>
          <p>Nome: {fornecedor.nome}</p>
          <p>Email: {fornecedor.email}</p>
          <p>CEP: {fornecedor.cep}</p>
        </div>
      )}
    </div>
  );
}

export default BuscarFornecedor;