package bankApp.security;

import bankApp.Consts;
import bankApp.services.JwtService;
import bankApp.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService usersService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader(Consts.AUTH_HEADER);

        if (authHeader == null || !authHeader.startsWith(Consts.AUTH_HEADER_START)) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwt = authHeader.substring(Consts.AUTH_HEADER_START.length());
        final String login = jwtService.extractLogin(jwt);

        if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails securityUserDetails = this.usersService.loadUserByUsername(login);
            if (jwtService.isTokenValid(jwt, securityUserDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        securityUserDetails,
                        null,
                        securityUserDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
