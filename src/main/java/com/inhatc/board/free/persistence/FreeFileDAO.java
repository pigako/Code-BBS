package com.inhatc.board.free.persistence;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.inhatc.board.free.domain.FreeFileVO;

public interface FreeFileDAO {
	public List<Integer> restore(List<MultipartFile> multipartFile, int fb_no);
	public String genSaveFileName(String extName);
	public boolean writeFile(MultipartFile multipartFile, String saveFileName) throws IOException;
	public List<FreeFileVO> getFileList(int fb_no) throws Exception;
}
