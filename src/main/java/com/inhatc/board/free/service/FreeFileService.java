package com.inhatc.board.free.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.inhatc.board.free.domain.FreeFileVO;

public interface FreeFileService {
	public List<Integer> registration(List<MultipartFile> multipartFile, int fb_no);
	public List<FreeFileVO> getFileList(int fb_no) throws Exception;
}
