package engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@SpringBootApplication
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class) //??? To test necessarity
public class WebQuizEngine {

    //private static final Logger log = LoggerFactory.getLogger(WebQuizEngine.class);

    public static void main(String[] args) {
        SpringApplication.run(WebQuizEngine.class, args);
    }

}
