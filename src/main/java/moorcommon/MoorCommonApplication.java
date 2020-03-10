package moorcommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ControllerAdvice;


@SpringBootApplication
@EnableScheduling
@ControllerAdvice
public class MoorCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoorCommonApplication.class, args);
    }

}

