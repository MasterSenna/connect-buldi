package com.connectMentor.auth.controllers;

import java.io.IOException;
import java.util.List;

import com.connectMentor.auth.domain.mentorado.Mentorado;
import com.connectMentor.auth.domain.mentorado.MentoradoPretensao;
import com.connectMentor.auth.domain.mentorado.Pretensao;
import com.connectMentor.auth.domain.user.User;
import com.connectMentor.auth.domain.user.UserRole;
import com.connectMentor.auth.repositories.UserRepository;
import com.connectMentor.auth.services.MentoradoPretensaoService;
import com.connectMentor.auth.services.MentoradoService;
import com.connectMentor.auth.services.PretensaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mentorado")
public class MentoradoController {

	// Injeção de depedências;
	@Autowired
	private MentoradoService mentoradoService;
	@Autowired
	private UserRepository repository;
	@Autowired
	private PretensaoService pretensaoService;

	@Autowired
	private MentoradoPretensaoService mentoradoPretensaoService;

	// Setando view na página de teste do perfil mentorado;
	@GetMapping("testePerfilMentorado")
	public ModelAndView testePerfilMentorado() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("testePerfilMentorado");
		return mv;
	}

	// Setando view no endpoint cadastro;
	@GetMapping("CadastroMentorado")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("CadastroMentorado");
		return mv;
	}
	// Setando view no endpoint index

	@GetMapping("sucesso")
	public ModelAndView sucesso() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("sucesso");
		return mv;
	}

	// Setando view no endpoint perfil mentorado;
	@GetMapping("perfilMentorado")
	public ModelAndView perfilMentorado() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("perfilmentorado");
		return mv;
	}

	@GetMapping("editarperfil/{idMentorado}")
	public ModelAndView editarperfil(@PathVariable Long idMentorado) {

		mentoradoService.buscarIdMentorado(idMentorado);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("editarperfil");
		return mv;
	}

	@PostMapping("/inserirMentorados")
	public String salvarMentorado(@ModelAttribute Mentorado mentorado) {

		String encryptedPassword = new BCryptPasswordEncoder().encode(mentorado.getSenha());
		mentoradoService.salvarMentorado(mentorado);



		User newUser = new User(mentorado.getEmail(), encryptedPassword, UserRole.USER);
		this.repository.save(newUser);

		return "sucesso";
	}
	// Inserindo pretensões do mentorado;
	@PostMapping("/inserirPretensoes")
	public String salvarPretensoes(@RequestParam Long idMentorado,
			@RequestParam("idPretensao") List<Long> idPretensao) {
		try {

			Mentorado mentorado = mentoradoService.buscarIdMentorado(idMentorado);

			if (mentorado != null && idPretensao != null && !idPretensao.isEmpty()) {

				for (Long id : idPretensao) {

					Pretensao pretensao = pretensaoService.buscarIdPretensao(id);

					if (pretensao != null) {

						MentoradoPretensao mentoradoPretensao = new MentoradoPretensao(null, mentorado, pretensao);

						mentoradoPretensaoService.salvarPretensao(mentoradoPretensao);
					}
				} // forEach;
				return "redirect:/sucesso"; // IF;
			} else {
				return "redirect:/index"; // ELSE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/Cadastro"; // EXCEPTION;
		}
	}// Method;

	// Método para retornar a lista de pretensões do mentorado;
	@GetMapping("perfilMentorado/listaPretensoes")
	public ResponseEntity<List<MentoradoPretensao>> findAll() {
		List<MentoradoPretensao> list = mentoradoPretensaoService.findAll();
		return ResponseEntity.ok().body(list);
	}

	// Método para logar o mentorado
	// @GetMapping("/logarMentorado")
	// public String logar(@RequestParam("email") String email,
	// @RequestParam("senha") String senha) {
	//
	// Mentorado mentorado = mentoradoService.buscarPorEmail(email);
	//
	// if(mentorado == null) {
	//
	// return "redirect:/againLogin";
	// }
	//
	// if(!mentorado.getSenha().equals(senha)) {
	// return "redirect:/againLogin";
	//
	// }else {
	//
	//
	// return "redirect:/perfilMentorado/"+mentorado.getId();
	// }
	// }

	@GetMapping("encontrarMentor")
	public ModelAndView encontrarMentor() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("encontrarMentor");
		return mv;
	}

	@GetMapping("/sidebar")
	public ModelAndView sidebar() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("sidebar");
		return mv;
	}

	@GetMapping("/editarPerfilMentorado")
	public ModelAndView editarPerfilMentorado() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("editarPerfilMentorado");
		return mv;
	}
	@GetMapping("/minhasSessoesAtivas")
	public ModelAndView sessoesMentoring() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("mentoradoSessoesMentoring");
		return mv;
	}




}