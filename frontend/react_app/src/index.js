import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import './my_style.css';
import reportWebVitals from './reportWebVitals';
import TelaPrincipal from './components/TelaPrincipal';

ReactDOM.render(
  <React.StrictMode>
    <TelaPrincipal />
  </React.StrictMode>,
  document.getElementById('root')
);

reportWebVitals();