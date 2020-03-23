package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class ProductMmsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductMms.class);
        ProductMms productMms1 = new ProductMms();
        productMms1.setId("id1");
        ProductMms productMms2 = new ProductMms();
        productMms2.setId(productMms1.getId());
        assertThat(productMms1).isEqualTo(productMms2);
        productMms2.setId("id2");
        assertThat(productMms1).isNotEqualTo(productMms2);
        productMms1.setId(null);
        assertThat(productMms1).isNotEqualTo(productMms2);
    }
}
