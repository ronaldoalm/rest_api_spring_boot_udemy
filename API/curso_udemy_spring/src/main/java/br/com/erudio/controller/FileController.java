package br.com.erudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.erudio.data.vo.v1.UploadFileResponse;
import br.com.erudio.services.FileStorageService;
import io.swagger.annotations.Api;

@Api(tags = "FileEndPoint")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {

	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file ) {
		
		String fileName = fileStorageService.storageFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/v1/dowloadFile/").path(fileName).toUriString();
		var fileType = file.getContentType();
		var size = file.getSize();
		
		return new UploadFileResponse(fileName, fileDownloadUri, fileType, size);
	}
}
