package br.gov.mt.controladoria.scsp.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MinioConfiguration {

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.endpoint}")
    private String minioUrl;

    @Bean
    @Primary
    public MinioClient minioClient() {
        return new MinioClient.Builder()        		
        		.endpoint(minioUrl)
                .credentials(accessKey, secretKey)
                .build();
    }

}
