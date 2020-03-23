package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class OfferSalesChannelTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfferSalesChannel.class);
        OfferSalesChannel offerSalesChannel1 = new OfferSalesChannel();
        offerSalesChannel1.setId("id1");
        OfferSalesChannel offerSalesChannel2 = new OfferSalesChannel();
        offerSalesChannel2.setId(offerSalesChannel1.getId());
        assertThat(offerSalesChannel1).isEqualTo(offerSalesChannel2);
        offerSalesChannel2.setId("id2");
        assertThat(offerSalesChannel1).isNotEqualTo(offerSalesChannel2);
        offerSalesChannel1.setId(null);
        assertThat(offerSalesChannel1).isNotEqualTo(offerSalesChannel2);
    }
}
