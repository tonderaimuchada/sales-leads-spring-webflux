/*
 * @author Tonderai Muchada
 * @date 19-04-2026 22:04
 */

package za.co.instacom.salesleads.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.instacom.salesleads.entity.Lead;
import za.co.instacom.salesleads.repository.LeadRepository;

import java.util.List;

@Service
public class LeadServiceImplementation implements LeadService {
    @Autowired
    private LeadRepository leadRepository;

    @Override
    public Lead createLead(Lead lead) {
        return leadRepository.save(lead);
    }

    @Override
    public List<Lead> findAllLeads() {
        return (List<Lead>) leadRepository.findAll();
    }

    @Override
    public Lead findLeadById(Long id) {
        return leadRepository.findById(id).orElse(null);
    }

    @Override
    public Lead updateLead(Lead lead) {
        return leadRepository.save(lead);
    }

    @Override
    public void deleteLeadById(Long id) {
        leadRepository.deleteById(id);
    }
}
