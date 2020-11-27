package com.pjh.aed.api;

import com.pjh.aed.api.aed.AEDAPICaller;
import com.pjh.aed.exception.AEDInternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class APIFactory {
    AEDAPICaller aedApiCaller;

    public APICaller getAPICaller(APICaller.APIs apiType) {
        switch (apiType){
            case PUBLIC_DATA_AED:
                return aedApiCaller;

            case ETC_API:
        }
        throw new AEDInternalServerException();
    }
}
