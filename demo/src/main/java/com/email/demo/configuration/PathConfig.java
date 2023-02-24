package com.email.demo.configuration;

import com.email.demo.model.CommonPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PathConfig {

    @Value("${spring.mail.properties.mail.file.attached.path}")
    private String attachedpath; // location to store attached file


    @Bean
    public CommonPath getConfigPath() {
        CommonPath cp = new CommonPath();
        cp.setAttachedFilePath(attachedpath);
        return cp;
    }
}
