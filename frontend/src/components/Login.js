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
  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
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
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </div>
  );
}

export default Login;
