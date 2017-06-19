package net.henryco.hblog.mvc.model.account.details;

import net.henryco.hblog.mvc.model.account.AuthUserProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Henry on 18/06/17.
 */
public class DetailsUserProfile implements UserDetails {

	private final AuthUserProfile authUserProfile;
	private final String userName;

	public DetailsUserProfile(AuthUserProfile authUserProfile,
							  String username) {
		this.authUserProfile = authUserProfile;
		this.userName = username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(authUserProfile.getAuthoritiesArray());
	}

	@Override
	public String getPassword() {
		return authUserProfile.getPassword();
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !authUserProfile.isLocked();
	}

	@Override
	public boolean isEnabled() {
		return authUserProfile.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}
