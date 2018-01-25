package by.matrosov.usermanagment.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomAuditorAware implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
