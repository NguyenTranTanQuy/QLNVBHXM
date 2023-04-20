package ptithcm.nhom27.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.XuPhatDao;
import ptithcm.nhom27.entity.CT_XuphatEntity;
import ptithcm.nhom27.entity.NhanVienEntity;

@Service
public class CT_XuphatService {
	
	@Autowired
	private XuPhatDao dao;
	
	public boolean addXuphat(CT_XuphatEntity ctxp) {
		return dao.addXuphat(ctxp);
	}
	
	public List<CT_XuphatEntity> getAllXuPhat() {
		return dao.getAllXuPhat();
	}
	
	public List<CT_XuphatEntity> getXuPhatofPB(String mapb) {
		return dao.getXuPhatofPB(mapb);
	}
	
	public List<CT_XuphatEntity> getXuPhatofMANV(NhanVienEntity nv) {
		return dao.getXuPhatofMANV(nv);
	}
	
	public int getTongTienPhatThang(int thang, int nam, NhanVienEntity nv) {
		return dao.getTongTienPhatThang(thang, nam, nv);
	}
}
