package ptithcm.nhom27.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.CT_LLVEntity;
import ptithcm.nhom27.entity.ChamCongEntity;
import ptithcm.nhom27.entity.LichLamViecEntity;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.services.CT_LLVService;
import ptithcm.nhom27.services.LichLamViecService;
import ptithcm.nhom27.services.NhanVienService;

@Repository("ChamCongDao")
@Transactional
public class ChamCongDao {
	@Autowired
	NhanVienService nvService;

	@Autowired
	LichLamViecService llvService;

	@Autowired
	CT_LLVService ctllvService;

	@Autowired
	SessionFactory sessionFactory;

	public List<ChamCongEntity> getAllChamCong() {
		Session session = sessionFactory.openSession();
		String hql = "FROM ChamCongEntity";
		Query query = session.createQuery(hql);
		List<ChamCongEntity> list = query.getResultList();
		return list;
	}

	public List<ChamCongEntity> getAllChamCongPB() {
		List<ChamCongEntity> list = getAllChamCong();
		NhanVienEntity nv = nvService.getNVLogin();
		return list.stream().filter(o -> o.getManv().getMapb().getMapb().equals(nv.getMapb().getMapb()))
				.collect(Collectors.toList());
	}

	public List<ChamCongEntity> getAllChamCongPersonal() {
		NhanVienEntity nv = nvService.getNVLogin();
		List<ChamCongEntity> list = getAllChamCongPB();
		return list.stream().filter(o -> o.getManv().getManv().equals(nv.getManv())).collect(Collectors.toList());
	}

	public int getMaxID() {
		List<ChamCongEntity> list = getAllChamCong();
		return list.size();
	}

	public ChamCongEntity getCheckin(String manv) {
		Session session = sessionFactory.openSession();
		String hql = "FROM ChamCongEntity WHERE manv.manv=:manv AND ngaycc=:date AND giora = null";
		Query query = session.createQuery(hql);
		long milis = System.currentTimeMillis();
		Date date = new Date(milis);
		query.setParameter("manv", manv);
		query.setParameter("date", date);
		return (ChamCongEntity) query.uniqueResult();
	}

	public ChamCongEntity getCCtheoLLV(LichLamViecEntity llv, String manv) {
		Session session = sessionFactory.openSession();
		String hql = "FROM ChamCongEntity WHERE idcm.id=:id AND manv.manv=:manv";
		Query query = session.createQuery(hql);
		query.setParameter("id", llv.getId());
		query.setParameter("manv", manv);
		return (ChamCongEntity) query.uniqueResult();
	}

	public String createStatus(String statusCheckin, String statusCheckout) {
		if (statusCheckin.equals("Đi trễ") && statusCheckout.equals("Về sớm")) {
			return statusCheckin + ", " + statusCheckout;
		} else if (statusCheckin.equals("Đi trễ")) {
			return statusCheckin;
		} else if (statusCheckout.equals("Về sớm")) {
			return statusCheckout;
		} else {
			return statusCheckin;
		}
	}

	public List<ChamCongEntity> getDsChamCongThangHT(NhanVienEntity nv, int thang, int nam) {
		List<ChamCongEntity> listcc = nv.getBangchamcong().stream().collect(Collectors.toList());
		List<ChamCongEntity> lccMonth = listcc.stream()
				.filter(o -> o.getNgaycc().toLocalDate().getMonthValue() == thang && o.getNgaycc().toLocalDate().getYear() == nam).collect(Collectors.toList());
		return lccMonth;
	}

	public List<ChamCongEntity> dsDiTreVesomThang(NhanVienEntity nv, int thang, int nam) {
		List<ChamCongEntity> lccMonth = getDsChamCongThangHT(nv, thang, nam);
		return lccMonth.stream().filter(o -> o.getTrangthai().equals("Đi trễ, Về sớm")).collect(Collectors.toList());
	}

	public List<ChamCongEntity> dsDiTreThang(NhanVienEntity nv, int thang, int nam) {
		List<ChamCongEntity> lccMonth = getDsChamCongThangHT(nv, thang, nam);
		return lccMonth.stream().filter(o -> o.getTrangthai().equals("Đi trễ")).collect(Collectors.toList());
	}

	public List<ChamCongEntity> dsVesomThang(NhanVienEntity nv, int thang, int nam) {
		List<ChamCongEntity> lccMonth = getDsChamCongThangHT(nv, thang, nam);
		return lccMonth.stream().filter(o -> o.getTrangthai().equals("Về sớm")).collect(Collectors.toList());
	}

	public List<ChamCongEntity> dsKhongDiLamThang(NhanVienEntity nv, int thang, int nam) {
		List<ChamCongEntity> lccMonth = getDsChamCongThangHT(nv, thang, nam);
		return lccMonth.stream().filter(o -> o.getTrangthai().equals("Không đi làm")).collect(Collectors.toList());
	}

	public long soNgayLam(NhanVienEntity nv, int thang, int nam) {
		List<CT_LLVEntity> ctllv = nv.getDsllv().stream().collect(Collectors.toList());
		Set<Date> set = new HashSet<Date>();
		ctllv.forEach(o -> {
			if (o.getId().getNgaylam().toLocalDate().getMonthValue() == thang && o.getId().getNgaylam().toLocalDate().getYear() == nam)
				set.add(o.getId().getNgaylam());
		});
		return set.size();
	}

	public static double roundAvoid(double value, int places) {
		double scale = Math.pow(10, places);
		return Math.round(value * scale) / scale;
	}

	
	public double soGioDiTreThang(NhanVienEntity nv, int thang, int nam) {
		List<ChamCongEntity> lditre1 = dsDiTreThang(nv, thang, nam);
		List<ChamCongEntity> lditre2 = dsDiTreVesomThang(nv, thang, nam);
		double soGioDiTre = 0;
		for (int i = 0; i < lditre1.size(); i++) {
			Time tgcc = lditre1.get(i).getGiovao();
			Time tglv = lditre1.get(i).getIdcm().getGiobatdau();
			soGioDiTre += (double) (tgcc.getTime() - tglv.getTime()) / 3600000;
		}

		for (int i = 0; i < lditre2.size(); i++) {
			Time tgcc = lditre2.get(i).getGiovao();
			Time tglv = lditre2.get(i).getIdcm().getGiobatdau();
			soGioDiTre += (double) (tgcc.getTime() - tglv.getTime()) / 3600000;
		}

		return roundAvoid(soGioDiTre, 2);
	}

	public double soGioVeSomThang(NhanVienEntity nv, int thang, int nam) {
		List<ChamCongEntity> lditre1 = dsVesomThang(nv, thang, nam);
		List<ChamCongEntity> lditre2 = dsDiTreVesomThang(nv, thang, nam);
		double soGioVesom = 0;
		for (int i = 0; i < lditre1.size(); i++) {
			Time tgcc = lditre1.get(i).getGiora();
			Time tglv = lditre1.get(i).getIdcm().getGioketthuc();
			soGioVesom += (double) (tglv.getTime() - tgcc.getTime()) / 3600000;
		}

		for (int i = 0; i < lditre2.size(); i++) {
			Time tgcc = lditre2.get(i).getGiora();
			Time tglv = lditre2.get(i).getIdcm().getGioketthuc();
			soGioVesom += (double) (tglv.getTime() - tgcc.getTime()) / 3600000;
		}

		return roundAvoid(soGioVesom, 2);
	}

	public double soGioKhongDiLamThang(NhanVienEntity nv, int thang, int nam) {
		List<ChamCongEntity> lKhongDiLam = dsKhongDiLamThang(nv, thang, nam);
		double soGioKhongDiLam = 0;
		for (int i = 0; i < lKhongDiLam.size(); i++) {
			soGioKhongDiLam += (double) (lKhongDiLam.get(i).getIdcm().getGioketthuc().getTime()
					- lKhongDiLam.get(i).getIdcm().getGiobatdau().getTime()) / 3600000;
		}
		return roundAvoid(soGioKhongDiLam, 2);
	}

	public double tongSoCanGioLam(NhanVienEntity nv, int thang, int nam) {
		List<CT_LLVEntity> ctllv = nv.getDsllv().stream().collect(Collectors.toList());
		List<CT_LLVEntity> ctllvthang = ctllv.stream().filter(o->o.getId().getNgaylam().toLocalDate().getMonthValue()== thang && o.getId().getNgaylam().toLocalDate().getYear() == nam).collect(Collectors.toList());
		double tongSoGioLam = 0;

		for (int i = 0; i < ctllvthang.size(); i++) {
			tongSoGioLam += (double) (ctllvthang.get(i).getId().getGioketthuc().getTime()
					- ctllvthang.get(i).getId().getGiobatdau().getTime()) / 3600000;
		}
		return roundAvoid(tongSoGioLam, 2);
	}
	
	public double phanTramDiLam(NhanVienEntity nv, int thang, int nam) {
		double tongSoGioCanLam = tongSoCanGioLam(nv, thang, nam);
		double soGioDiTre = soGioDiTreThang(nv, thang, nam);
		double soGioVeSom = soGioVeSomThang(nv, thang, nam);
		double soGioKhongDiLam = soGioKhongDiLamThang(nv, thang, nam);
		double soGioLamThuc = tongSoGioCanLam - soGioDiTre - soGioVeSom - soGioKhongDiLam;
		double phanTram  = roundAvoid((soGioLamThuc/tongSoGioCanLam)*100, 2);
		return roundAvoid((soGioLamThuc/tongSoGioCanLam)*100, 2);
	}
	
	public String createStatusCheckin(Time checkin, LichLamViecEntity llv) {
		if (checkin.getTime() - llv.getGiobatdau().getTime() > 0)
			return "Đi trễ";
		return "Đúng giờ";
	}

	public String createStatusCheckout(Time checkout, LichLamViecEntity llv) {
		if (checkout.before(llv.getGioketthuc()))
			return "Về sớm";
		return "Đúng giờ";
	}

	public boolean nvcheckin(Time checkin, LichLamViecEntity llv, NhanVienEntity nv) {
		ChamCongEntity cc = new ChamCongEntity();
//		LocalTime lct = checkin.toLocalTime();
//		LocalTime deleteSecond = LocalTime.of(lct.getHour(), lct.getMinute());
//		Time realCheckin = Time.valueOf(deleteSecond);
		cc.setId(getMaxID() + 1);
		cc.setNgaycc(llv.getNgaylam());
		cc.setGiovao(checkin);
		cc.setIdcm(llv);
		cc.setManv(nv);
		cc.setTrangthai(createStatusCheckin(checkin, llv));
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.saveOrUpdate(cc);
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}

	}

	public boolean nvcheckout(Time checkout, String manv) {
		ChamCongEntity cc = getCheckin(manv);
//		LocalTime lct = checkout.toLocalTime();
//		LocalTime deleteSecond = LocalTime.of(lct.getHour(), lct.getMinute());
//		Time realCheckout = Time.valueOf(deleteSecond);
		cc.setGiora(checkout);
		String statuscheckout = createStatusCheckout(checkout, cc.getIdcm());
		cc.setTrangthai(createStatus(cc.getTrangthai(), statuscheckout));
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.saveOrUpdate(cc);
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	public void autoCheckChamCong() {
		long milis = System.currentTimeMillis();
		Date date = new Date(milis);
		LocalDate localdate = date.toLocalDate();
		localdate = localdate.minusDays(1);
		date = Date.valueOf(localdate);

		List<CT_LLVEntity> lctllv = ctllvService.getCTLLVofDate(date);

		for (int i = 0; i < lctllv.size(); i++) {
			ChamCongEntity cc = getCCtheoLLV(lctllv.get(i).getId(), lctllv.get(i).getIdctllv().getManv());
			if (cc == null) {
				Time t = Time.valueOf("00:00:00");
				Session session = sessionFactory.openSession();
				Transaction tran = session.beginTransaction();
				ChamCongEntity ccnew = new ChamCongEntity();
				ccnew.setId(getMaxID() + 1);
				ccnew.setGiora(t);
				ccnew.setGiovao(t);
				ccnew.setIdcm(lctllv.get(i).getId());
				ccnew.setManv(lctllv.get(i).getManvlv());
				ccnew.setNgaycc(date);
				ccnew.setTrangthai("Không đi làm");
				try {
					session.save(ccnew);
					tran.commit();
				} catch (Exception e) {
					e.printStackTrace();
					tran.rollback();
				} finally {
					session.close();
				}
			} else if (cc.getGiora() == null) {
				Session session = sessionFactory.openSession();
				Transaction tran = session.beginTransaction();
				Time t = Time.valueOf("00:00:00");
				cc.setGiora(t);
				cc.setTrangthai("Chưa checkout");
				try {
					session.update(cc);
					tran.commit();
				} catch (Exception e) {
					e.printStackTrace();
					tran.rollback();
				} finally {
					session.close();
				}
			}
		}

	}

}
