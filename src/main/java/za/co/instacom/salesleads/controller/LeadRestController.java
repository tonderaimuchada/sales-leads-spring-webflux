/*
 * @author Tonderai Muchada
 * @date 19-04-2026 21:06
 */

package za.co.instacom.salesleads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.instacom.salesleads.entity.Lead;
import za.co.instacom.salesleads.service.LeadService;

@RestController
@RequestMapping("/api/leads")
public class LeadRestController {
    @Autowired
    private LeadService leadService;

    @PostMapping()
    public Mono<Lead> createLead(@RequestBody Lead lead) {
        return leadService.createLead(lead);
    }

    @GetMapping()
    public Flux<Lead> listLeads() {
        return leadService.findAllLeads();
    }

    @GetMapping("/{id}")
    public Mono<Lead> fetchLeadById(@PathVariable Long id) {
        return leadService.findLeadById(id);
    }

    @PutMapping("/{id}")
    public Mono<Lead> updateLead(@RequestBody Lead lead,  @PathVariable Long id) {
        return leadService.updateLead(lead, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteLead(@PathVariable Long id) {
        return leadService.deleteLeadById(id);
    }
}
