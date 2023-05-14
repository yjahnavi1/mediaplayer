package com.cg.mediaplayer.videos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.cg.mediaplayer.videos.dao.TagsDao;
import com.cg.mediaplayer.videos.dao.VideosDao;
import com.cg.mediaplayer.videos.entity.Tags;
import com.cg.mediaplayer.videos.entity.Videos;
import com.cg.mediaplayer.videos.exception.RecordNotFoundException;
import com.cg.mediaplayer.videos.service.ITagsService;
import com.cg.mediaplayer.videos.service.TagsServiceImpl;
import com.cg.mediaplayer.videos.service.VideosService;
import com.cg.mediaplayer.videos.service.VideosServiceImpl;

import nonapi.io.github.classgraph.utils.Assert;

@SpringBootTest
@ContextConfiguration(classes = VideoModuleApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 
public class TagTest {
    private TagsDao tagsDao;
    private ITagsService tagsService;

    @Mock
    private TagsDao TagsDao;
    
    @InjectMocks
    ITagsService service = new TagsServiceImpl();
    
    List<Tags> tagslist;
    Tags a;    
    
    private ITagsService ITagsServicee;
    @BeforeEach
    public void beforeTest() {
    tagslist=new ArrayList<>();
    a=new Tags(1,"java");
    tagslist.add(a);
    }
    
    @Test
    @Order(1)
    
    public void testAddTag() {
 
    	when(tagsDao.save(a)).thenReturn(a);
    	when(tagsDao.findAll()).thenReturn(tagslist);
    	List<Tags> u1=service.addTag(a);

    	assertTrue(u1.size()>0);
    	assertEquals(1,u1.size());
    	
    	verify(tagsDao,times(1)).save(a);
    	verify(tagsDao,times(1)).findAll();
}
    

    
}