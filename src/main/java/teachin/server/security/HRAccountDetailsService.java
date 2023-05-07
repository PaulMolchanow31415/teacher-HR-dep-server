package teachin.server.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import teachin.server.entity.HRAccount;
import teachin.server.repo.HRAccountRepo;

@Service("teachin_hr_account_service")
public class HRAccountDetailsService implements UserDetailsService {
    private final HRAccountRepo repo;

    public HRAccountDetailsService(HRAccountRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HRAccount account = repo.findByUsername(username).orElseThrow(() ->
                                new UsernameNotFoundException("Аккаунт с таким логином не найден"));
        return SecurityHRAccount.fromAccount(account);
    }
}
