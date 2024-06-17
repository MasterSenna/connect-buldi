
const buttons = document.querySelectorAll('.card-buttons button');
const sections = document.querySelectorAll('.card-section');
const card = document.querySelector('.card');

const handleButtonClick = (e) => {
  const targetSection = e.target.getAttribute("data-section")
  const section = document.querySelector(targetSection);
  targetSection !== "#about"
    ? card.classList.add("is-active")
    : card.classList.remove("is-active");
  card.setAttribute("data-state", targetSection);

  sections.forEach((s) => s.classList.remove("is-active"));
  buttons.forEach((b) => b.classList.remove("is-active"));

  e.target.classList.add("is-active");
  section.classList.add("is-active");
}

buttons.forEach((btn) => {
  btn.addEventListener('click', handleButtonClick);
})


const buscarMentorButton = document.getElementById('buscarMentorButton');

const redirectToEncontrarMentor = () => {
  // Redirecionar para a página "encontrarMentor.html" (ou outra página desejada)
  window.location.href = '/encontrarMentor';
};

buscarMentorButton.addEventListener('click', redirectToEncontrarMentor);



document.addEventListener('DOMContentLoaded', function () {
    const profilePicture = document.getElementById('profilePicture');
    const fileInput = document.getElementById('fileInput');

    profilePicture.addEventListener('click', function () {
        fileInput.click(); // Aciona o clique no input de arquivo invisível
    });

    fileInput.addEventListener('change', function () {
        const selectedFile = fileInput.files[0];
        // Aqui você pode enviar a foto para o servidor usando AJAX ou outra técnica de sua escolha
        // Após o upload bem-sucedido, você pode atualizar a imagem do perfil com a nova foto
        console.log('Nova foto selecionada:', selectedFile);
        // Exemplo de como você pode atualizar a imagem do perfil (substituir esta linha pelo código real)
        // profilePicture.src = URL.createObjectURL(selectedFile);
    });
});
