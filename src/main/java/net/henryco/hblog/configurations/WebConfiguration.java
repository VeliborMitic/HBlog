package net.henryco.hblog.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;

import static java.io.File.separator;


/**
 * @author Henry on 14/06/17.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {WebConfiguration.class})
public class WebConfiguration extends WebMvcConfigurerAdapter {

	public static final String REL_FILE_PATH = System.getProperty("user.dir");
	public static final String ABS_FILE_PATH = System.getProperty("user.home");

	public static final String AVATAR_UPLOAD_DIR = REL_FILE_PATH + separator + "res" +separator + "public" + separator + "av" + separator;
	public static final String UPLOAD_PATH = REL_FILE_PATH + separator + "res" + separator + "public" + separator;
	public static final String DEF_PATH = separator + "rel" + separator + "res" + separator + "public" + separator;
	public static final String USER_FILE_PATH = REL_FILE_PATH + separator + "res" + separator + "private" + separator;


	@Bean
	@Description("Thymeleaf template resolver serving HTML 5")
	public ClassLoaderTemplateResolver templateResolver() {

		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("templates/");
		templateResolver.setCacheable(false);
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	@Bean
	@Description("Thymeleaf template engine with Spring integration")
	public SpringTemplateEngine templateEngine() {

		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.addDialect(new SpringSecurityDialect());
		return templateEngine;
	}

	@Bean
	@Description("Thymeleaf view resolver")
	public ViewResolver viewResolver() {

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		return viewResolver;
	}


	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/access/login").setViewName("login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/");

		registry.addResourceHandler("/rel/**")
				.addResourceLocations("file:" + REL_FILE_PATH + File.separator);

		registry.addResourceHandler("/abs/**")
				.addResourceLocations("file:" + ABS_FILE_PATH + File.separator);

		System.out.println(
				"\n***\n\n" +
						"WORK DIR: "+REL_FILE_PATH+" \t[mapping: /rel/**" + "]\n" +
						"HOME DIR: "+ABS_FILE_PATH+" \t[mapping: /abs/**" + "]\n"+
				"\n***\n"
		);

	}
}
