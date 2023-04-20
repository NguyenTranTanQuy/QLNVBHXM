package ptithcm.nhom27.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QUYEN")
public class ChucVuEntity {
	@Id
	@Column(name = "MAQUYEN")
	private String macv;
	
	@Column(name = "TENQUYEN")
	private String tencv;
	
	@OneToMany(mappedBy = "chucvu",fetch = FetchType.EAGER)
	Collection<TaiKhoanEntity> dstk;

	public ChucVuEntity(String macv, String tencv, Collection<TaiKhoanEntity> dstk) {
		super();
		this.macv = macv;
		this.tencv = tencv;
		this.dstk = dstk;
	}

	public ChucVuEntity() {
		super();
	}

	public String getMacv() {
		return macv;
	}

	public void setMacv(String macv) {
		this.macv = macv;
	}

	public String getTencv() {
		return tencv;
	}

	public void setTencv(String tencv) {
		this.tencv = tencv;
	}

	public Collection<TaiKhoanEntity> getDstk() {
		return dstk;
	}

	public void setDstk(Collection<TaiKhoanEntity> dstk) {
		this.dstk = dstk;
	}
}
