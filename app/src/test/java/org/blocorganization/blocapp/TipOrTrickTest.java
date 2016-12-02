package org.blocorganization.blocapp;

import org.blocorganization.blocapp.models.TipOrTrick;
import org.junit.Assert;
import org.junit.Test;

import static org.blocorganization.blocapp.models.RecordType.*;

public class TipOrTrickTest {

    @Test
    public void testBuilder() throws Exception {
        TipOrTrick tip = new TipOrTrick.Builder("").build();
        Assert.assertNotNull(tip);
    }

    @Test
    public void testBuilderValuesNonNull() throws Exception {
        TipOrTrick tip = new TipOrTrick.Builder("")
                .description("")
                .admin("")
                .subType("")
                .isPublic(false).build();
        Assert.assertNotNull(tip.getTitle());
        Assert.assertNotNull(tip.getDescription());
        Assert.assertNotNull(tip.getAdmin());
        Assert.assertNotNull(tip.getIconId());
    }

    @Test
    public void testBuilderValuesSet() throws Exception {
        TipOrTrick tip = new TipOrTrick.Builder("Use JSTOR")
                .description("When writing essays, always start by writing out your ideas and then find sources on JSTOR to cite. Not the other way around!")
                .admin("Armand Gray")
                .subType(ACADEMIC.toString())
                .isPublic(true).build();
        Assert.assertEquals(tip.getTitle(), "Use JSTOR");
        Assert.assertEquals(tip.getDescription(), "When writing essays, always start by writing out your ideas and then find sources on JSTOR to cite. Not the other way around!");
        Assert.assertEquals(tip.getAdmin(), "Armand Gray");
        Assert.assertEquals(tip.isPublic, true);
    }

    @Test
    public void testIcon() throws Exception {

    }
}