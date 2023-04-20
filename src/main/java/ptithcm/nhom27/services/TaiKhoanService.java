package ptithcm.nhom27.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.TaiKhoanDao;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.TaiKhoanEntity;

@Service
@Transactional
public class TaiKhoanService {

	@Autowired
	TaiKhoanDao dao;

	public boolean autoAddTaiKhoan(NhanVienEntity nv) {
		return dao.autoaddTaiKhoan(nv);
	}

	public boolean resetPassword(TaiKhoanEntity tk) {
		return dao.resetPassword(tk);
	}

	public boolean changeTaiKhoan(TaiKhoanEntity tk) {
		return dao.changeTaiKhoan(tk);
	}

	public boolean changeStatus(TaiKhoanEntity tk, boolean status) {
		return dao.changeStatus(tk, status);
	}

	public List<TaiKhoanEntity> getAllTaiKhoan() {
		return dao.getAllTaiKhoan();
	}

	public TaiKhoanEntity getTaiKhoan(String manv) {
		return dao.getTaiKhoan(manv);
	}

	public boolean checkPassword(String rawPassword, String encodePassword) {
		return dao.checkPassword(rawPassword, encodePassword);
	}
	public List<TaiKhoanEntity> getTaiKhoanPB(){
		return dao.getAllTaiKhoanpb();
	}
}
