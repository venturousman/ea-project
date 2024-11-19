package cs544.security;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cs544.services.JwtUtilityService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtilityService jwtUtilityService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Check if the security context is already set up
        var auth = SecurityContextHolder.getContext().getAuthentication();
        // Look for the Authorizataion header
        String authorizationHeader = request.getHeader("Authorization");
        if (auth != null
                || authorizationHeader == null
                || !authorizationHeader.startsWith("Bearer ")) {
            // exit without setting up the security context
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT token
        String jwtToken = authorizationHeader.substring(7);
        Claims claims = jwtUtilityService.extractAllClaims(jwtToken);
        String username = claims.getSubject();
        Date expiration = claims.getExpiration();
        if (username == null || expiration == null || expiration.before(new Date())) {
            // exit without setting up the security context
            filterChain.doFilter(request, response);
            return;
        }

        // Setup the security context
        @SuppressWarnings("unchecked")
        List<String> roleNames = claims.get("roles", List.class);
        String[] roles = roleNames.toArray(new String[0]);

        // var firstname = claims.get("firstname", String.class);
        // var lastname = claims.get("lastname", String.class);
        // var userId = claims.get("userId", Long.class);
        // var user = new cs544.models.User(username, "doesn't like empty", firstname,
        // lastname);
        // user.setId(userId);

        // NOTE: if use cs544.models.User, .getAuthorities method will have no prefix ROLE_, just Admin / User
        // coz we're using UserDetails, it has prefix ROLE_, .requestMatchers("/users/**").hasAuthority("ROLE_Admin")

        UserDetails userDetails = User.withUsername(username).password("doesn't like empty").roles(roles).build();
        var token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(token);

        filterChain.doFilter(request, response);
    }

}
