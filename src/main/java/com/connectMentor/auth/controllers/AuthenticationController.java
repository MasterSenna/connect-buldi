package com.connectMentor.auth.controllers;

import org.springframework.security.core.AuthenticationException;

import com.connectMentor.auth.domain.user.RegisterDTO;
import com.connectMentor.auth.domain.user.User;
import com.connectMentor.auth.infra.security.TokenService;
import com.connectMentor.auth.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("logar")
    public ModelAndView login(@RequestParam("login") String login,
            @RequestParam("password") String password,  HttpServletResponse response
         ) {
        ModelAndView mv = new ModelAndView();
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(login, password);
            var auth = this.authenticationManager.authenticate(usernamePassword);

            // Generate token
            var token = tokenService.generateToken((User) auth.getPrincipal());

            SecurityContextHolder.getContext().setAuthentication(auth);
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            boolean isAdmin = userDetails.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));


            // Set the token as a cookie
            Cookie authCookie = new Cookie("auth-api", token);
            authCookie.setHttpOnly(true);
            authCookie.setSecure(true); // Use true if using HTTPS
            authCookie.setPath("/");
            authCookie.setMaxAge(60 * 60 * 24); // 1 day expiration
            response.addCookie(authCookie);

            response.addCookie(authCookie);
//            mv.addObject("auth-api", token);
            if(isAdmin){
                mv.setView(new RedirectView("/mentor/perfilMentor"));
            }else{
                mv.setView(new RedirectView("/mentorado/perfilMentorado"));
            }


        } catch (AuthenticationException e) {


            mv.setView(new RedirectView("/login?error=true"));
        }
        return mv;
    }

    // @PostMapping("logar")
    // public ModelAndView login(@RequestParam("login") String login,
    // @RequestParam("password") String password) {
    // ModelAndView mv = new ModelAndView();
    // try {
    // var usernamePassword = new UsernamePasswordAuthenticationToken(login,
    // password);
    // var auth = this.authenticationManager.authenticate(usernamePassword);

    // // Generate token
    // var token = tokenService.generateToken((User) auth.getPrincipal());

    // SecurityContextHolder.getContext().setAuthentication(auth);
    // // Redirect to perfilMentor
    // mv.setView(new RedirectView("/mentor/perfilMentor"));
    // } catch (AuthenticationException e) {
    // mv.setView(new RedirectView("/againLogin"));
    // }
    // return mv;
    // }
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("againLogin")
    public ModelAndView againLogin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("againLogin");
        return mv;
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

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    // @GetMapping("/logarMentor")
    // public String logar(@RequestParam("email") String email,
    // @RequestParam("senha") String senha) {
    // Mentor mentor = service.buscarPorEmail(email);
    //
    // if (mentor == null) {
    // return "redirect:/againLogin";
    // }
    //
    // if (!mentor.getSenha().equals(senha)) {
    // return "redirect:/againLogin";
    // } else {
    // return "redirect:/perfilMentor/" + mentor.getIdMentor();
    // }
    // }

    @GetMapping("sucesso")
    public ModelAndView sucesso() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("sucesso");
        return mv;
    }

    // @GetMapping("CadastroMentorado")
    // public ModelAndView cadastro() {
    // ModelAndView mv = new ModelAndView();
    // mv.setViewName("CadastroMentorado");
    // return mv;
    // }

    // @GetMapping("CadastroMentor")
    // public ModelAndView cadastro() {
    // ModelAndView mv = new ModelAndView();
    // mv.setViewName("CadastroMentor");
    // return mv;
    // }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
