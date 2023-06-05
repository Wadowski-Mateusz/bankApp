package bankApp.services;

import bankApp.Consts;
import bankApp.entities.Token;
import bankApp.services.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenService tokenService;


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        final String authHeader = request.getHeader(Consts.AUTH_HEADER);
        if (authHeader == null || !authHeader.startsWith(Consts.AUTH_HEADER_START)) {
            return;
        }
        final String jwt = authHeader.substring(Consts.AUTH_HEADER_START.length());
        Token token = tokenService.findByToken(jwt).orElse(null);
        if (token != null) {
            token.setExpired(true);
            tokenService.saveToken(token);
        }
    }
}
