package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.PropertyDao;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

@ComponentScan(
        basePackageClasses = PropertyServiceImpl.class,
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = ASSIGNABLE_TYPE, value = PropertyServiceImpl.class)
        })@Configuration
public class TestConfig {

    @Bean
    public PropertyDao propertyDao(){
       return Mockito.mock(PropertyDao.class);
    }

}

