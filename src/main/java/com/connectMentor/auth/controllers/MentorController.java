package com.connectMentor.auth.controllers;

import java.io.IOException;

import com.connectMentor.auth.domain.mentorado.Mentor;
import com.connectMentor.auth.domain.user.User;
import com.connectMentor.auth.domain.user.UserRole;
import com.connectMentor.auth.repositories.UserRepository;
import com.connectMentor.auth.services.FileService;
import com.connectMentor.auth.services.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mentor")
public class MentorController {

	@Autowired
	private MentorService service;
	@Autowired
	private UserRepository repository;
	@Autowired
	private FileService fileService;

	//
	// @GetMapping("index")
	// public ModelAndView initialPage() {
	//
	// ModelAndView mv = new ModelAndView();
	// mv.setViewName("index");
	// return mv;
	// }
	//
	// @GetMapping("index")
	// public ModelAndView index() {
	// ModelAndView mv = new ModelAndView();
	// mv.setViewName("index");
	// return mv;
	// }

	@GetMapping("login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}

	@GetMapping("sucesso")
	public ModelAndView sucesso() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("sucesso");
		return mv;
	}

	@GetMapping("CadastroMentor")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("CadastroMentor");
		return mv;
	}
	@GetMapping("avaliacao")
	public ModelAndView avaliacao() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("avaliacaoMentor");
		return mv;
	}

	@PostMapping("/inserirMentores")
	public String salvarMentor(@RequestParam("certificado") MultipartFile certificadoFile,
			@RequestParam("nome") String nome,
			@RequestParam("cpf") String cpf,
			@RequestParam("email") String email,
			@RequestParam("telefone") String telefone,
			@RequestParam("senha") String senha,
			@RequestParam("confirmPassword") String confirmPassword,
			@RequestParam("genero") Integer genero) throws IOException {
		Mentor mentor = new Mentor();
		mentor.setNome(nome);
		mentor.setCpf(cpf);
		mentor.setEmail(email);
		mentor.setTelefone(telefone);
		mentor.setSenha(senha);

		if (this.repository.findByLogin(email) != null)
			return "";

		String encryptedPassword = new BCryptPasswordEncoder().encode(mentor.getSenha());
		User newUser = new User(email, encryptedPassword, UserRole.ADMIN);

		String savedFileName = fileService.addImage(certificadoFile.getOriginalFilename(), certificadoFile.getBytes(),
				"C:\\Users\\user\\Downloads");
		mentor.setCertificado(certificadoFile.getBytes());
		service.salvarMentor(mentor, certificadoFile);
		this.repository.save(newUser);

		return "redirect:sucesso";
	}


	@GetMapping("/perfilMentor")
	public ModelAndView perfilMentor() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("perfilMentor");
		return mv;
	}

	@GetMapping("sessoesMentoring")
	public ModelAndView sessoesMentoring() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sessoesMentoring");
		return mv;
	}

	@GetMapping("perfil-mentor")
	public ModelAndView perfilMentorDois() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("perfil-mentor");
		return mv;
	}

	@GetMapping("/mentor/index")
	public ModelAndView mentor() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/mentor/index");
		return mv;
	}

	@RequestMapping("/")
	@GetMapping("/sobreNos")
	public ModelAndView sobreNos(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sobreNos");
		return mv;
	}




	@PostMapping("/atualizarFotoMentor")
	public String atualizarFotoMentor(@RequestParam("foto") MultipartFile file,
			@RequestParam("idMentor") Long idMentor) {
		if (!file.isEmpty()) {
			try {
				byte[] fotoBytes = file.getBytes();
				service.atualizarFotoMentor(idMentor, fotoBytes);
				return "redirect:/perfilMentor/" + idMentor;
			} catch (IOException e) {
				e.printStackTrace();
				return "erro";
			}
		} else {
			return "erro";
		}
	}
}
