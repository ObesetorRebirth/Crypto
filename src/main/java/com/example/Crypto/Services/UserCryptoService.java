package com.example.Crypto.Services;

import com.example.Crypto.Repositories.UserCryptoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserCryptoService {

    private final UserCryptoRepository userCryptoRepository;
}
