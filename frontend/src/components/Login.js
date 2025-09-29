// Componente de Login para autenticação do usuário
// Exibe formulário de login e chama a função onLogin ao autenticar com sucesso
import React, { useState } from 'react';

function Login({ onLogin }) {
  // Estados para armazenar usuário e senha digitados
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  // Função chamada ao submeter o formulário
  const handleSubmit = async (e) => {
    e.preventDefault(); // Evita recarregar a página
    setError(''); // Limpa erro anterior
    try {
      // Faz requisição para o endpoint de login do backend
      const response = await fetch(`${process.env.REACT_APP_API_URL || process.env.API_URL || '/api'}/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
      });
      if (!response.ok) {
        throw new Error('Usuário ou senha inválidos');
      }
      const data = await response.json();
      // Chama função recebida por props passando o token e o usuário
      onLogin(data.token, username);
    } catch (err) {
      setError(err.message);
    }
  };

  // Renderiza o formulário de login
  // Permite acessar cadastro se prop onRegister existir
  return (
    <div className="rpg-bg">
      <div className="rpg-login-container">
        <div style={{marginBottom: '1.5rem'}}>
          {/* Ícone temático D&D (d20) */}
          <svg width="48" height="48" viewBox="0 0 48 48" fill="none" style={{marginBottom: '0.5rem'}} xmlns="http://www.w3.org/2000/svg">
            <polygon points="24,4 44,14 44,34 24,44 4,34 4,14" fill="#bfa76a" stroke="#6b4f1d" strokeWidth="2"/>
            <text x="24" y="30" textAnchor="middle" fontSize="18" fontFamily="Cinzel Decorative, serif" fill="#2d1a06">d20</text>
          </svg>
        </div>
        <h2 className="rpg-login-title">Login RPG</h2>
        <form className="rpg-login-form" onSubmit={handleSubmit}>
          <div>
            <label>Usuário:</label>
            <input type="text" value={username} onChange={e => setUsername(e.target.value)} required />
          </div>
          <div>
            <label>Senha:</label>
            <input type="password" value={password} onChange={e => setPassword(e.target.value)} required />
          </div>
          <button type="submit">Entrar</button>
        </form>
        {error && <p className="rpg-login-error">{error}</p>}
        {/* Botão para acessar cadastro, se prop onRegister existir */}
        {typeof onRegister === 'function' && (
          <button style={{marginTop: '1rem'}} onClick={onRegister}>Cadastrar novo usuário</button>
        )}
      </div>
    </div>
  );
}

export default Login;
