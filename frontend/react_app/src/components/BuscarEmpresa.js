import React, { useState, useRef } from 'react';

function BuscarEmpresa() {
  const cnpjRef = useRef('');
  const [empresa, setEmpresa] = useState(null);

  const handleBuscarEmpresa = async (event) => {
    event.preventDefault();
    const cnpj = cnpjRef.current.value;

    try {
      const response = await fetch(`http://localhost:8080/empresa/buscarPorCnpj/${cnpj}`);
      if (response.ok) {
        const empresa = await response.json();
        setEmpresa(empresa);
      } else {
        setEmpresa(null);
        const errorMessage = await response.text();
        alert(`Erro ao buscar empresa: ${errorMessage}`);
      }
    } catch (error) {
      console.error(error);
      setEmpresa(null);
      alert('Erro ao buscar empresa');
    }
  };

  return (
    <div className="form-container">
      <h1 className="form-title">Buscar Empresa</h1>
      <form onSubmit={handleBuscarEmpresa} className="form-content">
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
          <button type="submit" className="form-button">Buscar Empresa</button>
        </div>
      </form>
      {empresa && (
        <div>
          <h2>Dados da Empresa</h2>
          <p>CNPJ: {empresa.cnpj}</p>
          <p>Nome Fantasia: {empresa.nomeFantasia}</p>
          <p>CEP: {empresa.cep}</p>
        </div>
      )}
    </div>
  );
}

export default BuscarEmpresa;