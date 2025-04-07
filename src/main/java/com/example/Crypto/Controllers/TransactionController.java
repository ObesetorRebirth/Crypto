package com.example.Crypto.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @GetMapping("/Transaction")
    public String printTransaction() {return "TransactionService";
    }
}
