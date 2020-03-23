package com.hthk.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.hthk.crm.web.rest.TestUtil;

public class GroupMemberTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupMember.class);
        GroupMember groupMember1 = new GroupMember();
        groupMember1.setId("id1");
        GroupMember groupMember2 = new GroupMember();
        groupMember2.setId(groupMember1.getId());
        assertThat(groupMember1).isEqualTo(groupMember2);
        groupMember2.setId("id2");
        assertThat(groupMember1).isNotEqualTo(groupMember2);
        groupMember1.setId(null);
        assertThat(groupMember1).isNotEqualTo(groupMember2);
    }
}
