package com.pjh.aed.api;

import com.pjh.aed.api.aed.AEDAPICaller;
import com.pjh.aed.exception.AEDInternalServerException;
import org.springframework.stereotype.Component;

@Component
public class APIFactory {
    final AEDAPICaller aedApiCaller;

    public APIFactory(AEDAPICaller aedApiCaller) {
        this.aedApiCaller = aedApiCaller;
    }

    public APICaller getAPICaller(APICaller.APIs apiType) {
        switch (apiType){
            case PUBLIC_DATA_AED:
                return aedApiCaller;

            case ETC_API:
        }
        throw new AEDInternalServerException();
    }
}
