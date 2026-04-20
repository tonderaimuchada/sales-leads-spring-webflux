/*
 * @author Tonderai Muchada
 * @date 19-04-2026 22:04
 */

package za.co.instacom.salesleads.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.instacom.salesleads.entity.Lead;
import za.co.instacom.salesleads.repository.LeadRepository;

@Service
public class LeadServiceImplementation implements LeadService {
    @Autowired
    private LeadRepository leadRepository;

    @Override
    public Mono<Lead> createLead(Lead lead) {
        return leadRepository.save(lead);
    }

    @Override
    public Flux<Lead> findAllLeads() {
        return leadRepository.findAll();
    }

    @Override
    public Mono<Lead> findLeadById(Long id) {
        return leadRepository.findById(id);
    }

    @Override
    public Mono<Lead> updateLead(Lead lead, Long id) {
        return leadRepository.save(lead);
    }

    @Override
    public Mono<Void> deleteLeadById(Long id) {
        return leadRepository.deleteById(id);
    }
}
