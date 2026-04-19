/*
 * @author Tonderai Muchada
 * @date 19-04-2026 21:06
 */

package za.co.instacom.salesleads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.instacom.salesleads.entity.Lead;
import za.co.instacom.salesleads.service.LeadService;

@RestController
public class LeadRestController {
    @Autowired
    private LeadService leadService;

    @PostMapping("/api/leads")
    public Lead createLead(@PathVariable Lead lead) {
        return leadService.createLead(lead);
    }
}
