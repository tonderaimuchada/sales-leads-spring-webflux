/*
 * @author Tonderai Muchada
 * @date 23-04-2026 16:34
 */

package za.co.instacom.salesleads.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class User {
    @Id
    private Long id;

    private String username;
    private String password;
    private String email;

    @Builder.Default
    private String role = "USER";

    @Column("created_at")
    private LocalDateTime createdAt;
}
