package ptithcm.nhom27.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PHONGBAN")
public class PhongBanEntity {
	
	@Id
	@Column(name = "MAPB")
	private String mapb;
	
	@Column(name = "TENPB")
	private String tenpb;
	
	@Column(name = "MOTACONGVIEC")
	private String mota;
	
	@OneToMany(mappedBy = "mapb", fetch = FetchType.EAGER)
	private Collection<NhanVienEntity> dsnvpb;
	
	@OneToOne
	@JoinColumn(name = "MANQL")
	private NhanVienEntity manvql;

	public PhongBanEntity(String mapb, String tenpb, String mota, Collection<NhanVienEntity> dsnvpb,
			NhanVienEntity manvql) {
		super();
		this.mapb = mapb;
		this.tenpb = tenpb;
		this.mota = mota;
		this.dsnvpb = dsnvpb;
		this.manvql = manvql;
	}

	public PhongBanEntity() {
		super();
	}

	public String getMapb() {
		return mapb;
	}

	public void setMapb(String mapb) {
		this.mapb = mapb;
	}

	public String getTenpb() {
		return tenpb;
	}

	public void setTenpb(String tenpb) {
		this.tenpb = tenpb;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public Collection<NhanVienEntity> getDsnvpb() {
		return dsnvpb;
	}

	public void setDsnvpb(Collection<NhanVienEntity> dsnvpb) {
		this.dsnvpb = dsnvpb;
	}

	public NhanVienEntity getManvql() {
		return manvql;
	}

	public void setManvql(NhanVienEntity manvql) {
		this.manvql = manvql;
	}
	
}
