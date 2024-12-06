package com.monika.monischool.audit;


import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MoniSchoolInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> moniMap = new HashMap<>();
        moniMap.put("App Name", "MoniSchool");
        moniMap.put("App Description", "Moni School Web Application for Students and Admin");
        moniMap.put("App Version", "1.0.0");
        moniMap.put("Contact Email", "info@monischool.com");
        moniMap.put("Contact Mobile", "+1(48) 663 0687");
        builder.withDetail("monischool-info", moniMap);
    }

}
