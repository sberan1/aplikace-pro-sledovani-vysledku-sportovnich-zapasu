package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthetificationFilter jwtAuthetificationFilter;
    private final AuthenticationProvider authenticationProvider;
    @Value("${allowed-cors}")
    public String cors;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/fixture/getFixturesBySportAndDate",
                        "/auth/register",
                        "/auth/authenticate",
                        "/league/getLeaguesByFixturePlayedAtDateInSport",
                        "/team/fillTeamsHockey",
                        "/fixture/fillFixturesHockey",
                        "/fixture/fillFixturesFootball",
                        "/fixture/fillFixturesBasketball",
                        "/league/fillFootballLeagues",
                        "/league/fillVolleyballLeagues",
                        "/team/getTeamsBySport",
                        "/fixture/getFixturesById",
                        "/fixture/fillFixturesVolleyball",
                        "/team/getTeamInfoById",
                        "/fixture/getFixturesByTeamIdAndDateFromToday",
                        "/fixture/getFixturesByTeamIdAndDateBeforeToday",
                        "/fixture/getFixtureInfoById",
                        "/team/search",
                        "/user/checkEmail")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthetificationFilter, UsernamePasswordAuthenticationFilter.class);

        return security.build();
    }
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of(this.cors.split(",")));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
