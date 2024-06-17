// Inicialização do Swiper
var swiper = new Swiper(".swiper", {
  effect: "coverflow",
  grabCursor: true,
  centeredSlides: true,
  slidesPerView: "auto",
  coverflowEffect: {
    rotate: 0,
    stretch: 0,
    depth: 100,
    modifier: 2,
    slideShadows: true
  },
  spaceBetween: 60,
  loop: true,
  pagination: {
    el: ".swiper-pagination",
    clickable: true
  }
});

// Event listener para o clique no ícone de hambúrguer
document.getElementById("menu-toggle").addEventListener("click", function() {
  // Ajusta a largura do sidebar para torná-lo visível
  document.getElementById("sidebar").style.width = "250px";
});

// Event listener para o clique no sidebar para fechá-lo
document.getElementById("sidebar").addEventListener("click", function() {
  // Define a largura do sidebar de volta para 0 para escondê-lo
  this.style.width = "0";
});

// Alternância de visibilidade do sidebar usando jQuery
$(document).ready(function() {
  $("#menu-toggle").click(function() {
    $("#sidebar").toggle();
  });
});

// Adicione um evento de clique aos elementos que representam os mentores
document.querySelectorAll('.swiper-slide').forEach(function(slide) {
  slide.addEventListener('click', function() {
    // Obtenha os detalhes do mentor clicado
    var mentorName = this.querySelector('h2').innerText;
    var mentorDescription = this.querySelector('p').innerText;
    var mentorCompany = this.querySelector('p').innerText;
    var mentorBio = this.dataset.bio; // Obtenha a biografia do atributo 'data-bio'


    // Atualize os elementos no pop-up com os detalhes do mentor
    document.getElementById('mentor-name').innerText = mentorName;
    document.getElementById('mentor-description').innerText = mentorDescription;
     document.getElementById('mentor-bio').innerText = mentorBio; // Atualize a biografia
    document.getElementById('mentor-company').innerText = mentorCompany;


    // Exiba o pop-up
    document.querySelector('.mentor-popup').style.display = 'block';
  });
});



// Adicione um evento de clique ao botão de fechar o pop-up
document.querySelector('.mentor-popup-close').addEventListener('click', function() {
  // Oculte o pop-up
  document.querySelector('.mentor-popup').style.display = 'none';
});


document.querySelector('.match-btn').addEventListener('click', function() {
  // Lógica para "match"
  // Por exemplo, exibir uma mensagem ou executar uma ação
  console.log('Match feito com sucesso!');
});

document.querySelector('.reject-btn').addEventListener('click', function() {
  // Lógica para "rejeitar"
  // Por exemplo, fechar o pop-up ou carregar o próximo mentor
  closeMentorPopup();
});

function closeMentorPopup() {
  document.querySelector('.mentor-popup').style.display = 'none';
}
