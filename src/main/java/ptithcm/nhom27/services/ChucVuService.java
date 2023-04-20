package ptithcm.nhom27.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.ChucVuDao;
import ptithcm.nhom27.entity.ChucVuEntity;

@Service
@Transactional
public class ChucVuService {
	
	@Autowired
	ChucVuDao dao;
	
	public ChucVuEntity getE() {
		return dao.getE();
	}
}
