/*
 * @author Tonderai Muchada
 * @date 23-04-2026 18:04
 */

package za.co.instacom.salesleads.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class LeadDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotBlank(message = "Full name is required")
        @Size(max = 255, message = "Full name must not exceed 255 characters")
        private String fullName;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email address format")
        @Size(max = 150, message = "Email address must not exceed 150 characters")
        private String emailAddress;

        @Pattern(regexp = "^[+]?[0-9\\s\\-().]{7,20}$", message = "Invalid phone number format")
        private String phoneNumber;

        @Size(max = 255, message = "Company name must not exceed 255 characters")
        private String companyName;

        @Size(max = 100, message = "Status must not exceed 100 characters")
        private String status;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        @Size(max = 255, message = "Full name must not exceed 255 characters")
        private String fullName;

        @Email(message = "Invalid email address format")
        @Size(max = 150, message = "Email address must not exceed 150 characters")
        private String emailAddress;

        @Pattern(regexp = "^[+]?[0-9\\s\\-().]{7,20}$", message = "Invalid phone number format")
        private String phoneNumber;

        @Size(max = 150, message = "Company name must not exceed 150 characters")
        private String companyName;

        @Size(max = 100, message = "Status must not exceed 100 characters")
        private String status;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String fullName;
        private String emailAddress;
        private String phoneNumber;
        private String companyName;
        private String status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateModified;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PagedResponse {
        private java.util.List<Response> content;
        private long totalElements;
        private int totalPages;
        private int page;
        private int size;
    }
}
