package com.cg.mediaplayer.videos;
 
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;	
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
 
import com.cg.mediaplayer.videos.VideoModuleApplication;
import com.cg.mediaplayer.videos.dao.VideosDao;
import com.cg.mediaplayer.videos.entity.Videos;
import com.cg.mediaplayer.videos.exception.DuplicateRecordException;
import com.cg.mediaplayer.videos.exception.RecordNotFoundException;
import com.cg.mediaplayer.videos.service.VideosService;
import com.cg.mediaplayer.videos.service.VideosServiceImpl;
 
@SpringBootTest
@ContextConfiguration(classes = VideoModuleApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 
class VideoModuleApplicationTests {
 
@Mock
private VideosDao videosDao;
 
@InjectMocks
VideosService service = new VideosServiceImpl();
 
List<Videos> videolist;
Videos a;

private VideosService videosService;
@BeforeEach
public void beforeTest() {
videolist=new ArrayList<>();
a=new Videos(1,"Java","programming",6,3,15,"java.com",LocalDate.now());
videolist.add(a);
}
 
@Test

@Order(1)

void testaddVideos() throws RecordNotFoundException {

when(videosDao.save(a)).thenReturn(a);
when(videosDao.findAll()).thenReturn(videolist);
List<Videos> u1=service.addVideos(a);

assertTrue(u1.size()>0);
assertEquals(1,u1.size());
assertEquals("Java",u1.get(0).getTitle());

verify(videosDao,times(1)).save(a);
verify(videosDao,times(1)).findAll();
}

@Test

@Order(2)

void testeditVideos() throws RecordNotFoundException {

when(videosDao.save(a)).thenReturn(a);
when(videosDao.findAll()).thenReturn(videolist);
List<Videos> u1=service.editVideos(a);

assertTrue(u1.size()>0);
assertEquals(1,u1.size());
assertEquals("Java",u1.get(0).getTitle());

verify(videosDao,times(1)).save(a);
verify(videosDao,times(1)).findAll();
}

//@Test
//
//@Order(3)
//
//public List<Videos> deleteVideos(Integer videoId) throws RecordNotFoundException  {
//  
//	VideosDao.deleteVideos(videoId);        
//    return getViewAllVideos();
//}


@Test

@Order(4)

void testViewAllVideos() throws RecordNotFoundException {
	
List<Videos> VideoList = (List<Videos>) Stream.of(
		
new Videos(1,"Java","programming",6,3,15,"java.com",LocalDate.now()),
new Videos(2,"C","program",7,8,10,"greeks.com",LocalDate.now())).collect
(Collectors.toList());

when(videosDao.findAll()).thenReturn(VideoList);
assertEquals(2, service.viewAllVideos().size());
}

@Test

@Order(5)

void testVideoByTitle() throws RecordNotFoundException {

when(videosDao.save(a)).thenReturn(a);
when(videosDao.findAll()).thenReturn(videolist);
List<Videos> u1=service.editVideos(a);

assertTrue(u1.size()>0);
assertEquals(1,u1.size());
assertEquals("Java",u1.get(0).getTitle());

verify(videosDao,times(1)).save(a);
verify(videosDao,times(1)).findAll();
}

@Test

@Order(6)

void testVideoDetails() throws RecordNotFoundException {

when(videosDao.save(a)).thenReturn(a);
when(videosDao.findAll()).thenReturn(videolist);
List<Videos> u1=service.editVideos(a);

assertTrue(u1.size()>0);
assertEquals(1,u1.size());
assertEquals("Java",u1.get(0).getTitle());

verify(videosDao,times(1)).save(a);
verify(videosDao,times(1)).findAll();
}

@Test

@Order(7)

void testlikeVideo() throws RecordNotFoundException {

when(videosDao.save(a)).thenReturn(a);
when(videosDao.findAll()).thenReturn(videolist);


verify(videosDao,times(1)).save(a);
verify(videosDao,times(1)).findAll();
}

@Test

@Order(8)

void testdislikeVideo() throws RecordNotFoundException {

when(videosDao.save(a)).thenReturn(a);
when(videosDao.findAll()).thenReturn(videolist);


verify(videosDao,times(1)).save(a);
verify(videosDao,times(1)).findAll();
}

}



