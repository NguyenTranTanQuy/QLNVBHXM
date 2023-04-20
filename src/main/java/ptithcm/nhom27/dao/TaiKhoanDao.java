package ptithcm.nhom27.dao;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.TaiKhoanEntity;
import ptithcm.nhom27.services.ChucVuService;
import ptithcm.nhom27.services.NhanVienService;

@Repository(value = "TaiKhoanDao")
@Transactional
public class TaiKhoanDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ChucVuService cvService;
	
	@Autowired
	NhanVienService nvService;

	private final String defaultPassword = "$2a$12$kLFl2zPPGqOjoIOya3qSt.VelEioChNL0TkiO1kZVYLlFFVN8inGq";

	public boolean autoaddTaiKhoan(NhanVienEntity nv) {
//		byte[] array = new byte[7];
//		new Random().nextBytes(array);
//		String generateString = new String(array, Charset.forName("UTF-8"));

		String username = "EMP" + nv.getManv();
		TaiKhoanEntity tk = new TaiKhoanEntity();
		tk.setTentk(username);
		tk.setMk(defaultPassword);
		tk.setChucvu(cvService.getE());
		tk.setTrangthai(true);
		tk.setManv(nv);

		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(tk);
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

	public boolean resetPassword(TaiKhoanEntity tk) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		tk.setMk(defaultPassword);
		try {
			session.update(tk);
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

	public List<TaiKhoanEntity> getAllTaiKhoan() {
		Session session = sessionFactory.openSession();
		String hql = "FROM TaiKhoanEntity";
		Query query = session.createQuery(hql);
		List<TaiKhoanEntity> list = query.getResultList();
		session.close();
		return list;
	}
	
	public List<TaiKhoanEntity> getAllTaiKhoanpb() {
		List<TaiKhoanEntity> list = getAllTaiKhoan();
		String pb = nvService.getPhongBan().getMapb();
		return list.stream().filter(o->o.getManv().getMapb().getMapb().equals(pb)&&o.getChucvu().getMacv().equals("E")).collect(Collectors.toList());
	}

	public TaiKhoanEntity getTaiKhoan(String manv) {
		List<TaiKhoanEntity> list = getAllTaiKhoan();
		return list.stream().filter(o -> manv.equals(o.getManv().getManv())).findFirst().orElse(null);
	}

	public boolean changeTaiKhoan(TaiKhoanEntity tk) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		String passencode = new BCryptPasswordEncoder().encode(tk.getMk());
		tk.setMk(passencode);
		try {
			session.update(tk);
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

	public boolean changeStatus(TaiKhoanEntity tk, boolean  status) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		tk.setTrangthai(status);
		try {
			session.update(tk);
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

	public boolean checkPassword(String password, String encodepassword) {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		return (encode.matches(password, encodepassword));
	}
}
