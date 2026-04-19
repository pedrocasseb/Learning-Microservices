package br.com.pedrocasseb.service.impl;

import br.com.pedrocasseb.config.JwtProvider;
import br.com.pedrocasseb.enums.UserRole;
import br.com.pedrocasseb.mapper.UserMapper;
import br.com.pedrocasseb.model.User;
import br.com.pedrocasseb.payload.dto.UserDTO;
import br.com.pedrocasseb.payload.response.AuthResponse;
import br.com.pedrocasseb.repository.UserRepository;
import br.com.pedrocasseb.service.AuthService;
import br.com.pedrocasseb.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthResponse signup(UserDTO req) throws Exception {
        User existingUser = userRepository.findByEmail(req.getEmail());
        if (existingUser != null) {
            throw new Exception("email already registered");
        }
        if (req.getRole() == UserRole.ROLE_SYSTEM_ADMIN) {
            throw new Exception("you cannot sign up with system admin");
        }

        User newUser = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .phone(req.getPhone())
                .role(req.getRole())
                .fullName(req.getFullName())
                .lastLogin(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(), savedUser.getPassword()
                );
        String jwt = jwtProvider.generateToken(
                authentication, savedUser.getId()
        );
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(savedUser));
        authResponse.setTitle("Welcome " +  savedUser.getFullName());
        authResponse.setMessage("Saved Successfully");
        return authResponse;
    }

    @Override
    public AuthResponse login(String email, String password) throws Exception {
        Authentication authentication = authenticate(email, password);
        User user = userRepository.findByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        String jwt = jwtProvider.generateToken(authentication, user.getId());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(user));
        authResponse.setTitle("Welcome " +  user.getFullName());
        authResponse.setMessage("login Successfully");
        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if(!passwordEncoder.matches(
                password, userDetails.getPassword()
        )) {
            throw new Exception("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
    }
}
