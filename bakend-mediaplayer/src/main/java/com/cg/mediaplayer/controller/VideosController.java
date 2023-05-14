package com.cg.mediaplayer.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cg.mediaplayer.entity.FileEntity;
import com.cg.mediaplayer.entity.FileResponse;

import com.cg.mediaplayer.entity.Videos;
import com.cg.mediaplayer.exception.RecordNotFoundException;
import com.cg.mediaplayer.service.FileServiceImpl;
import com.cg.mediaplayer.service.VideosService;

@RestController
@RequestMapping("/videos")
@CrossOrigin(origins = "http://localhost:4200")
public class VideosController {
	
	Logger logger = LoggerFactory.getLogger(VideosController.class);

	@Autowired
	private VideosService videosService;


	private final FileServiceImpl fileService;

	@Autowired
	public VideosController(FileServiceImpl fileService) {
		this.fileService = fileService;
	}

	// http://localhost:8084/videos/updateVideoDetails
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/updateVideoDetails")
	public ResponseEntity<List<Videos>> updateVideo(@RequestBody Videos bean) throws RecordNotFoundException {
		List<Videos> videos = videosService.editVideos(bean);
		if (videos.isEmpty()) {
			// logger.error("Videos are not available");
			return new ResponseEntity("Sorry! Videos not available!", HttpStatus.NOT_FOUND);
		}
		// logger.info("Video is successfully updated");
		return new ResponseEntity<List<Videos>>(videos, HttpStatus.OK);
	}

	// http://localhost:8084/videos/insertVideoDetails
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/insertVideoDetails")
	// @RequestMapping(value ="/videos", consumes =
	// MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json",method =
	// RequestMethod.POST)
	public ResponseEntity<List<Videos>> insertVideo(@RequestBody Videos bean) throws RecordNotFoundException { // @RequetBody
																											// Videos
																												// object
		List<Videos> videos = videosService.addVideos(bean);
		if (videos.isEmpty()) {
			return new ResponseEntity("Sorry! Videos not available!", HttpStatus.NOT_FOUND);
		}
		// logger.info("VideoRecord is successfully added");
		return new ResponseEntity<List<Videos>>(videos, HttpStatus.OK);
	}

	// http://localhost:8084/videos/deleteVideoDetailsById/1008
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/deleteVideoDetailsById/{videoId}")
	public ResponseEntity<List<Videos>> deleteVideo(@PathVariable("videoId") Integer videoId)
			throws RecordNotFoundException {
		List<Videos> videos = videosService.deleteVideos(videoId);
		if (videos.isEmpty() || videos == null) {
			return new ResponseEntity("Sorry! videoId not available!", HttpStatus.NOT_FOUND);
		}
		// logger.info("VideoRecord is successfully deleted");
		return new ResponseEntity<List<Videos>>(videos, HttpStatus.OK);
	}

	// http://localhost:8084/videos/findVideoDetailsById/101
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/findVideoDetailsById/{videoId}")
	public ResponseEntity<Videos> findById(@PathVariable("videoId") Integer videoId) throws RecordNotFoundException {
		Videos video = videosService.getVideoDetails(videoId);
		if (video == null) {
			return new ResponseEntity("Sorry! VideoRecords not found!", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Videos>(video, HttpStatus.OK);
	}

	// http://localhost:8084/videos/viewAllVideosDetails
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/viewAllVideosDetails") // @RequestMapping(value = "/videos",method = RequestMethod.GET)
	public ResponseEntity<List<Videos>> viewAllVideos() throws RecordNotFoundException {
		List<Videos> videos = videosService.viewAllVideos();
		if (videos.isEmpty()) {
			return new ResponseEntity("Sorry! VideoRecords not available!", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Videos>>(videos, HttpStatus.OK);
	}

	/* =======Video Files related APIs==== */

	//@PostMapping("/uploadVideoFile")
	@PostMapping(value="/uploadVideoFile",consumes = {"multipart/form-data"})
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
		try {
			fileService.save(file);

			return ResponseEntity.status(HttpStatus.OK)
					.body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
		}
	}

	@GetMapping("/getAllVideoFiles")
	public List<FileResponse> list() {
		return fileService.getAllFiles().stream().map(this::mapToFileResponse).collect(Collectors.toList());
	}

	@GetMapping("/getFileURL")
	public String fileResponse(FileEntity fileEntity) {
		String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
				.path(fileEntity.getId()).toUriString();
		logger.info("downloadURL:  "+downloadURL);
		return downloadURL ;
		
	}

	private FileResponse mapToFileResponse(FileEntity fileEntity) {
		String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
				.path(fileEntity.getId()).toUriString();
		FileResponse fileResponse = new FileResponse();
		fileResponse.setId(fileEntity.getId());
		fileResponse.setName(fileEntity.getName());
		fileResponse.setContentType(fileEntity.getContentType());
		fileResponse.setSize(fileEntity.getSize());
		fileResponse.setUrl(downloadURL);
		
		logger.info("downloadURL:  "+downloadURL);

		return fileResponse;
	}

	@GetMapping("/getVideoFileById/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable String id) {
		Optional<FileEntity> fileEntityOptional = fileService.getFile(id);

		if (!fileEntityOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		FileEntity fileEntity = fileEntityOptional.get();
		logger.info("file entity byte data: "+fileEntity.getData().toString());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
				.contentType(MediaType.valueOf(fileEntity.getContentType())).body(fileEntity.getData());
	}

	@DeleteMapping("/deleteVideoFileById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable String id) {
		try {
			fileService.delete(id);

			return ResponseEntity.status(HttpStatus.OK).body(String.format("File deleted successfully: "));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(String.format("Could not delete the file: !"));
		}
	}

	@GetMapping("/videos/{filename:.+}")
	public ResponseEntity<FileSystemResource> serveVideo(@PathVariable String filename) {
		FileSystemResource video = new FileSystemResource("videos/" + filename);
		return ResponseEntity.ok()
				.contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM))
				.body(video);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(RecordNotFoundException.class) // local to the RestController
	public final ResponseEntity<String> handleRecordNotFoundException(Exception ex, WebRequest request) {
		// logger.error(ex.getMessage());
		return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
