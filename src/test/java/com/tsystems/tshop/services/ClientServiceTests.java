package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Client;
import com.tsystems.tshop.repositories.ClientRepository;
import com.tsystems.tshop.services.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ClientServiceTests {

    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        this.clientService = new ClientServiceImpl(clientRepository);
    }

    @DisplayName("Test if clients can be fetched.")
    @Test
    void testIfClientsCanBeFetched() {
        List<Client> clients = singletonList(new Client("John", "Doe", LocalDate.of(1980, 1, 15), "john.doe@mail.com"));
        given(clientRepository.getAllUsers()).willReturn(clients);
        assertThat(clientService.getClients()).isEqualTo(clients);
    }

}
