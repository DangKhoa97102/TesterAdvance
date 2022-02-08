package com.poly.service.junit;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.poly.entity.Video;
import com.poly.service.VideoService;
import com.poly.service.impl.VideoServiceImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VideoServiceTest {
	private VideoService videoService;

	@Before
	public void setUp() throws Exception {
		videoService = new VideoServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		videoService = null;
	}

	/* Kiểm tra xuất danh sách video */
	@Test
	public final void testVideoList1() {
		List<Video> list = videoService.findAll();
		int result = 5;
		int actual = list.size();
		
		assertEquals(result, actual);
	}
	
	@Test
	public final void testVideoList2() {
		int pageNumber = 1; 
		int pageSize = 3;
		
		List<Video> list = videoService.findAll(pageNumber, pageSize);
		int result = 3;
		int actual = list.size();
		
		assertEquals(result, actual);
	}
	
	/* Kiểm tra tìm video theo tiêu đề */
	@Test
	public final void testVideoSearch1() {
		String titleVideo = "Quan Trường - Trường Quan";
		int pageNumber = 1; 
		int pageSize = 3;
		
		List<Video> list = videoService.findByTitle(titleVideo, pageNumber, pageSize);
		int result = 1;
		int actual = list.size();
		
		assertEquals(result, actual);
	}
	
	@Test
	public final void testVideoSearch2() {
		String titleVideo = "123456789";
		int pageNumber = 1; 
		int pageSize = 3;
		
		List<Video> list = videoService.findByTitle(titleVideo, pageNumber, pageSize);
		int result = 0;
		int actual = list.size();
		
		assertEquals(result, actual);
	}

	/* Kiểm tra thêm video */
	@Test
	public final void testVideoAdd1() {
		Video video = new Video();
		video.setId("tr8hm-xS33M");
		video.setTitle("Các kỹ thuật thiết kế Test Case");
		video.setDescription("Các kỹ thuật thiết kế Test Case");
		video.setPoster("https://img.youtube.com/vi/tr8hm-xS33M/maxresdefault.jpg");
		
		Video result = videoService.create(video);
		
		assertNotNull(result);
	}
	
	@Test(expected = Exception.class)
	public final void testVideoAdd2() {
		Video video = new Video();
		video.setId("Hi9eQnS7snc");
		video.setTitle("Các kỹ thuật thiết kế Test Case");
		video.setDescription("Các kỹ thuật thiết kế Test Case");
		video.setPoster("https://img.youtube.com/vi/tr8hm-xS33M/maxresdefault.jpg");
		
		videoService.create(video);
	}
	
	@Test(expected = Exception.class)
	public final void testVideoAdd3() {
		Video video = null;
		videoService.create(video);
	}
	
	@Test(expected = Exception.class)
	public final void testVideoAdd4() {
		Video video = new Video();
		videoService.create(video);
	}
	
	/* Test xoá Video */
	@Test
	public final void testVideoDelete1() {
		String id = "tr8hm-xS33M";
		Video result = videoService.delete(id);
		
		assertNotNull(result);
	}
	
	@Test(expected = Exception.class)
	public final void testVideoDelete2() {
		String id = "13245678";
		videoService.delete(id);
	}
	
	/* Test cập nhật user */
	@Test
	public final void testVideoEdit1() {
		Video video = new Video();
		video.setId("tr8hm-xS33M");
		video.setTitle("Thiết kế Test Case");
		video.setDescription("Các kỹ thuật thiết kế Test Case");
		video.setPoster("https://img.youtube.com/vi/tr8hm-xS33M/maxresdefault.jpg");
		video.setViews(200);
		video.setUploadDate(new Timestamp(System.currentTimeMillis()));
		Video result = videoService.update(video);
		assertNotNull(result);
	}
	
	@Test(expected = Exception.class)
	public final void testVideoEdit2() {
		Video video = null;
		videoService.update(video);
	}
	
	@Test(expected = Exception.class)
	public final void testVideoEdit3() {
		Video video = new Video();
		videoService.update(video);
	}

}
