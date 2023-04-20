package ptithcm.nhom27.entity;

import java.util.List;

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

@Entity
@Table(name = "QUYDINH")
public class QuyDinhEntity {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "TENQD")
	private String tenqd;
	
	@Column(name = "MOTA")
	private String mota;
	
	
	@OneToMany(mappedBy = "idqd", fetch = FetchType.EAGER)
	private List<MucdoEntity> mucdo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANQL")
	private NhanVienEntity manvql;

	

	public QuyDinhEntity(int id, String tenqd, String mota, List<MucdoEntity> mucdo, NhanVienEntity manvql) {
		super();
		this.id = id;
		this.tenqd = tenqd;
		this.mota = mota;
		this.mucdo = mucdo;
		this.manvql = manvql;
	}

	public QuyDinhEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenqd() {
		return tenqd;
	}

	public void setTenqd(String tenqd) {
		this.tenqd = tenqd;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}


	public List<MucdoEntity> getMucdo() {
		return mucdo;
	}

	public void setMucdo(List<MucdoEntity> mucdo) {
		this.mucdo = mucdo;
	}

	public NhanVienEntity getManvql() {
		return manvql;
	}

	public void setManvql(NhanVienEntity manvql) {
		this.manvql = manvql;
	}

	
}
