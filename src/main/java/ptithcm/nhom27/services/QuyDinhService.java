package ptithcm.nhom27.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.QuyDinhDao;
import ptithcm.nhom27.entity.PhongBanEntity;
import ptithcm.nhom27.entity.QuyDinhEntity;

@Service
@Transactional
public class QuyDinhService {

	@Autowired
	private QuyDinhDao dao;
	
	public List<QuyDinhEntity> getAllQDPB(PhongBanEntity pb) {
		return dao.getAllQDPB(pb);
	}
	
	public boolean addQuyDinh(QuyDinhEntity qd) {
		return dao.addQuyDinh(qd);
	}
	
	public boolean deleteQD(QuyDinhEntity qd) {
		return dao.deleteQD(qd);
	}
	public QuyDinhEntity getqdbyID(int id) {
		return dao.getqdbyID(id);
	}
}
