package com.dpsac.crud.Controller;
import com.dpsac.crud.Dto.ApiResponse;
import com.dpsac.crud.Dto.JwtAuthenticationResponse;
import com.dpsac.crud.Dto.LoginRequest;
import com.dpsac.crud.Dto.SignUpRequest;
import com.dpsac.crud.Exception.AppException;
import com.dpsac.crud.Model.NombreRol;
import com.dpsac.crud.Model.Rol;
import com.dpsac.crud.Model.Usuario;
import com.dpsac.crud.Repository.RolRepository;
import com.dpsac.crud.Repository.UsuarioRepository;
import com.dpsac.crud.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository userRepository;

    @Autowired
    RolRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByNombreUsuario(signUpRequest.getNombreUsuario())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Usuario user = new Usuario(signUpRequest.getApePaterno(),
                signUpRequest.getApeMaterno(),
                signUpRequest.getNombres(),
                signUpRequest.getNombreUsuario(),
                signUpRequest.getContraseña(),
                signUpRequest.getFechaInicio(),
                signUpRequest.getFechaFin(),
                signUpRequest.getEmail(),
                signUpRequest.getEstado());

        user.setContraseña(passwordEncoder.encode(user.getContraseña()));

        //System.out.println("rol: "+signUpRequest.);
        Rol userRole = roleRepository.findByNombreRol(NombreRol.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));


        user.setRoles(Collections.singleton(userRole));

        Usuario result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getNombreUsuario()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}