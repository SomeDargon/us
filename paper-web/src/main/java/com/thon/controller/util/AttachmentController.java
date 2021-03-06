/**
 *
 */
package com.thon.controller.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thon.commons.config.Global;
import com.thon.commons.mapper.JsonMapper;
import com.thon.commons.persistence.Page;
import com.thon.commons.utils.ImageUtil;
import com.thon.commons.utils.StringUtils;
import com.thon.entity.system.Attachment;
import com.thon.service.system.AttachmentService;
import com.thon.service.system.SystemService;
import com.thon.service.utils.UserUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @file AttachmentController.java
 * @author 57
 */
@Controller
@RequestMapping("/commons/attachment")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AttachmentController{
	private static final Log log = LogFactory.getLog(AttachmentController.class);

	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private SystemService systemService;


	@RequestMapping(value = "/ueditor")
	public ResponseEntity<?> doUploadConfig(@RequestParam(value = "action",required = true) String action,
											@RequestParam(value = "uid") int userId,
											HttpServletRequest request) throws URISyntaxException, IOException {

		if (action.equals("config")){
			JsonMapper mapper = JsonMapper.nonEmptyMapper();
			Map<String,Object> config = mapper.getMapper().
					readValue(new File(this.getClass().getResource("/ueditor_config.json").toURI()), Map.class);
			return new ResponseEntity(mapper.toJson(config), HttpStatus.OK);

		}else if (action.equals("uploadimage")) {
			MultipartHttpServletRequest fileRequest = (MultipartHttpServletRequest)request;
			MultipartFile file = fileRequest.getFile("upfile");

			Attachment attachment = new Attachment();
			attachment = AttachmentUtil.parse(file, userId);
			attachmentService.saveAttachment(attachment);
			AttachmentUtil.write(attachment);

			Map<String, Object> map = Maps.newHashMap();
			map.put("url", Global.getConfig("project.img.url")+attachment.getId()+attachment.getSuffix());
			map.put("type", attachment.getSuffix());
			map.put("original", attachment.getFileName());
			map.put("size", attachment.getFileSize());
			map.put("title", attachment.getFileName());
			map.put("state", "SUCCESS");
			JsonMapper jsonMapper = new JsonMapper();
			return new ResponseEntity(jsonMapper.toJson(map), HttpStatus.OK);

		} else if (action.equals("listimage")) {
			int start = StringUtils.toInteger(request.getParameter("start"));
			int pageNo = start+1;
			int pageSize = StringUtils.toInteger(request.getParameter("size"));
			Page<Attachment> page = attachmentService.findPage(pageNo,pageSize,userId);
			List<Map<String, Object>> metas = Lists.newArrayList();
			for (Attachment attachment : page.getResult()) {
				Map<String, Object> meta = Maps.newHashMap();
				meta.put("url", Global.getConfig("project.img.url")+attachment.getId()+attachment.getSuffix());
				metas.add(meta);
			}

			Map<String, Object> map = Maps.newHashMap();
			map.put("list",metas);
			map.put("start",start);
			map.put("total",page.getTotalCount());
			map.put("state", "SUCCESS");
			JsonMapper jsonMapper = new JsonMapper();
			return new ResponseEntity(jsonMapper.toJson(map), HttpStatus.OK);
		} else {
			return new ResponseEntity("error", HttpStatus.OK);
		}
	}


	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<?> doUploadAction(@RequestParam(value = "uid", required = true) int userId,
											MultipartHttpServletRequest request){
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf;
		List<Attachment> attachments = new LinkedList<Attachment>();
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			Attachment attachment = AttachmentUtil.parse(mpf, userId);
			attachmentService.saveAttachment(attachment);
			AttachmentUtil.write(attachment);

			attachment.setBytes(null);
			attachments.add(attachment);
		}
//		Map<String, Object> mapdata = Maps.newHashMap();
//		mapdata.put("id", attachments.get(0).getId());
		return new ResponseEntity(attachments, HttpStatus.CREATED);
	}

	//论文上传
	@RequestMapping(value = "/file_upload", method = RequestMethod.POST)
	public ResponseEntity<?> doFileUploadAction(@RequestParam(value = "uid", required = true) int uid,
									 @RequestParam(value = "description", required = false) String description,
											MultipartHttpServletRequest request){
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf;
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			Attachment attachment = AttachmentUtil.parse(mpf, uid);
			attachmentService.saveAttachment(attachment);
			AttachmentUtil.write(attachment);
			attachment.setBytes(null);
		}
		return new ResponseEntity("true", HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/crop", method=RequestMethod.POST)
	public ResponseEntity<?> cropAction(@RequestParam("id")int id, @RequestParam("x")double x, @RequestParam("y")double y,
										@RequestParam("width")double width, @RequestParam("height")double height){

		Attachment attachment = attachmentService.getAttachment(id);
		if (attachment == null) {
			attachment = new Attachment();
		}

		String src = AttachmentUtil.getTargetFilePath(attachment);
		ImageUtil.abscut(src, src, (int)x, (int)y, (int)width, (int)height, 1);
		return new ResponseEntity(true, HttpStatus.OK);
	}

	@RequestMapping({"/download/{targetId}/{dimension}"})
	public void doDownloadAction(@PathVariable int targetId,
								 @PathVariable String dimension, HttpServletResponse response) throws IOException {
		download(targetId, dimension, response);
	}

	@RequestMapping({"/download/{targetId}"})
	public void doDownloadAction1(@PathVariable int targetId,
								  HttpServletResponse response) throws IOException {
		download(targetId, null, response);
	}

	@RequestMapping({"/download"})
	public void doDownloadAction2(@RequestParam int id,
								  HttpServletResponse response) throws IOException {
		download(id, null, response);
	}

	public void download(int targetId, String dimension,HttpServletResponse response){
		Attachment attachment = attachmentService.getAttachment(targetId);
		if (attachment == null) {
			attachment = new Attachment();
		}
		response.setCharacterEncoding("UTF-8");
		attachment.setDimension(dimension);
		AttachmentUtil.read(attachment);
		byte[] bytes = attachment.getBytes();

		response.setContentLength(bytes.length);
		response.setContentType(attachment.getContentType());
		if(attachment.getContentType()!= null
				&& !attachment.getContentType().matches("image/.*")
				&& !attachment.getContentType().matches("audio/.*")) {
			response.setHeader("Content-Disposition","attachment; filename=\"" + attachment.getFileName() +"\"");
		}

		// 添加Last-Modified和Cache-Control响应头信息
		SimpleDateFormat formatDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		formatDate.setTimeZone(new SimpleTimeZone(0, "GMT"));
		response.addHeader("Last-Modified",formatDate.format(attachment.getCreateTime()));
		response.addHeader("Cache-Control", "max-age=31536000");//最长缓存一年

		try {
			response.getOutputStream().write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
