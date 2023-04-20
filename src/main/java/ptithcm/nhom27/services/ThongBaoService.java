package ptithcm.nhom27.services;


import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.ThongBaoDao;
import ptithcm.nhom27.entity.ThongBaoEntity;

@Service
@Transactional
public class ThongBaoService {
	
	@Autowired
	ThongBaoDao dao;
	
	public boolean addThongBao(ThongBaoEntity tb) {
		return dao.addThongBao(tb);
	}
	
	public String createNewMaTb() {
		return dao.createNewMaTb();
	}
	public ThongBaoEntity autoCreateTBXP(String tenqd, Date date, int sotien) {
		return dao.autoCreateTBXP(tenqd, date, sotien);
	}
}
