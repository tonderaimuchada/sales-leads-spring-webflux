/*
 * @author Tonderai Muchada
 * @date 19-04-2026 22:01
 */

package za.co.instacom.salesleads.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.instacom.salesleads.entity.Lead;

public interface LeadService {
    Mono<Lead> createLead(Lead lead);
    Flux<Lead> findAllLeads();
    Mono<Lead> findLeadById(Long id);
    Mono<Lead> updateLead(Lead lead, Long id);
    Mono<Void> deleteLeadById(Long id);
}
