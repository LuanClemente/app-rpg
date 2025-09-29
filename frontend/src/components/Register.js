// Componente de Cadastro de Usuário para o app RPG
// Permite criar uma nova conta integrando com o backend
import React, { useState } from 'react';

function Register({ onRegister, onBack }) {
  // Estados para os campos do formulário
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('PLAYER');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  // Função chamada ao submeter o formulário
  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');
    try {
      const response = await fetch(`${process.env.REACT_APP_API_URL || process.env.API_URL || '/api'}/auth/register`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, email, password, role })
      });
      const text = await response.text();
      if (!response.ok || text.includes('existe')) {
        throw new Error(text);
      }
      setSuccess('Usuário cadastrado com sucesso!');
      if (onRegister) onRegister();
    } catch (err) {
      setError(err.message);
    }
  };

  // Renderiza o formulário de cadastro
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
        <h2 className="rpg-login-title">Cadastro</h2>
        <form className="rpg-login-form" onSubmit={handleSubmit}>
          <div>
            <label>Usuário:</label>
            <input type="text" value={username} onChange={e => setUsername(e.target.value)} required />
          </div>
          <div>
            <label>Email:</label>
            <input type="email" value={email} onChange={e => setEmail(e.target.value)} required />
          </div>
          <div>
            <label>Senha:</label>
            <input type="password" value={password} onChange={e => setPassword(e.target.value)} required />
          </div>
          <div>
            <label>Papel:</label>
            <select value={role} onChange={e => setRole(e.target.value)}>
              <option value="PLAYER">Jogador</option>
              <option value="MASTER">Mestre</option>
            </select>
          </div>
          <button type="submit">Cadastrar</button>
        </form>
        {error && <p className="rpg-login-error">{error}</p>}
        {success && <p style={{ color: '#7fff7f', fontWeight: 'bold', marginTop: '0.5rem' }}>{success}</p>}
        <button style={{marginTop: '1rem'}} onClick={onBack}>Voltar</button>
      </div>
    </div>
  );
}

export default Register;
