package com.pjh.aed.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class APICallerConfigurationTest {
    @Autowired
    private AEDConfiguration AEDConfiguration;

    @Autowired
    private ErmctConfiguration ErmctConfiguration;

    @Test
    public void whenFactoryProviedThenPropertiesInjectionToAEDConfiguration(){
        assertThat(AEDConfiguration.getUrl()).isEqualTo("http://apis.data.go.kr/B552657/AEDInfoInqireService/");
        assertThat(AEDConfiguration.getApikey()).isEqualTo("8WkFeHl8obzSp%2BLAlvjpzvhaJHE%2BymMSwJwP8IBRGTSzzJs2hSvDRn8lchYK2hEl8k0NhnZzYu9UNckgs7mf3A%3D%3D");
        assertThat(AEDConfiguration.getDataformat()).isEqualTo("xml");

        System.out.println(AEDConfiguration.getData());
    }

    @Test
    public void whenFactoryProviedThenPropertiesInjectionToErmctConfiguration(){
        assertThat(ErmctConfiguration.getUrl()).isEqualTo("http://apis.data.go.kr/B552657/ErmctInfoInqireService/");
        assertThat(ErmctConfiguration.getApikey()).isEqualTo("8WkFeHl8obzSp%2BLAlvjpzvhaJHE%2BymMSwJwP8IBRGTSzzJs2hSvDRn8lchYK2hEl8k0NhnZzYu9UNckgs7mf3A%3D%3D");
        assertThat(ErmctConfiguration.getDataformat()).isEqualTo("xml");

        System.out.println(ErmctConfiguration.getData());
    }
}