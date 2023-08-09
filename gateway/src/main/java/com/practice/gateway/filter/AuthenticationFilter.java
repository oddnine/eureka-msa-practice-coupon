package com.practice.gateway.filter;

import com.practice.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (validator.isSecured.test(request)) {
                List<String> authHeaders = request.getHeaders().get(HttpHeaders.AUTHORIZATION);

                if (authHeaders == null || authHeaders.isEmpty()) {
                    throw new RuntimeException("Missing authorization header");
                }

                String authHeader = authHeaders.get(0);

                if (authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);

                    try {
                        jwtUtil.validateToken(token);
                    } catch (Exception e) {
                        throw new RuntimeException("Unauthorized access to application");
                    }
                } else {
                    throw new RuntimeException("Invalid authorization header format");
                }
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                ServerHttpResponse response = exchange.getResponse();
                System.out.println("Custom Post filter: response code: " + response.getStatusCode());
            }));
        };
    }

    public static class Config {

    }
}