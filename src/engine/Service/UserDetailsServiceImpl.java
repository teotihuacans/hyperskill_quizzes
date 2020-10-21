package engine.Service;

import engine.Repository.UserRepository;
import engine.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository users;

    User userLogged;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        User user = users.findUserByEmail(userEmail);
        if (user == null){
            throw new UsernameNotFoundException(userEmail + " was not found");
        }
        this.userLogged = user;

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("USER")
                //AuthorityUtils.createAuthorityList("USER") //new SimpleGrantedAuthority(“user”)
                //AuthorityUtils.createAuthorityList(user.getRoles())
        );
    }

    /*public User getUserLogged() {
        return userLogged;
    }*/
}

