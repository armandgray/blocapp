package org.blocorganization.blocapp;

import org.blocorganization.blocapp.models.RecordType;
import org.blocorganization.blocapp.models.Remark;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class RemarkTest {

    private static final String ARMAND_GRAY = "Armand Gray";

    @Test
    public void testConstructor() throws Exception {
        Remark like = new Remark("");
        assertNotNull(like);
    }

    @Test
    public void testRemarkNotNull() throws Exception {
        Remark like = new Remark("");
        assertNotNull(like.getSubtype());
        assertNotNull(like.getUser());
    }

    @Test
    public void testRemarkValues() throws Exception {
        Remark like = new Remark(ARMAND_GRAY);
        assertEquals(like.getSubtype(), RecordType.LIKE.toString());
        assertEquals(like.getUser(), ARMAND_GRAY);
    }

}
