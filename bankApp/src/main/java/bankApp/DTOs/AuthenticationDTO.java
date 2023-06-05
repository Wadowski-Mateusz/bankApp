package bankApp.DTOs;

import bankApp.security.JwtAuthenticationFilter;

public class AuthenticationDTO {
        String accessToken;
        String tokenType = JwtAuthenticationFilter.TOKEN_START;

        public AuthenticationDTO(String accessToken) {
            this.accessToken = accessToken;
        }

}
