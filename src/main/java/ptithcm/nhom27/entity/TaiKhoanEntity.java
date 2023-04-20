package ptithcm.nhom27.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "TAIKHOAN")
public class TaiKhoanEntity {
	@Id
	@Column(name = "TENTK")
	private String tentk;
	
	@Column(name = "MATKHAU")
	private String mk;
	
	@Column(name = "TRANGTHAI")
	private boolean trangthai;
	
	@OneToOne
	@JoinColumn(name = "MANV")
	private NhanVienEntity manv;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MAQUYEN")
	private ChucVuEntity chucvu;

	public TaiKhoanEntity(String tentk, String mk, boolean trangthai, NhanVienEntity manv, ChucVuEntity chucvu) {
		super();
		this.tentk = tentk;
		this.mk = mk;
		this.trangthai = trangthai;
		this.manv = manv;
		this.chucvu = chucvu;
	}

	public TaiKhoanEntity() {
		super();
	}

	public String getTentk() {
		return tentk;
	}

	public void setTentk(String tentk) {
		this.tentk = tentk;
	}

	public String getMk() {
		return mk;
	}

	public void setMk(String mk) {
		this.mk = mk;
	}

	public boolean getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
	}

	public NhanVienEntity getManv() {
		return manv;
	}

	public void setManv(NhanVienEntity manv) {
		this.manv = manv;
	}

	public ChucVuEntity getChucvu() {
		return chucvu;
	}

	public void setChucvu(ChucVuEntity chucvu) {
		this.chucvu = chucvu;
	}
	
}
