document.addEventListener("DOMContentLoaded", function() {
  const slides = document.querySelectorAll(".swiper-slide");

  slides.forEach(function(slide, index) {
    slide.addEventListener("click", function() {
      const mentor = mentors[index];

      document.getElementById("mentor-name").textContent = mentor.name;
      document.getElementById("mentor-description").textContent = mentor.description;
      document.getElementById("mentor-company").textContent = mentor.company;
      document.getElementById("mentor-bio").textContent = mentor.bio;
      document.getElementById("mentor-image").src = mentor.image;

      document.getElementById("requestsList").style.display = "block";
    });
  });

  document.querySelector(".mentor-popup-close").addEventListener("click", function() {
    document.getElementById("requestsList").style.display = "none";
  });
});

const mentors = [
  {
    name: "Edi Nascimento",
    description: "Desenvolvedor Full Stack",
    company: "Sênior",
    bio: "Sou um entusiasta da tecnologia com vasta experiência em desenvolvimento de software. Estou sempre buscando aprender e compartilhar conhecimento com outros profissionais da área.",
    image: "../assets/edii.png"
  },
  {
    name: "Luis Henrique",
    description: "Software Enginner",
    company: "Shopee",
    bio: "Sou um Software Enginner com mais de 7 anos de experiência liderando equipes com desenvolvimento ágil. Minha paixão é garantir que os projetos sejam entregues com alta qualidade e dentro do prazo. Estou sempre disponível para compartilhar meu conhecimento e ajudar a impulsionar sua carreira na área de gerenciamento de projetos.",
    image: "../assets/luiz.png"
  },
  {
    name: "Cláudia Lins",
    description: "FullStack",
    company: "Facebook",
    bio: "Sou um Engenheiro de Dados apaixonado por transformação digital. Com experiência em projetos de big data e machine learning, estou sempre buscando soluções inovadoras para problemas complexos. Estou disponível para orientação e mentoria em projetos de análise de dados e desenvolvimento de pipelines de dados.",
    image: "../assets/mentora.png"
  },
  {
    name: "Sara Silva",
    description: "Software Enginner",
    company: "Havaianas",
    bio: "Sou um desenvolvedor front-end com paixão por criar interfaces de usuário atraentes e funcionais. Com experiência em HTML, CSS e JavaScript, estou sempre explorando novas tecnologias e melhores práticas de design. Estou aqui para ajudá-lo a aprender e aprimorar suas habilidades de desenvolvimento front-end.",
    image: "../assets/saraa.png"
  },
  {
    name: "João Cézar",
    description: "FullStack",
    company: "Bradesco",
    bio: "Sou um Product Owner com uma paixão por criar produtos digitais que cativam e encantam os usuários. Com experiência em gestão de projetos e desenvolvimento ágil, estou sempre focado em alcançar os objetivos de negócios e satisfazer as necessidades dos clientes. Estou aqui para ajudá-lo a transformar suas ideias em produtos de sucesso.",
    image: "../assets/cezar.png"
  }
];
