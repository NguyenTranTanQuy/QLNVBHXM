package ptithcm.nhom27.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.MucDoDao;
import ptithcm.nhom27.entity.MucdoEntity;

@Service
public class MucDoService {
	
	@Autowired
	private MucDoDao dao;
	
	public List<MucdoEntity> getAllmucdo(){
		return dao.getAllMucdo();
	}
	
	public MucdoEntity getMucdobyid(int id) {
		return dao.getMucdobyid(id);
	}
}
