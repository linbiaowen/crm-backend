package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class ProductSimTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductSimType.class);
        ProductSimType productSimType1 = new ProductSimType();
        productSimType1.setId("id1");
        ProductSimType productSimType2 = new ProductSimType();
        productSimType2.setId(productSimType1.getId());
        assertThat(productSimType1).isEqualTo(productSimType2);
        productSimType2.setId("id2");
        assertThat(productSimType1).isNotEqualTo(productSimType2);
        productSimType1.setId(null);
        assertThat(productSimType1).isNotEqualTo(productSimType2);
    }
}
