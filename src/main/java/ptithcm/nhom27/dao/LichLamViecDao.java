package ptithcm.nhom27.dao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.CT_LLVEntity;
import ptithcm.nhom27.entity.LichLamViecEntity;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.services.CT_LLVService;
import ptithcm.nhom27.services.NhanVienService;

@Repository(value = "LichLamViecDao")
@Transactional
public class LichLamViecDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	NhanVienService nvService;
	
	@Autowired
	CT_LLVService ctllvService;
	
	public List<LichLamViecEntity> getAllLichLamViec() {
		Session session = sessionFactory.openSession();
		String hql = "From LichLamViecEntity order by ngaylam DESC, giobatdau ASC";
		Query query = session.createQuery(hql);
		List<LichLamViecEntity> list = query.getResultList();
		session.close();
		return list;
	}
	
	public List<LichLamViecEntity> getLLVofDate(Date date) {
		List<LichLamViecEntity> list = getAllLichLamViec();
		return list.stream().filter(o->o.getNgaylam().toString().equals(date.toString())).collect(Collectors.toList());
	}
	
	public List<LichLamViecEntity> getLLVPBofDate(Date date) {
		NhanVienEntity nv = nvService.getNVLogin();
		List<LichLamViecEntity> list = getLLVofDate(date);
		return list.stream().filter(o->o.getManvql().getMapb().getMapb().equals(nv.getMapb().getMapb())).collect(Collectors.toList());
	}
	
	public List<CT_LLVEntity> getCTLLVnow() {
		NhanVienEntity nv = nvService.getNVLogin();
		List<CT_LLVEntity> ctllv = nv.getDsllv().stream().collect(Collectors.toList());
		long now = System.currentTimeMillis();
		Date datenow = new Date(now);
		ctllv = ctllv.stream().filter(o->o.getId().getNgaylam().toString().equals(datenow.toString())).collect(Collectors.toList());
		return ctllv.stream().sorted(new Comparator<CT_LLVEntity>() {

			@Override
			public int compare(CT_LLVEntity o1, CT_LLVEntity o2) {
				
				return o1.getId().getGiobatdau().compareTo(o2.getId().getGiobatdau());
			}
		}).collect(Collectors.toList());
	}
	
	
	public int getMaxID() {
		List<LichLamViecEntity> list = getAllLichLamViec();
		return list.size();
	}
	
	public boolean addoreditLichLamViec(LichLamViecEntity llv) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(llv);
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
	}
//	if (timeStart.getHour()>=endT.getHour()+1 && timeStart.getMinute()>=endT.getMinute()) 
//	if (timeEnd.getHour()+1<=startT.getHour()&&timeEnd.getMinute()<=startT.getMinute()) 
	
	public boolean nvCoTheNhanLichLamViec(LichLamViecEntity llv, NhanVienEntity nv){
		List<NhanVienEntity> lnv = nvService.getAllNhanVienOfPb();
		LocalTime timeStart = llv.getGiobatdau().toLocalTime();
		LocalTime timeEnd = llv.getGioketthuc().toLocalTime();
		List<CT_LLVEntity> ctllv = ctllvService.getCTLLVofDate(llv.getNgaylam());
		List<CT_LLVEntity> ctllvs = ctllv.stream().filter(o->o.getIdctllv().getManv().equals(nv.getManv())).collect(Collectors.toList());
		if (ctllvs.isEmpty()) {
			return true;
		}
		for (int i=0;i<ctllvs.size();i++) {
			if (ctllvs.get(i).getId().getId()==llv.getId()) return true;
			LocalTime endT = ctllvs.get(i).getId().getGioketthuc().toLocalTime();
			if (timeStart.getHour()>=endT.getHour()+1 && timeStart.getMinute()>=endT.getMinute()) {
				if (i+1<ctllvs.size()) {
					LocalTime startT = ctllvs.get(i+1).getId().getGiobatdau().toLocalTime();
					if (timeEnd.getHour()+1<=startT.getHour()&&timeEnd.getMinute()<=startT.getMinute()) {
						return true;
					}
				}
				else {
					return true;
				}
			}
		}
		return false;
	}
}
