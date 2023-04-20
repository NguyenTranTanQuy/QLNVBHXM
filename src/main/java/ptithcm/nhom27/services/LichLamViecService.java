package ptithcm.nhom27.services;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.LichLamViecDao;
import ptithcm.nhom27.entity.CT_LLVEntity;
import ptithcm.nhom27.entity.LichLamViecEntity;
import ptithcm.nhom27.entity.NhanVienEntity;

@Service
@Transactional
public class LichLamViecService {
	@Autowired
	LichLamViecDao dao;
	
	public List<LichLamViecEntity> getAllLichLamViec(){
		return dao.getAllLichLamViec();
	}
	
	public List<LichLamViecEntity> getLLVofDay(Date date) {
		return dao.getLLVofDate(date);
	}
	
	public boolean addoreditLichLamViec(LichLamViecEntity llv) {
		return dao.addoreditLichLamViec(llv);
	}
	
	public int getMaxID() {
		return dao.getMaxID();
	}
	
	public List<CT_LLVEntity> getCTLLVnow() {
		return dao.getCTLLVnow();
	}
	
	public boolean nvCoTheNhanLichLamViec(LichLamViecEntity llv, NhanVienEntity nv) {
		return dao.nvCoTheNhanLichLamViec(llv, nv);
	}
	
	public List<LichLamViecEntity> getLLVPBofDate(Date date) {
		return dao.getLLVPBofDate(date);
	}
}
