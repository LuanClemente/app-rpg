
// Componente principal da aplicação
// Gerencia autenticação e exibe tela de login ou ficha de personagem
import React, { useState, useEffect } from 'react'; // Importa React e hooks
import Login from './components/Login'; // Importa componente de Login
import Register from './components/Register'; // Importa componente de Cadastro
import CharacterSheet from './components/CharacterSheet'; // Importa componente de ficha

function App() {
  // Inicializa o estado com os dados do localStorage, se existirem
  const [token, setToken] = useState(localStorage.getItem('jwt_token') || '');
  const [username, setUsername] = useState(localStorage.getItem('username') || '');
  const [showRegister, setShowRegister] = useState(false); // Estado para alternar entre login e cadastro

  // Efeito para persistir os dados no localStorage sempre que mudarem
  useEffect(() => {
    if (token && username) {
      localStorage.setItem('jwt_token', token);
      localStorage.setItem('username', username);
    } else {
      localStorage.removeItem('jwt_token');
      localStorage.removeItem('username');
    }
  }, [token, username]);

  // Função chamada após login bem-sucedido
  const handleLogin = (jwt, user) => {
    setToken(jwt); // Salva o token JWT
    setUsername(user); // Salva o nome de usuário
  };

  // Função chamada após cadastro bem-sucedido
  const handleRegister = () => {
    setShowRegister(false); // Volta para tela de login
  };

  // Função para fazer logout
  const handleLogout = () => {
    setToken('');
    setUsername('');
  };

  // Se não estiver logado, exibe tela de login ou cadastro
  if (!token) {
    return showRegister ? (
      <Register onRegister={handleRegister} onBack={() => setShowRegister(false)} />
    ) : (
      <Login onLogin={handleLogin} onRegister={() => setShowRegister(true)} />
    );
  }

  // Se estiver logado, exibe ficha de personagem
  return (
    <div>
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <h2>Bem-vindo, {username}!</h2>
        <button onClick={handleLogout} style={{ height: 'fit-content' }}>Sair</button>
      </div>
      <CharacterSheet token={token} username={username} />
    </div>
  );
}

export default App; // Exporta o componente principal
