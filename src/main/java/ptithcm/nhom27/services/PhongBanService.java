package ptithcm.nhom27.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.PhongBanDao;
import ptithcm.nhom27.entity.PhongBanEntity;

@Service
public class PhongBanService {
	@Autowired
	private PhongBanDao dao;
	
	public List<PhongBanEntity> getListOffice() {
		return dao.getListOffice();
	}
	
	public PhongBanEntity getPhongBan(String mapb) {
		return dao.getPhongban(mapb);
	}
	public long slNVconlam(String mapb) {
		return dao.slNVconlam(mapb);
	}
}
