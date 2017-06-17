package net.henryco.hblog.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;


/**
 * @author Henry on 14/06/17.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {WebConfiguration.class})
public class WebConfiguration extends WebMvcConfigurerAdapter {

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

//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/index").setViewName("index");
//	}



	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		final String workDir = System.getProperty("user.dir");
		final String homeDir = System.getProperty("user.home");
		System.out.println(
				"\n***\n\n" +
				"WORK DIR: "+workDir+"\n" +
				"HOME DIR: "+homeDir+"\n" +
				"\n***\n"
		);

		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/");

		registry.addResourceHandler("/rel/**")
				.addResourceLocations("file:" + workDir + File.separator);

		registry.addResourceHandler("/abs/**")
				.addResourceLocations("file:" + homeDir + File.separator);
	}
}
