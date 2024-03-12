package com.velikanovdev.sportcenterplatform.repository.configuration;

import com.velikanovdev.sportcenterplatform.job.SaveAddressJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ImportResource({
        "classpath:spring/root-context.xml"
})
@ComponentScan(
        basePackages = {
                "com.velikanovdev.sportcenterplatform.repository",
                "com.velikanovdev.sportcenterplatform.job",
                "com.velikanovdev.sportcenterplatform.entity"
        }
)
@EnableJpaRepositories(basePackages="com.velikanovdev.sportcenterplatform.repository")
public class AddressRepositoryTestConfiguration {
    @Bean(name = SaveAddressJob.NAME)
    public SaveAddressJob saveAddressJob(){
        return new SaveAddressJob();
    }
}
