package org.unsj.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import com.github.jknack.mwa.Startup;
import com.github.jknack.mwa.jpa.JpaModule;

@Configuration
public class TodoApp extends Startup {

  @Override
  protected Class<?>[] imports() {
    return new Class<?> [] {
        JpaModule.class
    };
  }

  @Bean
  public ViewResolver viewResolver() {
    HandlebarsViewResolver viewResolver = new HandlebarsViewResolver();
    viewResolver.setSuffix(".html");
    viewResolver.setCache(false);
    viewResolver.setExposeRequestAttributes(true);

    return viewResolver;
  }
}
