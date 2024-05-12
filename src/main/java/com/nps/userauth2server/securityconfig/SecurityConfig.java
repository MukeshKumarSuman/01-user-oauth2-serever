package com.nps.userauth2server.securityconfig;

//@Configuration
public class SecurityConfig {
    /*
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/account", "/balance", "/cards", "/loans").authenticated()
//                .requestMatchers("/contact", "/notices").permitAll());
        http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())
//                .authorizeHttpRequests(requests -> requests.requestMatchers("/account", "/balance", "/cards", "/loans").authenticated()
//                .requestMatchers("/signup", "/notices").permitAll());
        .authorizeHttpRequests(req -> req.anyRequest().permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
    */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> {
//                            try {
//                                requests
//                                        .anyRequest().permitAll()
//                                        .and().cors().disable()
//                                        .csrf().disable();
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                );
//
//        return http.build();
//    }
}


