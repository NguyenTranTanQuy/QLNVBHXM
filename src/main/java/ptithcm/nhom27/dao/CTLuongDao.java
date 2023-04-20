package ptithcm.nhom27.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.CTLuongEntity;
import ptithcm.nhom27.entity.CT_LLVEntity;
import ptithcm.nhom27.entity.LuongEntity;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.services.LuongService;
import ptithcm.nhom27.services.NhanVienService;

@Repository(value = "CTLuongDao")
@Transactional
public class CTLuongDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	NhanVienService nvService;
	
	@Autowired
	LuongService luService;
	
	private final int defaultBacluong = 1;
	
	public List<CTLuongEntity> getAllCTLuong() {
		Session session = sessionFactory.openSession();
		String hql = "FROM CTLuongEntity";
		Query query = session.createQuery(hql);
		List<CTLuongEntity> list = query.getResultList();
		session.close();
		return list;
	}
	
	public List<CTLuongEntity> getAllCTLuongE() {
		List<CTLuongEntity> list = getAllCTLuong();
		return list.stream().filter(o->o.getManvluong().getMatk().getChucvu().getMacv().equals("E")).collect(Collectors.toList());
	}
	
	public List<CTLuongEntity> getAllCTluongMANV(String manv) {
		NhanVienEntity nv = nvService.getNhanVien(manv);
		List<CTLuongEntity> list = nv.getDsctl().stream().collect(Collectors.toList());
		return list;
	}
	
	public List<CTLuongEntity> getAllCTluongPB(String mapb) {
		List<CTLuongEntity> list = getAllCTLuongE();
		return list.stream().filter(o->o.getManvluong().getMapb().getMapb().equals(mapb)).collect(Collectors.toList());
	}
	
	public List<CTLuongEntity> getLuongTheoThangNam(int thang, int nam){
		List<CTLuongEntity> list = getAllCTLuongE();
		return list.stream().filter(o->o.getIdctluong().getThang()==thang&&o.getIdctluong().getNam()==nam).collect(Collectors.toList());
	}
	
	public List<CTLuongEntity> getLuongPhongBan(String mapb, int thang, int nam) {
		List<CTLuongEntity> list = getAllCTluongPB(mapb);
		return list.stream().filter(o->o.getIdctluong().getThang()==thang&&o.getIdctluong().getNam()==nam).collect(Collectors.toList());
	}
	
	public List<CTLuongEntity> getLuongTheoNam(int nam) {
		List<CTLuongEntity> list = getAllCTLuongE();
		return list.stream().filter(o->o.getIdctluong().getNam()==nam).collect(Collectors.toList());
	}
	
	public CTLuongEntity getCTLuongNV(String manv,int thang, int nam) {
		List<CTLuongEntity> list = getAllCTluongMANV(manv);
		return list.stream().filter(o->o.getIdctluong().getThang()==thang&&o.getIdctluong().getNam()==nam).findFirst().orElse(null);
	}
	
//	public boolean autoAddDsLuong() {
//		Date now = new Date();
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(now);
//		int month = calendar.get(Calendar.MONTH);
//		int year = calendar.get(Calendar.YEAR);
//		List<NhanVienEntity> lnv = nvService.getAllNhanVienOfPb();
//		lnv = lnv.stream().filter(o->o.getMatk().getTrangthai()).collect(Collectors.toList());
//		CTLuongEntity ctl = getCTLuongNV(lnv.get(0).getManv(), month, year);
//		if (!ctl.equals(null)) return false;
//		int beforemonth;
//		int beforeyear = year-1;
//		if (month==1) beforemonth=12;
//		else beforemonth = month-1;
//		ctl = getCTLuongNV(lnv.get(0).getManv(), beforemonth, beforeyear);
//		if (ctl.equals(null)) {
//			LuongEntity luong = luService.getBacLuong(defaultBacluong);
//			lnv.forEach(o->{
//				CTLuongEntity newctl = new CTLuongEntity();
//				newctl.setBacluong(defaultBacluong);
//				newctl.setManv(o.getManv());
//				newctl.setThang(month);
//				newctl.setNam(year);
//				newctl.setTientamung(0);
//				newctl.setManvluong(o);
//				newctl.setBacluongentity(luong);
//				Session session = sessionFactory.openSession();
//				Transaction t = session.beginTransaction();
//				try {
//					session.save(newctl);
//					t.commit();
//				} catch (Exception e) {
//					e.printStackTrace();
//					t.rollback();
//				} finally {
//					session.close();
//				}
//			});
//		}
//		else {
//			List<CTLuongEntity> listlu = getLuongPhongBan(lnv.get(0).getMapb().getMapb(), beforemonth, beforeyear);
//			listlu.forEach(o->{
//				o.setThang(month);
//				o.setNam(year);
//				Session session = sessionFactory.openSession();
//				Transaction t = session.beginTransaction();
//				try {
//					session.save(o);
//					t.commit();
//				} catch (Exception e) {
//					t.rollback();
//					e.printStackTrace();
//				} finally {
//					session.close();
//				}
//			});
//		}
//		return true;
//	}
	
}
