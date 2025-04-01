package br.gov.mt.controladoria.scsp.component;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class CustomMessageSource {

    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); // Aponta para messages.properties
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}

