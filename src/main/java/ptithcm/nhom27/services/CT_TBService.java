package ptithcm.nhom27.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.CT_TBDao;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.ThongBaoEntity;

@Service
public class CT_TBService {
	
	@Autowired
	CT_TBDao dao;
	
	public boolean addOneNV(NhanVienEntity nv, ThongBaoEntity tb) {
		return dao.addOneNV(nv, tb);
	}
	
	public void autoAddAllNV(List<NhanVienEntity> lnv, ThongBaoEntity tb) {
		dao.autoAddAllNV(lnv, tb);
	}
	
	public List<ThongBaoEntity> getAllTBMaNV(String manv) {
		return dao.getAllTBMaNV(manv);
	}
}
