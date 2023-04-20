package ptithcm.nhom27.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.CTLuongDao;
import ptithcm.nhom27.entity.CTLuongEntity;

@Service
@Transactional
public class CTLuongService {

	@Autowired
	private CTLuongDao dao;
	
	
	public List<CTLuongEntity> getAllCTluongPB(String mapb){
		return dao.getAllCTluongPB(mapb);
	}
	
	public List<CTLuongEntity> getAllCTluongMANV(String manv) {
		return dao.getAllCTluongMANV(manv);
	}
}	
