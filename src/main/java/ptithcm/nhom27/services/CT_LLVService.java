package ptithcm.nhom27.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.CT_LLVDao;
import ptithcm.nhom27.entity.CT_LLVEntity;
import ptithcm.nhom27.entity.LichLamViecEntity;
import ptithcm.nhom27.entity.NhanVienEntity;

@Service
@Transactional
public class CT_LLVService {
	
	@Autowired
	CT_LLVDao dao;
	
	public boolean addoreditLLV(LichLamViecEntity llv, NhanVienEntity nv) {
		return dao.addoreditCTLLV(llv, nv);
	}
	
//	public void addoreditCTLLVallNV(LichLamViecEntity llv) {
//		dao.addforeditCTLLVallNV(llv);
//	}
	
	public List<CT_LLVEntity> lichLamViecCuaNVNow(String manv){
		return dao.lichLamViecCuaNVNow(manv);
	}
	
	public List<CT_LLVEntity> getCTLLVofDate(Date date) {
		return dao.getCTLLVofDate(date);
	}
	
	public void deleteCTLLV(LichLamViecEntity llv) {
		dao.deleteCTLLV(llv);
	}
}
