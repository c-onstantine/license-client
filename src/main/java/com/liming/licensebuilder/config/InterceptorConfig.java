package com.liming.licensebuilder.config;


import com.liming.licensebuilder.interceptor.LicenseCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LicenseCheckInterceptor licenseCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //添加要拦截的url
        registry.addInterceptor(licenseCheckInterceptor)
                // 拦截的路径
                .addPathPatterns("/**");
                // 放行的路径
//                .excludePathPatterns("/admin/login");
    }
}
