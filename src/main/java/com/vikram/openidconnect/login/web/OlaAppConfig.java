package com.vikram.openidconnect.login.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.vikram.openidconnect.login.core.IIdentityFetcher;
import com.vikram.openidconnect.login.web.identity.IdentityResovler;

@Configuration
@ComponentScan("com.vikram.openidconnect.login.web")
public class OlaAppConfig extends WebMvcConfigurerAdapter{

	@Autowired
	private IIdentityFetcher identityFetcher;
	
	@Autowired
	private IAccessToken accessToken;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(getIdentityResolver());
	}
	
	public IdentityResovler getIdentityResolver(){
		return new IdentityResovler(identityFetcher,accessToken);
	}
}