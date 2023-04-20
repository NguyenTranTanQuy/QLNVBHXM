package ptithcm.nhom27.services;

import java.sql.Time;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.ChamCongDao;
import ptithcm.nhom27.entity.ChamCongEntity;
import ptithcm.nhom27.entity.LichLamViecEntity;
import ptithcm.nhom27.entity.NhanVienEntity;

@Service
@Transactional
public class ChamCongService {

	@Autowired
	ChamCongDao dao;

	public List<ChamCongEntity> getAllChamCong() {
		return dao.getAllChamCong();
	}

	public boolean nvCheckin(Time checkin, LichLamViecEntity llv, NhanVienEntity nv) {
		return dao.nvcheckin(checkin, llv, nv);
	}

	public boolean nvCheckout(Time checkout, String manv) {
		return dao.nvcheckout(checkout, manv);
	}

	public ChamCongEntity getCheckin(String manv) {
		return dao.getCheckin(manv);
	}

	public ChamCongEntity getCCtheoLLV(LichLamViecEntity llv, String manv) {
		return dao.getCCtheoLLV(llv, manv);
	}

	public List<ChamCongEntity> getAllChamCongPB() {
		return dao.getAllChamCongPB();
	}
	
	public void autoCheckChamCong() {
		dao.autoCheckChamCong();
	}
	
	public List<ChamCongEntity> getAllChamCongPersonal() {
		return dao.getAllChamCongPersonal();
	}
	
	public int sCaDiTreVesomThang(NhanVienEntity nv, int thang, int nam) {
		return dao.dsDiTreVesomThang(nv, thang, nam).size();
	}
	
	public int soCaDiTreThang(NhanVienEntity nv, int thang, int nam) {
		return dao.dsDiTreThang(nv, thang, nam).size();
	}
	
	public int soCaVesomThang(NhanVienEntity nv, int thang, int nam) {
		return dao.dsVesomThang(nv, thang, nam).size();
	}
	public double soGioDiTreThang(NhanVienEntity nv, int thang, int nam) {
		return dao.soGioDiTreThang(nv, thang, nam);
	}
	public double soGioVeSomThang(NhanVienEntity nv, int thang, int nam) {
		return dao.soGioVeSomThang(nv, thang, nam);
	}
	public double soGioKhongDiLamThang(NhanVienEntity nv, int thang, int nam) {
		return dao.soGioKhongDiLamThang(nv, thang, nam);
	}
	public double tongSoCanGioLam(NhanVienEntity nv, int thang, int nam) {
		return dao.tongSoCanGioLam(nv, thang, nam);
	}
	public long soNgayLam(NhanVienEntity nv, int thang, int nam) {
		return dao.soNgayLam(nv, thang, nam);
	}
	public double phanTramDiLam(NhanVienEntity nv, int thang, int nam) {
		return dao.phanTramDiLam(nv, thang, nam);
	}
}
