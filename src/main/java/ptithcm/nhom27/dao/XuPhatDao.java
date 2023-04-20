package ptithcm.nhom27.dao;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.CT_XuphatEntity;
import ptithcm.nhom27.entity.NhanVienEntity;

@Repository(value = "XuPhatDao")
@Transactional
public class XuPhatDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addXuphat(CT_XuphatEntity ctxp) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ctxp);
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
	
	public List<CT_XuphatEntity> getAllXuPhat(){
		Session session = sessionFactory.openSession();
		String hql = "FROM CT_XuphatEntity";
		Query query = session.createQuery(hql);
		return query.getResultList();
	}
	
	public List<CT_XuphatEntity> getXuPhatofPB(String mapb){
		List<CT_XuphatEntity> ctxp = getAllXuPhat();
		return ctxp.stream().filter(o->o.getNventity().getMapb().getMapb().equals(mapb)).collect(Collectors.toList());
	}
	
	public List<CT_XuphatEntity> getXuPhatofMANV(NhanVienEntity nv){
		List<CT_XuphatEntity> ctxp = getAllXuPhat();
		return ctxp.stream().filter(o->o.getNventity().getManv().equals(nv.getManv())).collect(Collectors.toList());
	}
	
	public int getTongTienPhatThang(int thang, int nam, NhanVienEntity nv) {
		List<CT_XuphatEntity> ctxp = getXuPhatofMANV(nv);
		
		List<CT_XuphatEntity> ctxpt = ctxp.stream().filter(o->o.getKey().getThoigianxuphat().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == thang &&
				o.getKey().getThoigianxuphat().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == nam).collect(Collectors.toList());
		int tongtienphat = 0;
		for (int i =0 ;i<ctxpt.size();i++) {
			tongtienphat+=ctxpt.get(i).getIdmd().getTienphat();
		}
		return tongtienphat;
	}
}
