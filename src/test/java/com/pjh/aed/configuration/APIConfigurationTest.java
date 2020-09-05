package com.pjh.aed.configuration;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class APIConfigurationTest extends TestCase {
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