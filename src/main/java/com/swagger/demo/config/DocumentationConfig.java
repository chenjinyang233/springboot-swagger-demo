package com.swagger.demo.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        //aigou网关前缀,employee网关路径   ；swagger的路径 ，以后增加了接口就在这配置就ok
        resources.add(swaggerResource("子系统一", "/v2/api-docs", "2.0"));
        resources.add(swaggerResource("子系统二", "/v2/api-docs", "2.0"));
        resources.add(swaggerResource("子系统三", "/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}