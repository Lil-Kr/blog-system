package com.cy.sys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;


/**
 * 拦截器
 * @author Ryo
 * @date 2018-6-21 10:12:29
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Bean
	public ApiInterceptor apiInterceptor(){
		return new ApiInterceptor();
	}

	/**
	 * 拦截器注册 设置拦截接口
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
	}

    /**
     * 如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。 需要重新指定静态资源
     * @param registry
     */
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/META-INF/resources/");
//		super.addResourceHandlers(registry);
	}

    /**
     * 跨域处理
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
          .allowedOriginPatterns("*")
          .allowCredentials(true)
          .allowedMethods("GET", "POST", "DELETE", "PUT")
          .maxAge(3600);
    }
    
    private CorsConfiguration addcorsConfig() {
      CorsConfiguration corsConfiguration = new CorsConfiguration();
      List<String> list = new ArrayList<>();
      list.add("*");
      corsConfiguration.setAllowedOrigins(list);
    /**
     * 请求常用的三种配置，*代表允许所有，当时你也可以自定义属性（比如header只能带什么，只能是post方式等等）
     */
      corsConfiguration.addAllowedOrigin("*");
      corsConfiguration.addAllowedHeader("*"); 
      corsConfiguration.addAllowedMethod("*"); 
      return corsConfiguration;
    }
    
    @Bean
    public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", addcorsConfig());
      return new CorsFilter(source);
    }

}
