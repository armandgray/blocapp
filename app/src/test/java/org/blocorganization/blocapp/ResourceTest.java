package org.blocorganization.blocapp;

import org.blocorganization.blocapp.models.Resource;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.blocorganization.blocapp.models.RecordType.ACADEMIC;

public class ResourceTest {

    @Test
    public void testBuilder() throws Exception {
        Resource tip = new Resource.Builder("").build();
        assertNotNull(tip);
    }

    @Test
    public void testBuilderValuesNonNull() throws Exception {
        Resource tip = new Resource.Builder("")
                .description("")
                .admin("")
                .subType("")
                .isPublic(false).build();
        assertNotNull(tip.getTitle());
        assertNotNull(tip.getDescription());
        assertNotNull(tip.getAdmin());
        assertNotNull(tip.getSubType());
    }

    @Test
    public void testBuilderValuesSet() throws Exception {
        Resource tip = new Resource.Builder("Use JSTOR")
                .description("When writing essays, always start by writing out your ideas and then find sources on JSTOR to cite. Not the other way around!")
                .admin("Armand Gray")
                .subType(ACADEMIC.toString())
                .isPublic(true).build();
        assertEquals(tip.getTitle(), "Use JSTOR");
        assertEquals(tip.getDescription(), "When writing essays, always start by writing out your ideas and then find sources on JSTOR to cite. Not the other way around!");
        assertEquals(tip.getAdmin(), "Armand Gray");
        assertEquals(tip.getSubType(), ACADEMIC.toString());
        assertEquals(tip.isPublic(), true);
    }

    @Test
    public void testIcon() throws Exception {
        Resource tip = new Resource.Builder("Use JSTOR")
                .description("When writing essays, always start by writing out your ideas and then find sources on JSTOR to cite. Not the other way around!")
                .admin("Armand Gray")
                .subType(ACADEMIC.toString())
                .isPublic(true).build();
        int academicsDrawable = R.drawable.ic_book_open_variant_white_48dp;
        assertEquals(tip.getIconId(), academicsDrawable);
    }
}