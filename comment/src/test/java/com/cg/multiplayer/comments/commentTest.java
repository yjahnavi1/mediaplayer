package com.cg.multiplayer.comments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.mediaplayer.comments.dao.CommentsDao;
import com.cg.mediaplayer.comments.entity.Comments;
import com.cg.mediaplayer.comments.exception.DuplicateRecordException;
import com.cg.mediaplayer.comments.exception.RecordNotFoundException;
import com.cg.mediaplayer.comments.service.CommentsServiceImpl;

public class commentTest {

    @Mock
    private CommentsDao commentsDaoMock;

    @InjectMocks
    private CommentsServiceImpl commentsService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddComment() throws RecordNotFoundException {
        Comments comment = new Comments();
        comment.setId(1);
        comment.setCommenterName("Test Comment");
        List<Comments> commentsList = new ArrayList<>();
        commentsList.add(comment);
        when(commentsDaoMock.save(comment)).thenReturn(comment);
        when(commentsDaoMock.findAll()).thenReturn(commentsList);
        List<Comments> returnedCommentsList = commentsService.addComment(comment);
        assertEquals(commentsList, returnedCommentsList);
        verify(commentsDaoMock, times(1)).save(comment);
        verify(commentsDaoMock, times(1)).findAll();
    }

   // @Test
    public void testDeleteComment() throws RecordNotFoundException {
        Integer commentId = 1;
        List<Comments> commentsList = new ArrayList<>();
        Comments comment = new Comments();
        comment.setId(commentId);
        commentsList.add(comment);
        when(commentsDaoMock.findById(commentId)).thenReturn(Optional.of(comment));
        commentsService.deleteComment(1);
        verify(commentsDaoMock, times(1)).deleteById(commentId);
    }

   @Test(expected = RecordNotFoundException.class)
    public void testDeleteCommentThrowsRecordNotFoundException() throws RecordNotFoundException {
        Integer commentId = 1;
       // when(commentsDaoMock.findById(commentId)).thenReturn(Optional.empty());
        commentsService.deleteComment(commentId);
    }

  @Test
    public void testGetAllComments() throws RecordNotFoundException {
        List<Comments> commentsList = new ArrayList<>();
        Comments comment1 = new Comments();
        comment1.setId(1);
        comment1.setCommenterName("Test Comment 1");
        Comments comment2 = new Comments();
        comment2.setId(2);
        comment2.setCommenterName("Test Comment 2");
        commentsList.add(comment1);
        commentsList.add(comment2);
        when(commentsDaoMock.findAll()).thenReturn(commentsList);
        List<Comments> returnedCommentsList = commentsService.getAllComments();
        assertEquals(commentsList, returnedCommentsList);
        verify(commentsDaoMock, times(1)).findAll();
    }

   @Test(expected = RecordNotFoundException.class)
    public void testGetAllCommentsThrowsRecordNotFoundException() throws RecordNotFoundException {
        when(commentsDaoMock.findAll()).thenReturn(new ArrayList<Comments>());
        commentsService.getAllComments();
    }

    //@Test
    public void testIncreseLikeCount() throws RecordNotFoundException {
        Integer commentId = 1;
        Comments comment = new Comments();
        comment.setId(commentId);
        comment.setLikeCount(0);
        when(commentsDaoMock.findById(commentId)).thenReturn(Optional.of(comment));
        commentsService.increseLikeCount(commentId);
        verify(commentsDaoMock, times(1)).findById(commentId);
        verify(commentsDaoMock, times(1)).save(comment);
    }
 //  @Test
	public void testUpdateComment() throws RecordNotFoundException {
		Comments comment = new Comments();
		comment.setId(1);
		comment.setCommenterName("testuser");
		comment.setText("test comment");

		when(commentsDaoMock.save(comment)).thenReturn(comment);

		List<Comments> commentsList = new ArrayList<>();
		commentsList.add(comment);
		when(commentsDaoMock.findById(comment.getCommentId())).thenReturn(Optional.of(comment));

		List<Comments> updatedCommentsList = commentsService.update(comment);

		assertEquals(commentsList.size(), 1);
		assertEquals(commentsList.get(0).getCommentId(), comment.getCommentId());
		assertEquals(commentsList.get(0).getText(), comment.getText());
	}
   
	
//	//@Test
//	@Order(4)
//	void testdeleteCommentRecord() throws RecordNotFoundException {
//	//Comments.info("Testing Delete LoginRecord");
//
//	commentsList.remove(Comments);
//	// When record is deleted then return list without that Login
//	doNothing().when(commentsDaoMock).deleteById(1);
//	when(commentsDaoMock.findAll()).thenReturn(commentsList);
//
//	List<Comments> commentsList=service.deleteById(1);
//	// service will call CommentsDao delete 
//	assertTrue(commentsList.size()==0);
//	assertEquals(0,commentsList.size());
//	//assertThrows(IndexOutOfBoundsException.class, ()->l1.get(0));
//
//	// verify that CommentsDao.delete method got executed at least once
//	verify(commentsDaoMock,times(1)).deleteById(1);
//	verify(commentsDaoMock,times(1)).findAll();
//	}

	
	
}











