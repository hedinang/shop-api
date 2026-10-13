package com.example.shop_api.configuration;

import com.example.shop_api.model.entity.MongoBaseEntity;
import com.example.shop_api.util.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class BeforeSaveListener extends AbstractMongoEventListener<MongoBaseEntity> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent<MongoBaseEntity> event) {
        MongoBaseEntity base = event.getSource();
        if (StringUtils.isBlank(base.getCreatedAt())) {
            base.setCreatedAt(DateUtil.formatDateTime(new Date()));
        }
        base.setUpdatedAt(DateUtil.formatDateTime(new Date()));
    }
}
