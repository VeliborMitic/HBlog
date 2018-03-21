package net.henryco.hblog.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Henry on 14/06/17.
 */
@Configuration
@Import({SecurityConfiguration.class, DaoConfiguration.class})
public class MainConfiguration {


}
