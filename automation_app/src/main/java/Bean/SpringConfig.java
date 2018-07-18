/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Service.Codenvy;
import Service.CreateWebdriver;
import Service.PathDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans({
    @ComponentScan(basePackages = "Bean")
    ,@ComponentScan(basePackages = "Service")
})
public class SpringConfig {

    @Bean
    public Codenvy codenvy() {
        return new Codenvy();
    }
    
    @Bean
    public CreateWebdriver createWebdriver() {
        return new CreateWebdriver();
    }
    
    @Bean
    public PathDriver pathDriver() {
        return new PathDriver();
    }
    
    @Bean
    public SystemConfig systemConfig() {
        return new SystemConfig();
    }
}
