package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class EfLockerLocationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EfLockerLocation.class);
        EfLockerLocation efLockerLocation1 = new EfLockerLocation();
        efLockerLocation1.setId("id1");
        EfLockerLocation efLockerLocation2 = new EfLockerLocation();
        efLockerLocation2.setId(efLockerLocation1.getId());
        assertThat(efLockerLocation1).isEqualTo(efLockerLocation2);
        efLockerLocation2.setId("id2");
        assertThat(efLockerLocation1).isNotEqualTo(efLockerLocation2);
        efLockerLocation1.setId(null);
        assertThat(efLockerLocation1).isNotEqualTo(efLockerLocation2);
    }
}
