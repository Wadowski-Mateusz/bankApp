package bankApp.services;


import bankApp.entities.Token;
import bankApp.entities.User;
import bankApp.repositories.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenService {
    TokenRepository tokenRepository;

    public Token saveToken(User user, String jwtToken) {
        expireAllUserTokens(user.getId());
        Token token = new Token(UUID.randomUUID(), jwtToken, false, user);
        return tokenRepository.save(token);
    }

    public void expireAllUserTokens(UUID userId) {
        List<Token> tokens = tokenRepository.findAllByUserIdAndExpiredIsFalse(userId);

        tokens.forEach(t -> {
            t.setExpired(true);
        });
        tokenRepository.saveAll(tokens);
    }

    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }


    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }
}
