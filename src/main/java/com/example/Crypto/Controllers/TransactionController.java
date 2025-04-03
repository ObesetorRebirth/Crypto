package com.example.Crypto.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @GetMapping("/Transaction")
    public String printTransaction() {return "TransactionService";
    }
}
