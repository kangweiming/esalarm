package com.all_union.es.esalarm.common.util;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.stream.FileImageOutputStream;

import org.springframework.web.multipart.MultipartFile;

import com.all_union.es.esalarm.common.WxConstants;
import com.kwm.common.lang.StringUtil;
/**
 * 附件上传
 * 
 */
public class UploadUtil {
	
	public static String doUpload(String realPath ,MultipartFile file){
		//return doUpload(realPath,"/res/upload/",file);
		return doUpload(realPath,WxConstants.UPLOAD_CONTEXT_PATH,file);
	}
	/**
	 * 目前缺失对上传文件 分辨率、存储空间、扩展名的检查
	 * @param realPath 上传文件保存的服务器本地基本路径
	 * @param modulePath 上传文件保存的module路径 如/res/upload
	 * @param file spring MultipartFile 对象
	 * @return 上传后文件的相对访问地址 如 /res/upload/12123123.jpg
	 */
	public static String doUpload(String realPath ,String modulePath ,MultipartFile file){
		String fileName = file.getOriginalFilename();  
		if(!StringUtil.isBlank(fileName)){
			String ext = fileName.substring(fileName.indexOf("."));
			fileName = Calendar.getInstance().getTimeInMillis() + ext;
			
			File targetFile = new File(realPath + modulePath + fileName);  
	        if(!targetFile.exists()){
	            targetFile.mkdirs();
	        }  
	        //保存
	        try {
	            file.transferTo(targetFile);  
	        } catch (Exception e) {
	            e.printStackTrace();  
	        } 
	        //return "/res/upload/" + fileName;
	        return modulePath + fileName;
		}
		return null;
	}
	
	public static String byteToImg(String realPath ,byte[] bytes){
		if(bytes != null && bytes.length > 0){
			//String imagePath = "/res/upload/" + UUID.randomUUID().toString() + ".jpg";
			String imagePath = WxConstants.UPLOAD_CONTEXT_PATH + UUID.randomUUID().toString() + ".jpg";
			FileImageOutputStream imageOutput;
			try {
				File file = new File(realPath + imagePath);
				file.createNewFile();
				imageOutput = new FileImageOutputStream(file);
				imageOutput.write(bytes, 0, bytes.length);  
			 	imageOutput.close(); 
				return imagePath;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} 
		}
		return null;
	}
	
}

