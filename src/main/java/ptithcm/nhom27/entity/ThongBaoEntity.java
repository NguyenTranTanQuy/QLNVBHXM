package ptithcm.nhom27.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "THONGBAO")
public class ThongBaoEntity {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "NGAYTB")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date ngaytb;

	@Column(name = "NOIDUNG")
	private String noidung;

	@OneToMany(mappedBy = "matbentity", fetch = FetchType.EAGER)
	private Collection<CT_TBEntity> dstb;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANQL")
	private NhanVienEntity manvql;

	public ThongBaoEntity() {
		super();
	}

	public ThongBaoEntity(int id, Date ngaytb, String noidung, Collection<CT_TBEntity> dstb, NhanVienEntity manvql) {
		super();
		this.id = id;
		this.ngaytb = ngaytb;
		this.noidung = noidung;
		this.dstb = dstb;
		this.manvql = manvql;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getNgaytb() {
		return ngaytb;
	}

	public void setNgaytb(Date ngaytb) {
		this.ngaytb = ngaytb;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public Collection<CT_TBEntity> getDstb() {
		return dstb;
	}

	public void setDstb(Collection<CT_TBEntity> dstb) {
		this.dstb = dstb;
	}

	public NhanVienEntity getManvql() {
		return manvql;
	}

	public void setManvql(NhanVienEntity manvql) {
		this.manvql = manvql;
	}
}
