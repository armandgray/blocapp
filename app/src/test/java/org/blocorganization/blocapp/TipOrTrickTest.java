package org.blocorganization.blocapp;

import org.blocorganization.blocapp.models.TipOrTrick;
import org.junit.Assert;
import org.junit.Test;

public class TipOrTrickTest {

    @Test
    public void createObjectWithBuilder() throws Exception {
        TipOrTrick tip = new TipOrTrick.Builder("").build();
        Assert.assertNotNull(tip);
    }

    @Test
    public void createValuesWithBuilder() throws Exception {
        TipOrTrick tip = new TipOrTrick.Builder("")
                .description("")
                .admin("")
                .icon(0).build();
        Assert.assertNotNull(tip.getTitle());
        Assert.assertNotNull(tip.getDescription());
        Assert.assertNotNull(tip.getAdmin());
        Assert.assertNotNull(tip.getIconId());
    }

    @Test
    public void setValuesWithBuilder() throws Exception {
        TipOrTrick tip = new TipOrTrick.Builder("Use JSTOR")
                .description("When writing essays, always start by writing out your ideas and then find sources on JSTOR to cite. Not the other way around!")
                .admin("Armand Gray")
                .icon(R.drawable.ic_book_open_variant_white_48dp).build();
        Assert.assertNotEquals(tip.getTitle(), "");
        Assert.assertNotEquals(tip.getDescription(), "");
        Assert.assertNotEquals(tip.getAdmin(), "");
        Assert.assertNotEquals(tip.getIconId(), "");
    }

    // isPublic

}