package com.tsystems.tshop.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.tsystems.tshop.domain.Client;
import com.tsystems.tshop.services.ClientService;

@Controller
@RequestMapping("/api/clients")
public class ClientController {

	private final ClientService clientService;

	public ClientController(final ClientService clientService) {
		this.clientService = clientService;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	@ResponseBody
	public Client getClient(@PathVariable Long id) {
		return clientService.getClientById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Client> getClients() {
		return clientService.getClients();
	}

	@PostMapping
	@ResponseBody
	public Number addClient(@RequestBody Client client) {
		return clientService.addClient(client);
	}

}
