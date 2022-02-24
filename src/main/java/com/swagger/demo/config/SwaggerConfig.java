package com.swagger.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration//配置类
@EnableSwagger2//开启Swagger2
public class SwaggerConfig {

    //配置Swagger的Bean实例
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, customerResponseMessage())
                .globalResponseMessage(RequestMethod.GET, customerResponseMessage())
                .forCodeGeneration(true)
                .select()

                //为当前包下的controller生成API文档
                .apis(RequestHandlerSelectors.basePackage("com.swagger.demo.controller"))

                //为有@Api注解的Controller生成API文档
                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))

                //为有@ApiOperation注解的方法生成API文档
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));

                //指定包下的所有请求
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(unifiedAuth())//调用API时把token、type作为header内容发送到服务端进行认证操作（全局设置）
                .globalOperationParameters(defaultHeader());//调用API时把token、type作为header内容发送到服务端进行认证操作（单个设置）
    }

    //配置API的基本信息（会在http://项目实际地址/swagger-ui.html页面显示）
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试API文档标题")//名字
                .description("测试API接口文档描述")//额外描述
                .contact(new Contact("联系人名称", "官方网站", "联系方式"))//联系信息
                .version("1.0").description("版本")// API版本
                .license("Apache 2.0") //license 规范
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")//规范地址
                .build();
    }

    private static List<Parameter> defaultHeader(){
        ParameterBuilder appType = new ParameterBuilder();
        appType.name("app-type").description("应用类型").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        ParameterBuilder appToken = new ParameterBuilder();
        appToken.name("app-token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        List<Parameter> pars = new ArrayList<>();
        pars.add(appType.build());
        pars.add(appToken.build());
        return pars;
    }

    private static List<ApiKey> unifiedAuth() {
        List<ApiKey> arrayList = new ArrayList();
        arrayList.add(new ApiKey("app-type", "app-type", "header"));
        arrayList.add(new ApiKey("app-token", "app-token", "header"));
        return arrayList;
    }

    private List<ResponseMessage> customerResponseMessage() {
        List<ResponseMessage> list = new ArrayList<>();
        list.add(new ResponseMessageBuilder().code(200).message("请求成功").build());
        list.add(new ResponseMessageBuilder().code(201).message("资源创建成功").build());
        list.add(new ResponseMessageBuilder().code(204).message("服务器成功处理了请求，但不需要返回任何实体内容").build());
        list.add(new ResponseMessageBuilder().code(400).message("请求失败,具体查看返回业务状态码与对应消息").build());
        list.add(new ResponseMessageBuilder().code(401).message("请求失败,未经过身份认证").build());
        list.add(new ResponseMessageBuilder().code(405).message("请求方法不支持").build());
        list.add(new ResponseMessageBuilder().code(415).message("请求媒体类型不支持").build());
        list.add(new ResponseMessageBuilder().code(500).message("服务器遇到了一个未曾预料的状况,导致了它无法完成对请求的处理").build());
        list.add(new ResponseMessageBuilder().code(503).message("服务器当前无法处理请求,这个状况是临时的，并且将在一段时间以后恢复").build());
        return list;
    }

}