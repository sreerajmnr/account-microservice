package com.banking.core.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class LocaleConfig {

	private static final String BASE_NAME = "i18n/messages";

	private static final String DEFAULT_ENCODING = "UTF-8";

	@Bean
	AcceptHeaderLocaleResolver localResolver() {
		final AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}

	@Bean
	MessageSource messageSource() {
		final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename(BASE_NAME);
		source.setDefaultEncoding(DEFAULT_ENCODING);
		return source;
	}

}
