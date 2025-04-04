package com.example.Crypto.Repositories;

import com.example.Crypto.CompositeKeys.UserCryptoId;
import com.example.Crypto.Entities.IntermediaryEntities.UserCrypto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCryptoRepository extends JpaRepository<UserCrypto, UserCryptoId> {

}
