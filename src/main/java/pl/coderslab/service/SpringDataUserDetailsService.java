package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.coderslab.model.Role;
import pl.coderslab.model.User;
import pl.coderslab.security.CurrentUser;

import java.util.HashSet;
import java.util.Set;


public class SpringDataUserDetailsService implements UserDetailsService { //implementacja serwisu UserDetailsService ktory wywolywany jest
    // przez spring security podczas autoryzacji
    // podczas loadUserByUsername szukamy usera w naszym serwisie (DB)


    @Autowired
    private UserService userService;

    @Override
    public CurrentUser loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByLogin(s);
        if (user == null) {throw new UsernameNotFoundException(s); }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())); //nadane uprawnienia
        }

        return new CurrentUser(user.getLogin(),user.getPassword(), grantedAuthorities, user);
    }
}
