package org.multilinguals.example.infrastructure.security;

import org.multilinguals.example.query.user.UserDetailsView;
import org.multilinguals.example.query.user.UserDetailsViewRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestValidationFilter extends BasicAuthenticationFilter {
    private UserDetailsViewRepository userDetailsViewRepository;

    public RequestValidationFilter(AuthenticationManager authenticationManager, UserDetailsViewRepository userDetailsViewRepository) {
        super(authenticationManager);
        this.userDetailsViewRepository = userDetailsViewRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Token ")) {
            chain.doFilter(request, response);
            return;
        }
        Authentication authentication = buildAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private Authentication buildAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        // 判断请求头中的token是否存在
        if (token == null || token.isEmpty()) {
            return null;
        }

        // 如果token存在，判断是否有效
        String sessionId = token.substring(6);
        List<UserDetailsView> userList = this.userDetailsViewRepository.findByUserSessionId(sessionId);
        if (userList != null && userList.size() > 0) {
            String userId = userList.get(0).getId();
            request.setAttribute("reqSenderId", userId);

            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            // TODO 需要最终变更为从数据库读取
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            // authorities.add(new SimpleGrantedAuthority("ROLE_REST_ADMIN"));
            return new UsernamePasswordAuthenticationToken(userId, null, authorities);
        } else {
            return null;
        }
    }
}
