/*
 * @author Tonderai Muchada
 * @date 23-04-2026 17:47
 */

package za.co.instacom.salesleads.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import za.co.instacom.salesleads.dto.AuthDto;
import za.co.instacom.salesleads.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthDto.LoginResponse>> login(
            @Valid @RequestBody AuthDto.LoginRequest request) {
        return authService.login(request)
                .map(ResponseEntity::ok);
    }
}
