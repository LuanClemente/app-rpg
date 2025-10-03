// Componente de Ficha de Personagem
// Exibe os dados da ficha do usuário autenticado
import React, { useEffect, useState } from 'react'; // Importa React e hooks

function CharacterSheet({ token, username }) { // Recebe token JWT e nome de usuário
  const [sheet, setSheet] = useState(null); // Estado para ficha de personagem
  const [error, setError] = useState(''); // Estado para mensagem de erro

  // Busca ficha do usuário ao carregar o componente
  useEffect(() => {
    const fetchSheet = async () => {
      try {
        const response = await fetch('/api/charactersheets', {
          headers: { 'Authorization': `Bearer ${token}` }
        });
        const data = await response.json();
        // Busca a ficha do usuário logado
        const userSheet = data.find(s => s.user && s.user.username === username);
        setSheet(userSheet || null); // Define a ficha encontrada
      } catch (err) {
        setError('Erro ao buscar ficha'); // Exibe erro de conexão
      }
    };
    fetchSheet();
  }, [token, username]);

  if (error) return <p style={{color:'red'}}>{error}</p>; // Exibe erro
  if (!sheet) return <p>Ficha não encontrada.</p>; // Exibe se não houver ficha

  // Exibe os dados da ficha
  return (
    <div>
      <h2>Ficha de Personagem</h2>
      <p><strong>Nome:</strong> {sheet.name}</p>
      <p><strong>Classe:</strong> {sheet.characterClass}</p>
      <p><strong>Raça:</strong> {sheet.race}</p>
      <p><strong>Nível:</strong> {sheet.level}</p>
      <p><strong>Força:</strong> {sheet.strength}</p>
      <p><strong>Destreza:</strong> {sheet.dexterity}</p>
      <p><strong>Constituição:</strong> {sheet.constitution}</p>
      <p><strong>Inteligência:</strong> {sheet.intelligence}</p>
      <p><strong>Sabedoria:</strong> {sheet.wisdom}</p>
      <p><strong>Carisma:</strong> {sheet.charisma}</p>
    </div>
  );
}

export default CharacterSheet; // Exporta o componente de ficha
