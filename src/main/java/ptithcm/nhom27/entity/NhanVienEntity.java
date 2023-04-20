package ptithcm.nhom27.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "NHANVIEN")
public class NhanVienEntity {
	@Id
	@Column(name = "MANV")
	private String manv;

	@Column(name = "CMND")
	private String cmnd;

	@Column(name = "HO")
	private String ho;

	@Column(name = "TEN")
	private String ten;

	@Column(name = "NGAYSINH")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngaysinh;

	@Column(name = "GIOITINH")
	private String gioitinh;

	@Column(name = "DIACHI")
	private String diachi;

	@Column(name = "SDT")
	private String sdt;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ANH")
	private String anh;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MAPB")
	private PhongBanEntity mapb;

	@OneToMany(mappedBy = "manvql")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<LichLamViecEntity> dsnvqlllv;

	@OneToMany(mappedBy = "manvql")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<ThongBaoEntity> dsnvqltb;

	@OneToMany(mappedBy = "manvlv")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<CT_LLVEntity> dsllv;

	@OneToMany(mappedBy = "manv")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<ChamCongEntity> bangchamcong;

	@OneToMany(mappedBy = "manventity")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<CT_TBEntity> dstb;

	@OneToMany(mappedBy = "manvluong")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<CTLuongEntity> dsctl;

	@OneToMany(mappedBy = "nventity")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<CT_XuphatEntity> dsxp;
	
	@OneToMany(mappedBy = "manvql")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<QuyDinhEntity> dsqd;

	@OneToOne(mappedBy = "manv")
	private TaiKhoanEntity matk;

	@OneToMany(mappedBy = "manv")
	private Collection<HDLDEntity> hdld;

	@OneToOne(mappedBy = "manvql")
	private PhongBanEntity manvqlpb;

	public NhanVienEntity(String manv, String cmnd, String ho, String ten, Date ngaysinh, String gioitinh,
			String diachi, String sdt, String email, String anh, PhongBanEntity mapb,
			Collection<LichLamViecEntity> dsnvqlllv, Collection<ThongBaoEntity> dsnvqltb,
			Collection<CT_LLVEntity> dsllv, Collection<ChamCongEntity> bangchamcong, Collection<CT_TBEntity> dstb,
			Collection<CTLuongEntity> dsctl, Collection<CT_XuphatEntity> dsxp, Collection<QuyDinhEntity> dsqd,
			TaiKhoanEntity matk, Collection<HDLDEntity> hdld, PhongBanEntity manvqlpb) {
		super();
		this.manv = manv;
		this.cmnd = cmnd;
		this.ho = ho;
		this.ten = ten;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
		this.sdt = sdt;
		this.email = email;
		this.anh = anh;
		this.mapb = mapb;
		this.dsnvqlllv = dsnvqlllv;
		this.dsnvqltb = dsnvqltb;
		this.dsllv = dsllv;
		this.bangchamcong = bangchamcong;
		this.dstb = dstb;
		this.dsctl = dsctl;
		this.dsxp = dsxp;
		this.dsqd = dsqd;
		this.matk = matk;
		this.hdld = hdld;
		this.manvqlpb = manvqlpb;
	}

	public Collection<ThongBaoEntity> getDsnvqltb() {
		return dsnvqltb;
	}

	public void setDsnvqltb(Collection<ThongBaoEntity> dsnvqltb) {
		this.dsnvqltb = dsnvqltb;
	}

	public Collection<CT_TBEntity> getDstb() {
		return dstb;
	}

	public void setDstb(Collection<CT_TBEntity> dstb) {
		this.dstb = dstb;
	}

	public Collection<CT_XuphatEntity> getDsxp() {
		return dsxp;
	}

	public void setDsxp(Collection<CT_XuphatEntity> dsxp) {
		this.dsxp = dsxp;
	}

	public Collection<QuyDinhEntity> getDsqd() {
		return dsqd;
	}

	public void setDsqd(Collection<QuyDinhEntity> dsqd) {
		this.dsqd = dsqd;
	}

	public NhanVienEntity() {
		super();
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public Collection<CT_LLVEntity> getDsllv() {
		return dsllv;
	}

	public void setDsllv(Collection<CT_LLVEntity> dsllv) {
		this.dsllv = dsllv;
	}

	public Collection<LichLamViecEntity> getDsnvqlllv() {
		return dsnvqlllv;
	}

	public void setDsnvqlllv(Collection<LichLamViecEntity> dsnvqlllv) {
		this.dsnvqlllv = dsnvqlllv;
	}

	public Collection<CTLuongEntity> getDsctl() {
		return dsctl;
	}

	public void setDsctl(Collection<CTLuongEntity> dsctl) {
		this.dsctl = dsctl;
	}

	public Collection<ChamCongEntity> getBangchamcong() {
		return bangchamcong;
	}

	public void setBangchamcong(Collection<ChamCongEntity> bangchamcong) {
		this.bangchamcong = bangchamcong;
	}

	public TaiKhoanEntity getMatk() {
		return matk;
	}

	public void setMatk(TaiKhoanEntity matk) {
		this.matk = matk;
	}

	

	public Collection<HDLDEntity> getHdld() {
		return hdld;
	}

	public void setHdld(Collection<HDLDEntity> hdld) {
		this.hdld = hdld;
	}

	public PhongBanEntity getMapb() {
		return mapb;
	}

	public void setMapb(PhongBanEntity mapb) {
		this.mapb = mapb;
	}

	public PhongBanEntity getManvqlpb() {
		return manvqlpb;
	}

	public void setManvqlpb(PhongBanEntity manvqlpb) {
		this.manvqlpb = manvqlpb;
	}
}
