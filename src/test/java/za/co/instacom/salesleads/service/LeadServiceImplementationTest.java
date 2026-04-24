/*
 * @author Tonderai Muchada
 * @date 24-04-2026 05:02
 */

package za.co.instacom.salesleads.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import za.co.instacom.salesleads.entity.Lead;
import za.co.instacom.salesleads.repository.LeadRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeadServiceImplementationTest {

    @Mock
    private LeadRepository leadRepository;

    @InjectMocks
    private LeadServiceImplementation leadService;

    private Lead lead;

    @BeforeEach
    void setUp() {
        lead = new Lead();
        lead.setLeadId(1L);
        lead.setFullName("John Doe");
        lead.setEmailAddress("john@test.com");
        lead.setPhoneNumber("0677134567");
        lead.setStatus("NEW");
    }

    @Test
    void shouldCreateLead() {
        when(leadRepository.save(any(Lead.class))).thenReturn(Mono.just(lead));

        StepVerifier.create(leadService.createLead(lead))
                .expectNext(lead)
                .verifyComplete();

        verify(leadRepository, times(1)).save(lead);
    }

    @Test
    void shouldFindAllLeads() {
        when(leadRepository.findAll()).thenReturn(Flux.just(lead));

        StepVerifier.create(leadService.findAllLeads())
                .expectNext(lead)
                .verifyComplete();

        verify(leadRepository, times(1)).findAll();
    }

    @Test
    void shouldFindLeadById() {
        when(leadRepository.findById(1L)).thenReturn(Mono.just(lead));

        StepVerifier.create(leadService.findLeadById(1L))
                .expectNext(lead)
                .verifyComplete();

        verify(leadRepository, times(1)).findById(1L);
    }

    @Test
    void shouldUpdateLead() {
        Lead updatedLead = new Lead();
        updatedLead.setFullName("Jane Doe");
        updatedLead.setEmailAddress("jane@test.com");
        updatedLead.setPhoneNumber("0678999999");
        updatedLead.setStatus("CONTACTED");

        when(leadRepository.findById(1L)).thenReturn(Mono.just(lead));
        when(leadRepository.save(any(Lead.class))).thenReturn(Mono.just(lead));

        StepVerifier.create(leadService.updateLead(updatedLead, 1L))
                .expectNextMatches(result ->
                        result.getFullName().equals("Jane Doe") &&
                                result.getEmailAddress().equals("jane@test.com") &&
                                result.getStatus().equals("CONTACTED"))
                .verifyComplete();

        verify(leadRepository).findById(1L);
        verify(leadRepository).save(any(Lead.class));
    }

    @Test
    void shouldDeleteLeadById() {
        when(leadRepository.deleteById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(leadService.deleteLeadById(1L))
                .verifyComplete();

        verify(leadRepository, times(1)).deleteById(1L);
    }
}
