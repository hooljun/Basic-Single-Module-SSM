package com.config;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.paths.SwaggerPathProvider;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableSwagger
@ComponentScan(basePackages ={"com.youmeek.ssm.module.user.controller"})
public class MySwaggerConfig extends WebMvcConfigurerAdapter {
    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }
    /**
     * 链式编程 来定制API样式
     * 后续会加上分组信息
     * @return
     */
    @Bean
    public SwaggerSpringMvcPlugin customImplementation(){
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .includePatterns(".*")
//            .pathProvider(new GtPaths())
                .apiVersion("0.0.1")
                .swaggerGroup("user");
    }
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "bugkillers-back API",
                "bugkillers 后台API文档",
                "<a href='http://127.0.0.1:9081/api' '='' style='color: rgb(59, 115, 175); text-decoration: none; border-radius: 0px !important; border: 0px !important; bottom: auto !important; float: none !important; height: auto !important; left: auto !important; margin: 0px !important; outline: 0px !important; overflow: visible !important; padding: 0px !important; position: static !important; right: auto !important; top: auto !important; vertical-align: baseline !important; width: auto !important; box-sizing: content-box !important; min-height: inherit !important; background: none !important;'>http://127.0.0.1:9081/api",
        "bugkillers@163.com",
                "My License",
                "My Apps API License URL");
        return apiInfo;
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    class GtPaths extends SwaggerPathProvider{
        @Override
        protected String applicationPath() {
            return "/restapi";
        }
        @Override
        protected String getDocumentationPath() {
            return "/restapi";
        }
    }

}