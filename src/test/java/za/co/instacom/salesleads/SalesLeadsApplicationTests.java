package za.co.instacom.salesleads;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import za.co.instacom.salesleads.dto.AuthDto;
import za.co.instacom.salesleads.dto.LeadDto;
import za.co.instacom.salesleads.entity.User;
import za.co.instacom.salesleads.repository.LeadRepository;
import za.co.instacom.salesleads.repository.UserRepository;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class SalesLeadsApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String authToken;

    @BeforeEach
    void setUp() {
        // Clean up
        leadRepository.deleteAll().block();
        userRepository.deleteAll().block();

        // Create test user
        User testUser = User.builder()
                .username("testuser")
                .password(passwordEncoder.encode("password123"))
                .email("test@test.com")
                .role("ADMIN")
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(testUser).block();

        // Login to get token
        AuthDto.LoginRequest loginRequest = AuthDto.LoginRequest.builder()
                .username("testuser")
                .password("password123")
                .build();

        AuthDto.LoginResponse loginResponse = webTestClient.post()
                .uri("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AuthDto.LoginResponse.class)
                .returnResult()
                .getResponseBody();

        authToken = "Bearer " + loginResponse.getToken();
    }

//    @Test
//    void contextLoads() {
//    }

    @Test
    void shouldCreateLeadSuccessfully() {
        LeadDto.CreateRequest request = LeadDto.CreateRequest.builder()
                .fullName("John Doe")
                .emailAddress("john.doe@example.com")
                .phoneNumber("+27-0199")
                .companyName("Test Corp")
                .status("NEW")
                .createdBy("Test")
                .build();

        webTestClient.post()
                .uri("/api/leads")
                .header("Authorization", authToken)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(LeadDto.Response.class)
                .value(lead -> {
                    assert lead.getId() != null;
                    assert "John Doe".equals(lead.getFullName());
                    assert "john.doe@example.com".equals(lead.getEmailAddress());
                });
    }

    @Test
    void shouldReturnUnauthorizedWithoutToken() {
        webTestClient.get()
                .uri("/api/leads")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void shouldReturnValidationErrorForInvalidEmail() {
        LeadDto.CreateRequest request = LeadDto.CreateRequest.builder()
                .fullName("John Doe")
                .emailAddress("not-an-email")
                .build();

        webTestClient.post()
                .uri("/api/leads")
                .header("Authorization", authToken)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void shouldReturn404ForNonExistentLead() {
        webTestClient.get()
                .uri("/api/leads/999999")
                .header("Authorization", authToken)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void shouldListLeadsWithPagination() {
        webTestClient.get()
                .uri("/api/leads?page=0&size=5")
                .header("Authorization", authToken)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LeadDto.PagedResponse.class)
                .value(response -> {
                    assert response.getContent() != null;
                    assert response.getSize() == 5;
                });
    }
}