package teachin.server.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import teachin.server.entity.HRAccount;

import java.util.Collection;
import java.util.List;

@Data
public class SecurityHRAccount implements UserDetails {
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromAccount(HRAccount account) {
        return new User(
                account.getUsername(), account.getPassword(),
                account.getStatus().equals(Status.ACTIVE),
                account.getStatus().equals(Status.ACTIVE),
                account.getStatus().equals(Status.ACTIVE),
                account.getStatus().equals(Status.ACTIVE),
                account.getRole().getAuthorities()
        );
    }
}
