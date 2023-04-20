package ptithcm.nhom27.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.HDLDDao;
import ptithcm.nhom27.entity.HDLDEntity;

@Service
@Transactional
public class HDLDService {
	
	@Autowired
	private HDLDDao dao;
	
	public boolean addHDLD(HDLDEntity hdld) {
		return dao.addHDLD(hdld);
	}
	
	public String createHDLD() {
		return dao.createMaHDLD();
	}
	
	public boolean deleteHDLD(HDLDEntity hdld) {
		return dao.deleteHDLD(hdld);
	}
	
	public HDLDEntity getHDLD(String manv) {
		return dao.getHDLD(manv);
	}
	
}
