package Coupons.Controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.net.HttpHeaders;

import Coupons.services.FileStorageService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@CrossOrigin
@EnableSwagger2
@Transactional
public class FileController {
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam MultipartFile file) {
		String fileName = this.fileStorageService.storeFile(file);
		return fileName;
	}
	
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
		Resource re = this.fileStorageService.loadFileAsResource(fileName);
		
		//Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(re.getFile().getAbsolutePath());
		}catch (IOException e) {
			System.out.println("Could not determine file type");
		}
		
		//Fallback to the default content type if type could not be determined
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		
		System.out.println("content type: " + contentType);
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + re.getFilename() + "\"")
				.body(re);
	}

}
