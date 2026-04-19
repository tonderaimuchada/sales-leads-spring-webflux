/*
 * @author Tonderai Muchada
 * @date 19-04-2026 22:01
 */

package za.co.instacom.salesleads.service;

import za.co.instacom.salesleads.entity.Lead;

import java.util.List;

public interface LeadService {
    Lead createLead(Lead lead);
    List<Lead> findAllLeads();
    Lead findLeadById(Long id);
    Lead updateLead(Lead lead);
    void deleteLeadById(Long id);
}
