package com.liming.licensebuilder.listener;

import com.liming.licensebuilder.entity.LicenseVerifyParam;
import com.liming.licensebuilder.license.LicenseVerify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 在项目启动时安装证书
 */
@Slf4j
@Component
public class LicenseCheckListener implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 证书subject
     */
    private String subject = "buptnsrc_license";

    /**
     * 公钥别称
     */
    private String publicAlias = "publicCert";

    /**
     * 访问公钥库的密码
     */
    @Value("${license.storePass}")
    private String storePass;

    /**
     * 证书生成路径
     */
    @Value("${license.licensePath}")
    private String licensePath;

    /**
     * 密钥库存储路径
     */
    @Value("${license.publicKeysStorePath}")
    private String publicKeysStorePath;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //root application context 没有parent
        ApplicationContext context = event.getApplicationContext().getParent();
        if(context == null){
            if(!StringUtils.isEmpty(licensePath)){
                log.info("++++++++ 开始安装证书 ++++++++");

                LicenseVerifyParam param = new LicenseVerifyParam();
                param.setSubject(subject);
                param.setPublicAlias(publicAlias);
                param.setStorePass(storePass);
                param.setLicensePath(licensePath);
                param.setPublicKeysStorePath(publicKeysStorePath);

                LicenseVerify licenseVerify = new LicenseVerify();
                //安装证书
                licenseVerify.install(param);

                log.info("++++++++ 证书安装结束 ++++++++");
            }
        }
    }
}
