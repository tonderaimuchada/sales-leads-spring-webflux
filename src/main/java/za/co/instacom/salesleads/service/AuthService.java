/*
 * @author Tonderai Muchada
 * @date 23-04-2026 17:45
 */

package za.co.instacom.salesleads.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import za.co.instacom.salesleads.config.JwtUtil;
import za.co.instacom.salesleads.dto.AuthDto;
import za.co.instacom.salesleads.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Mono<AuthDto.LoginResponse> login(AuthDto.LoginRequest request) {
        return userRepository.findByUsername(request.getUsername())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
                    return AuthDto.LoginResponse.builder()
                            .token(token)
                            .tokenType("Bearer")
                            .expiresIn(jwtUtil.getExpirationMs())
                            .username(user.getUsername())
                            .role(user.getRole())
                            .build();
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Invalid credentials")));
    }
}