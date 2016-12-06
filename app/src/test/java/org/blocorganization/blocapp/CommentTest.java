package org.blocorganization.blocapp;

import org.blocorganization.blocapp.models.Comment;
import org.blocorganization.blocapp.models.RecordType;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class CommentTest {

    private static final String ARMAND_GRAY = "Armand Gray";
    private static final String NICE_POST = "Nice Post!";

    @Test
    public void testCommentConstructor() throws Exception {
        Comment comment = new Comment(ARMAND_GRAY, NICE_POST);
        assertNotNull(comment);
    }

    @Test
    public void testCommentNotNull() throws Exception {
        Comment comment = new Comment(ARMAND_GRAY, NICE_POST);
        assertNotNull(comment.getSubtype());
        assertNotNull(comment.getUser());
        assertNotNull(comment.getText());

    }

    @Test
    public void testCommentValues() throws Exception {
        Comment comment = new Comment(ARMAND_GRAY, NICE_POST);
        assertEquals(comment.getSubtype(), RecordType.COMMENT.toString());
        assertEquals(comment.getUser(), ARMAND_GRAY);
        assertEquals(comment.getText(), NICE_POST);

    }
}
