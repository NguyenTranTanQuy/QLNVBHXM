package ptithcm.nhom27.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.PhongBanEntity;

@Repository(value="PhongBanDao")
@Transactional
public class PhongBanDao {
	@Autowired
	private SessionFactory factory;
	
	public List<PhongBanEntity> getListOffice() {
		List<PhongBanEntity> listOffice = new ArrayList<>();
		String hql = "FROM PhongBanEntity";
		Session session = factory.openSession();
		Query query = session.createQuery(hql);
		listOffice = query.getResultList();
		return listOffice;
	}
	
	public PhongBanEntity getPhongban(String mapb) {
		Session session = factory.openSession();
		return session.get(PhongBanEntity.class, mapb);
	}
	
	public long slNVconlam(String mapb) {
		PhongBanEntity pb = getPhongban(mapb);
		List<NhanVienEntity> lnv = pb.getDsnvpb().stream().collect(Collectors.toList());
		return lnv.stream().filter(o->o.getMatk().getTrangthai()).count();
	}
}
