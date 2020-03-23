package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class CfsServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CfsService.class);
        CfsService cfsService1 = new CfsService();
        cfsService1.setId("id1");
        CfsService cfsService2 = new CfsService();
        cfsService2.setId(cfsService1.getId());
        assertThat(cfsService1).isEqualTo(cfsService2);
        cfsService2.setId("id2");
        assertThat(cfsService1).isNotEqualTo(cfsService2);
        cfsService1.setId(null);
        assertThat(cfsService1).isNotEqualTo(cfsService2);
    }
}
