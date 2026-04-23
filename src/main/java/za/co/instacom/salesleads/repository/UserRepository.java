/*
 * @author Tonderai Muchada
 * @date 23-04-2026 16:40
 */

package za.co.instacom.salesleads.repository;

import org.springframework.stereotype.Repository;
import za.co.instacom.salesleads.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long>{
    Mono<User> findByUsername(String username);
    Mono<User> findByEmail(String email);
    Mono<Boolean> existsByUsername(String username);
}
