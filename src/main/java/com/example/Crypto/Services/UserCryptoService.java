package com.example.Crypto.Services;

import com.example.Crypto.CompositeKeys.UserCryptoId;
import com.example.Crypto.Entities.IntermediaryEntities.UserCrypto;
import com.example.Crypto.Mappers.UserCryptoMapper;
import com.example.Crypto.Repositories.CryptoRepository;
import com.example.Crypto.Repositories.UserCryptoRepository;
import com.example.Crypto.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserCryptoService {

    private final UserCryptoRepository userCryptoRepository;
    private final UserCryptoMapper userCryptoMapper;
    private final UserRepository userRepository;
    private final CryptoRepository cryptoRepository;

    public void addToHoldings(Long userId,Long cryptoId, Double quantity) {
        UserCryptoId id = new UserCryptoId(userId,cryptoId);

        UserCrypto userCrypto = new UserCrypto();
        userCrypto.setId(id);
        userCrypto.setUser(userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User with this ID does not exist")));
        userCrypto.setCrypto(cryptoRepository.findById(cryptoId).orElseThrow(()-> new EntityNotFoundException("Crypto with this ID does not exist")));
        userCrypto.setQuantity(quantity);
        userCryptoRepository.save(userCrypto);
    }
    public void subtractFromHoldings(Long userId,Long cryptoId,Double quantityToSubtract) {
        UserCrypto userCrypto = userCryptoRepository.findByUserIdAndCryptoId(userId, cryptoId)
                .orElseThrow(() -> new EntityNotFoundException("UserCrypto with userId " + userId + " and cryptoId " + cryptoId + " not found"));

        Double quantity = userCrypto.getQuantity();

        if(!DoubleComparator(quantity, quantityToSubtract ))
        {
            throw new ArithmeticException("You can't sell more than you currently own");
        }

        double newBalance = quantity - quantityToSubtract;
        if(newBalance == 0) {
            userCryptoRepository.delete(userCrypto);
        }else{
            userCrypto.setQuantity(newBalance);
            userCryptoRepository.save(userCrypto);
        }
    }

    public List<UserCrypto> getHoldingsByUser(Long userId) {
        return userCryptoRepository.findByUserId(userId);
    }
    public UserCrypto getHolding(Long userId, Long cryptoId) {
        return userCryptoRepository.findByUserIdAndCryptoId(userId, cryptoId)
                .orElseThrow(() -> new EntityNotFoundException("Holding not found for userId: " + userId + " and cryptoId: " + cryptoId));
    }

    //util
    private boolean DoubleComparator(double a, double b) {
        boolean isBigger = false;
        double c = a - b;
        if(c >= 0)
        {
            isBigger = true;
        }
        return isBigger;
    }
}
