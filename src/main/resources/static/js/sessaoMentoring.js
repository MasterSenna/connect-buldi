const agendaMentorado = [
    { id: 1, mentorado: 'João', tópico: 'Desenvolvimento Web', data: '20 de Abril de 2024', hora: '10:00', duração: '1 hora', imagem: '../assets/2.png' },
    { id: 2, mentorado: 'Ivo Holanda', tópico: 'Scrum Master (Mentoria Teste)', data: 'Qualquer data (Mentoria Teste)', hora: 'Qualquer horário (Mentoria Teste)', duração: '30 minutos', imagem: '../assets/executive.jpeg' },
    { id: 3, mentorado: 'Pedro', tópico: 'Startups', data: '25 de Abril de 2024', hora: '11:30', duração: '1 hora', imagem: '../assets/2.png' },
    { id: 4, mentorado: 'Sofia', tópico: 'Desenvolvimento Web', data: '28 de Abril de 2024', hora: '09:00', duração: '1 hora', imagem: '../assets/2.png' }
];


// Função para exibir as solicitações na página
function renderRequests() {
    const requestsList = document.getElementById('requestsList');
    requestsList.innerHTML = '';

    agendaMentorado.forEach(request => {
        const requestCard = document.createElement('div');
        requestCard.classList.add('request-card');
        requestCard.innerHTML = `
            <img src="${request.imagem}" alt="${request.mentee} Image" onmouseover="jumpEffect(this)">
            <p><strong>Mentorado:</strong> ${request.mentorado}</p>
            <p><strong>Tópico:</strong> ${request.tópico}</p>
            <p><strong>Data:</strong> ${request.data}</p>
            <p><strong>Hora:</strong> ${request.hora}</p>
            <p><strong>Duração:</strong> ${request.duração}</p>
            <button onclick="window.location.href = 'https://meet.google.com/tqn-ouoq-jmz'">Iniciar Sessão</button>
        `;
        requestsList.appendChild(requestCard);
    });
}

// Função para simular a aceitação da solicitação
function acceptRequest(requestId) {
    // Aqui você pode adicionar a lógica para aceitar a solicitação com o ID requestId
    console.log(`Request ${requestId} accepted.`);
    // Atualiza a lista após a aceitação
    renderRequests();
}

// Função para adicionar efeito de salto na imagem quando o cursor passa sobre ela
function jumpEffect(image) {
    image.style.transform = 'translateY(-5px)';
    setTimeout(() => {
        image.style.transform = 'translateY(0)';
    }, 200);
}


// JavaScript para ajustar a posição do menu conforme o scroll da página
window.addEventListener('scroll', function() {
    const menu = document.getElementById('menu');
    const scrollPosition = window.scrollY;

    // Ajusta o valor "top" do menu conforme o scroll
    menu.style.top = (scrollPosition + 10) + 'px'; // Adicione uma margem de 10px, se necessário
});



// Renderiza as solicitações na inicialização
window.onload = renderRequests;