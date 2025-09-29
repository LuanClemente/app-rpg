
// Componente principal da aplicação
// Gerencia autenticação e exibe tela de login ou ficha de personagem
import React, { useState } from 'react'; // Importa React e useState
import Login from './components/Login'; // Importa componente de Login
import CharacterSheet from './components/CharacterSheet'; // Importa componente de ficha

function App() {
  const [token, setToken] = useState(''); // Estado para armazenar o token JWT
  const [username, setUsername] = useState(''); // Estado para nome de usuário

  // Função chamada após login bem-sucedido
  const handleLogin = (jwt, user) => {
    setToken(jwt); // Salva o token JWT
    setUsername(user); // Salva o nome de usuário
  };

  // Se não estiver logado, exibe tela de login
  if (!token) {
    return <Login onLogin={handleLogin} />;
  }

  // Se estiver logado, exibe ficha de personagem
  return (
    <div>
      <h2>Bem-vindo, {username}!</h2>
      <CharacterSheet token={token} username={username} />
    </div>
  );
}

export default App; // Exporta o componente principal
