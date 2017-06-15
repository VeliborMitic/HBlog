package net.henryco.hblog.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Henry on 14/06/17.
 */
@Configuration
@Import({SecurityConfiguration.class})
public class MainConfiguration {

}
