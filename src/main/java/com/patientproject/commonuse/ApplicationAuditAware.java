package com.patientproject.commonuse;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.patientproject.entity.User;

import lombok.NonNull;


public class ApplicationAuditAware implements AuditorAware<String> {

	
	@NonNull
	@Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null|| !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken){
            return Optional.of("unauthorized");
        }
        UserDetails userPrincipal=(UserDetails) authentication.getPrincipal();
        return Optional.ofNullable(userPrincipal.getUsername());
    }
}
