/*
 * @author Tonderai Muchada
 * @date 19-04-2026 21:38
 */

package za.co.instacom.salesleads.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import za.co.instacom.salesleads.entity.Lead;

@Repository
public interface LeadRepository extends ReactiveCrudRepository<Lead, Long> {
}
