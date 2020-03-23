package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class OfferCustomerClassTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfferCustomerClass.class);
        OfferCustomerClass offerCustomerClass1 = new OfferCustomerClass();
        offerCustomerClass1.setId("id1");
        OfferCustomerClass offerCustomerClass2 = new OfferCustomerClass();
        offerCustomerClass2.setId(offerCustomerClass1.getId());
        assertThat(offerCustomerClass1).isEqualTo(offerCustomerClass2);
        offerCustomerClass2.setId("id2");
        assertThat(offerCustomerClass1).isNotEqualTo(offerCustomerClass2);
        offerCustomerClass1.setId(null);
        assertThat(offerCustomerClass1).isNotEqualTo(offerCustomerClass2);
    }
}
