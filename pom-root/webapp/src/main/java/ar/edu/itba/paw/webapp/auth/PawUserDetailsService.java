package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class PawUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserService us;

    public UserDetails loadUserByUsername(final String mail) throws UsernameNotFoundException {
        User user = null;
        try {
            user = us.findByMail(mail);
        } catch (Exception e) {
            throw new UsernameNotFoundException("No user by the name " + mail);
        }
        final Collection<? extends GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ USER"));
        return new org.springframework.security.core.userdetails.User(mail, user.getPassword(), authorities);
    }

}