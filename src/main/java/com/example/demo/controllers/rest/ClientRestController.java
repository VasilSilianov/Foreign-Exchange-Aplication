package com.example.demo.controllers.rest;

import com.example.demo.services.contracts.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exchange")
public class ClientRestController {
    private ClientService clientService;


    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{currencyFrom}/{currencyTo}")
    public double getExchangeRate(@PathVariable String currencyFrom,@PathVariable String currencyTo){
        return clientService.exchangeRate(currencyFrom,currencyTo);
    }
}
