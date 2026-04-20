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

import java.time.LocalDateTime;

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
        return leadRepository.findById(id)
                .map((l) -> {
                    l.setFirstName(lead.getFirstName());
                    l.setLastName(lead.getLastName());
                    l.setCompanyName(lead.getCompanyName());
                    l.setJobTitle(lead.getJobTitle());
                    l.setEmail(lead.getEmail());
                    l.setPhoneNumber(lead.getPhoneNumber());
                    l.setAddress(lead.getAddress());
                    l.setDateModified(LocalDateTime.now());
                    return l;
                })
                .flatMap(leadRepository::save);
    }

    @Override
    public Mono<Void> deleteLeadById(Long id) {
        return leadRepository.deleteById(id);
    }
}
