/*
 * @author Tonderai Muchada
 * @date 19-04-2026 21:44
 */

package za.co.instacom.salesleads.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long leadId;
    private String firstName;
    private String lastName;
    private String companyName;
    private String jobTitle;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
}
