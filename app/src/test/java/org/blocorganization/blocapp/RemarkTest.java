package org.blocorganization.blocapp;

import org.blocorganization.blocapp.models.Remark;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class RemarkTest {

    @Test
    public void testConstructor() throws Exception {
        Remark like = new Remark("");
        assertNotNull(like);
    }

    @Test
    public void testRemarkValues() throws Exception {
        Remark like = new Remark("");
        assertNotNull(like.getSubtype());
        assertNotNull(like.getUser());
    }


}
