package com.inhatc.board.free.persistence;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.inhatc.board.free.domain.FreeFileVO;

@Repository
public class FreeFileDAOImpl implements FreeFileDAO {
	
	private static String SAVE_PATH = "/C:\\3-1\\spring\\code\\src\\main\\webapp\\resources\\upload_file";
	
	@Autowired
	private SqlSession sql;
	
	private static String namespace = "com.inhatc.mapper.freeFileMapper";
	
	@Override
	public List<Integer> restore(List<MultipartFile> multipartFile, int fb_no) {
		try {
			List<Integer> result = new ArrayList<Integer>();
			for(int i = 0 ; i < multipartFile.size(); i++) {
				MultipartFile file = multipartFile.get(i);
				
				// 파일 정보
				String originFilename = file.getOriginalFilename();
			    String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
			    Long size = file.getSize();
			    
			 // 서버에서 저장 할 파일 이름
			    String saveFileName = genSaveFileName(extName);
			    
			    writeFile(file, saveFileName);
			    
				/*
				 * System.out.println("originFilename : " + originFilename);
				 * System.out.println("extensionName : " + extName);
				 * System.out.println("size : " + size); System.out.println("saveFileName : " +
				 * saveFileName);
				 */
			    
			    HashMap<String, Object> paramMap = new HashMap<String, Object>();
			    paramMap.put("ff_file_name", saveFileName);
			    paramMap.put("ff_origin_file_name", originFilename);
			    paramMap.put("fb_no", fb_no);
			    result.add(sql.insert(namespace + ".registration", paramMap));
			}
		      
		      return result;
		    }
		    catch (IOException e) {
		      // 원래라면 RuntimeException 을 상속받은 예외가 처리되어야 하지만
		      // 편의상 RuntimeException을 던진다.
		      // throw new FileUploadException();
		      throw new RuntimeException(e);
		    }
	};
	
	// 현재 시간을 기준으로 파일 이름 생성
	@Override
	public String genSaveFileName(String extName) {
		  String fileName = "";
		     
		  Calendar calendar = Calendar.getInstance();
		  fileName += calendar.get(Calendar.YEAR);
		  fileName += calendar.get(Calendar.MONTH);
		  fileName += calendar.get(Calendar.DATE);
		  fileName += calendar.get(Calendar.HOUR);
		  fileName += calendar.get(Calendar.MINUTE);
		  fileName += calendar.get(Calendar.SECOND);
		  fileName += calendar.get(Calendar.MILLISECOND);
		  fileName += extName;
		     
		  return fileName;
	}
	   
	   
	@Override
	// 파일을 실제로 write 하는 메서드
	public boolean writeFile(MultipartFile multipartFile, String saveFileName) throws IOException{
	    boolean result = false;
	 
	    byte[] data = multipartFile.getBytes();
	    FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
	    fos.write(data);
	    fos.close();
	     
	    return result;
	};
	
	@Override
	public List<FreeFileVO> getFileList(int fb_no) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace+".getFileList",fb_no);
	}
}
