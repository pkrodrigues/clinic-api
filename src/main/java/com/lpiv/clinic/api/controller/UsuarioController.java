// package com.lpiv.clinic.api.controller;


// import org.springframework.http.HttpStatus;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.server.ResponseStatusException;

// import com.lpiv.clinic.api.dto.CredenciaisDTO;
// import com.lpiv.clinic.api.dto.TokenDTO;
// import com.lpiv.clinic.exception.SenhaInvalidaException;
// import com.lpiv.clinic.model.entity.Usuario;
// import com.lpiv.clinic.security.JwtService;
// import com.lpiv.clinic.service.UsuarioService;

// import io.swagger.annotations.ApiOperation;
// import io.swagger.annotations.ApiResponse;
// import io.swagger.annotations.ApiResponses;
// import lombok.RequiredArgsConstructor;

// @RestController
// @RequestMapping("/api/v1/usuario")
// @RequiredArgsConstructor
// public class UsuarioController {
//     private final UsuarioService usuarioService;
//     private final PasswordEncoder passwordEncoder;
//     private final JwtService jwtService;

//     @PostMapping
//     @ApiOperation("Salva uma nova senha para o Usuário")
//     @ApiResponses({
//             @ApiResponse(code = 201, message = "Senha salva com sucesso"),
//             @ApiResponse(code = 400, message = "Erro")
//     })
//     @ResponseStatus(HttpStatus.CREATED)
//     public Usuario salvar(@RequestBody Usuario usuario){
//         String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
//         usuario.setSenha(senhaCriptografada);
//         return usuarioService.salvar(usuario);
//     }

//     @PostMapping("/auth")
//     @ApiOperation("Token")
//     @ApiResponses({
//             @ApiResponse(code = 201, message = "Token gerado com sucesso"),
//             @ApiResponse(code = 400, message = "Erro")
//     })
//     public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
//         try{
//             Usuario usuario = Usuario.builder()
//                     .login(credenciais.getLogin())
//                     .senha(credenciais.getSenha()).build();
//             UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
//             String token = jwtService.gerarToken(usuario);
//             return new TokenDTO(usuario.getLogin(), token);
//         } catch (UsernameNotFoundException | SenhaInvalidaException e ){
//             throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
//         }
//     }
// }