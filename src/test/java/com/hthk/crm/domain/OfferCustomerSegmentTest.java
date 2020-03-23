package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class OfferCustomerSegmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfferCustomerSegment.class);
        OfferCustomerSegment offerCustomerSegment1 = new OfferCustomerSegment();
        offerCustomerSegment1.setId("id1");
        OfferCustomerSegment offerCustomerSegment2 = new OfferCustomerSegment();
        offerCustomerSegment2.setId(offerCustomerSegment1.getId());
        assertThat(offerCustomerSegment1).isEqualTo(offerCustomerSegment2);
        offerCustomerSegment2.setId("id2");
        assertThat(offerCustomerSegment1).isNotEqualTo(offerCustomerSegment2);
        offerCustomerSegment1.setId(null);
        assertThat(offerCustomerSegment1).isNotEqualTo(offerCustomerSegment2);
    }
}
