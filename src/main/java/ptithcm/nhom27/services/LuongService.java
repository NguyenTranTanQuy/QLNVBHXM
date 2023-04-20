package ptithcm.nhom27.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.LuongDao;
import ptithcm.nhom27.entity.LuongEntity;

@Service
@Transactional
public class LuongService {
	
	@Autowired
	LuongDao dao;
	
	public List<LuongEntity> getAllBacluong() {
		return dao.getAllBacluong();
	}
	
	public LuongEntity getBacLuong(int bacluong) {
		return dao.getbacluong(bacluong);
	}
}
