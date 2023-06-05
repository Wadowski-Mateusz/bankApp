package bankApp.security;

import bankApp.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final static String HEADER_AUTH = "authorization";
    public final static String TOKEN_START = "owner ";

    // This one is autowired
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserService bankUserService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String token = getJwtFromRequest(request);
        if (StringUtils.hasText(token) && jwtProvider.validate(token)) {
            String login = jwtProvider.getLoginFromJwt(token);

            UserDetails securityUserDetails = bankUserService.loadUserByUsername(login);
            UsernamePasswordAuthenticationToken authnToken = new UsernamePasswordAuthenticationToken(
                    securityUserDetails,
                    null,
                    securityUserDetails.getAuthorities()
            );
            authnToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authnToken);
        }

        filterChain.doFilter(request, response);
    }


    private String getJwtFromRequest(HttpServletRequest request) {
        String tokenOwner = request.getHeader(HEADER_AUTH);
        if (StringUtils.hasText(tokenOwner) && tokenOwner.startsWith(TOKEN_START)) {
            System.out.println("if NO trim");
            return tokenOwner.substring(TOKEN_START.length());
        } else if (StringUtils.hasText(tokenOwner) && tokenOwner.startsWith(TOKEN_START.trim())) {
            System.out.println("if trim");
            return tokenOwner.substring(TOKEN_START.trim().length());
        } else {
            System.out.println("else");
            return null;
        }
    }


}
