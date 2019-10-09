package com.inhatc.board.free.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inhatc.board.free.domain.FreeFileVO;
import com.inhatc.board.free.persistence.FreeFileDAO;

@Service
public class FreeFileServiceImpl implements FreeFileService {
	
	@Autowired
	private FreeFileDAO dao;
	
	@Override
	public List<Integer> registration(List<MultipartFile> multipartFile, int fb_no) {
		// TODO Auto-generated method stub
		return dao.restore(multipartFile, fb_no);
	}
	
	@Override
	public List<FreeFileVO> getFileList(int fb_no) throws Exception {
		// TODO Auto-generated method stub
		return dao.getFileList(fb_no);
	}
}
