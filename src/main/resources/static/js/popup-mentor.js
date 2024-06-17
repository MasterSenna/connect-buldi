// Script para controlar o pop-up do mentor
document.addEventListener("DOMContentLoaded", function() {
  // Seleciona todos os elementos dos slides do carrossel
  const slides = document.querySelectorAll(".swiper-slide");

  // Adiciona um event listener para cada slide
  slides.forEach(function(slide, index) {
    slide.addEventListener("click", function() {
      // Obtém as informações do mentor do slide clicado
      const mentor = mentors[index];

      // Preenche as informações do mentor no pop-up
      document.getElementById("mentor-name").textContent = mentor.name;
      document.getElementById("mentor-description").textContent = mentor.description;
      document.getElementById("mentor-company").textContent = mentor.company;
     
      // Exibe o pop-up do mentor
      document.getElementById("mentor-popup").style.display = "block";
    });
  });

  // Event listener para fechar o pop-up do mentor quando clicar no botão de fechar
  document.querySelector(".mentor-popup-close").addEventListener("click", function() {
    document.getElementById("mentor-popup").style.display = "none";
  });
});

// Informações dos Mentores (Você pode adicionar mais mentores conforme necessário)
const mentors = [
  {
    name: "Ivo Holanda",
    description: "Desenvolvedor Java",
    company: "Google",
  
  },
  {
    name: "Luis Henrique",
    description: "Scrum Master",
    company: "Shopee"
  },
  {
    name: "José Carlos",
    description: "Data Engineer",
    company: "Facebook"
  },
  {
    name: "Epaminondas Silva",
    description: "Front-end | UI/UX",
    company: "Havaianas"
  },
  {
    name: "João Cézar",
    description: "Product Owner",
    company: "Bradesco"
  }
];
