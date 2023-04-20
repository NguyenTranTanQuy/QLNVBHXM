package ptithcm.nhom27.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.NhanVienDao;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.PhongBanEntity;


@Service
@Transactional
public class NhanVienService {

	@Autowired
	private NhanVienDao dao;
	
	public List<NhanVienEntity> getAllNhanVienOfPb(){
		return dao.getAllNhanVienOfPb();
	}
	
	public NhanVienEntity getNhanVien(String manv) {
		return dao.getNhanVien(manv);
	}
	
	public String Createmanv() {
		return dao.Createmnv();
	}
	
	public String getNameUser() {
		return dao.getNameUser();
	}
	
	public String getPhongBanUser() {
		return dao.getPhongBanUser();
	}
	
	public boolean addNhanVien(NhanVienEntity nv) {
		return dao.addNhanVien(nv);
	}
	
	public PhongBanEntity getPhongBan() {
		return dao.getPhongBan();
	}
	
	public boolean deleteNhanVien(NhanVienEntity nv) {
		return dao.deleteNhanVien(nv);
	}
	
	public NhanVienEntity getNVLogin() {
		return dao.getNVLogin();
	}
	
	public List<NhanVienEntity> getAllQuanLy() {
		return dao.getAllQuanLy();
	}
}
