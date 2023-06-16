package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthetificationFilter jwtAuthetificationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/fixture/getFixturesBySportAndDate",
                        "/user/register",
                        "/user/authenticate",
                        "/league/getLeaguesByFixturePlayedAtDateInSport",
                        "/team/fillTeamsHockey",
                        "/fixture/fillFixturesHockey",
                        "/fixture/fillFixturesBasketball",
                        "/league/fillFootballLeagues")
                        "/league/fillVolleyballLeagues",
                        "/team/getTeamsBySport")
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
}
