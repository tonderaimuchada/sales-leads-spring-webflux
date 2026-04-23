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
        @NotBlank(message = "First name is required")
        @Size(max = 100, message = "First name must not exceed 100 characters")
        private String firstName;

        @NotBlank(message = "Last name is required")
        @Size(max = 100, message = "Last name must not exceed 100 characters")
        private String lastName;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 150, message = "Email must not exceed 150 characters")
        private String email;

        @Pattern(regexp = "^[+]?[0-9\\s\\-().]{7,20}$", message = "Invalid phone number format")
        private String phone;

        @Size(max = 150, message = "Company name must not exceed 150 characters")
        private String company;

        @Pattern(regexp = "NEW|CONTACTED|QUALIFIED|LOST|WON", message = "Invalid status")
        private String status;

        @Size(max = 50, message = "Source must not exceed 50 characters")
        private String source;

        private String notes;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        @Size(max = 100, message = "First name must not exceed 100 characters")
        private String firstName;

        @Size(max = 100, message = "Last name must not exceed 100 characters")
        private String lastName;

        @Email(message = "Invalid email format")
        @Size(max = 150, message = "Email must not exceed 150 characters")
        private String email;

        @Pattern(regexp = "^[+]?[0-9\\s\\-().]{7,20}$", message = "Invalid phone number format")
        private String phone;

        @Size(max = 150, message = "Company name must not exceed 150 characters")
        private String company;

        @Pattern(regexp = "NEW|CONTACTED|QUALIFIED|LOST|WON", message = "Invalid status")
        private String status;

        @Size(max = 50, message = "Source must not exceed 50 characters")
        private String source;

        private String notes;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String company;
        private String status;
        private String source;
        private String notes;
        private Long createdBy;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
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
