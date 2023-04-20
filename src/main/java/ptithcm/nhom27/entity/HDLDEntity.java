package ptithcm.nhom27.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "HOPDONGLAODONG")
public class HDLDEntity {
	@Id
	@Column(name = "MAHDLD")
	private String mahdld;
	
	@Column(name = "NGAYBATDAU")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngaybd;
	
	@Column(name = "NGAYKETTHUC")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngaykt;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANV")
	private NhanVienEntity manv;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BACLUONG")
	private LuongEntity luongentity;

	public HDLDEntity(String mahdld, Date ngaybd, Date ngaykt, NhanVienEntity manv, LuongEntity luongentity) {
		super();
		this.mahdld = mahdld;
		this.ngaybd = ngaybd;
		this.ngaykt = ngaykt;
		this.manv = manv;
		this.luongentity = luongentity;
	}

	public HDLDEntity() {
		super();
	}

	public String getMahdld() {
		return mahdld;
	}

	public void setMahdld(String mahdld) {
		this.mahdld = mahdld;
	}

	public Date getNgaybd() {
		return ngaybd;
	}

	public void setNgaybd(Date ngaybd) {
		this.ngaybd = ngaybd;
	}

	public Date getNgaykt() {
		return ngaykt;
	}

	public void setNgaykt(Date ngaykt) {
		this.ngaykt = ngaykt;
	}

	public NhanVienEntity getManv() {
		return manv;
	}

	public void setManv(NhanVienEntity manv) {
		this.manv = manv;
	}

	public LuongEntity getLuongentity() {
		return luongentity;
	}

	public void setLuongentity(LuongEntity luongentity) {
		this.luongentity = luongentity;
	}
}
