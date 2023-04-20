package ptithcm.nhom27.entity;

import java.util.Collection;
import java.sql.Date;
import java.sql.Time;

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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "LICHLAMVIEC")
public class LichLamViecEntity {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "NGAYLAM")
	private Date ngaylam;
	
	@Column(name = "GIOBATDAU")
	private Time giobatdau;
	
	@Column(name = "GIOKETTHUC")
	private Time gioketthuc;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANQL")
	private NhanVienEntity manvql;
	
	@OneToMany(mappedBy = "id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<CT_LLVEntity> dsllv;
	
	@OneToMany(mappedBy = "idcm")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<ChamCongEntity> idllv;

	public LichLamViecEntity(int id, Date ngaylam, Time giobatdau, Time gioketthuc, NhanVienEntity manvql,
			Collection<CT_LLVEntity> dsllv, Collection<ChamCongEntity> idllv) {
		super();
		this.id = id;
		this.ngaylam = ngaylam;
		this.giobatdau = giobatdau;
		this.gioketthuc = gioketthuc;
		this.manvql = manvql;
		this.dsllv = dsllv;
		this.idllv = idllv;
	}

	public LichLamViecEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getNgaylam() {
		return ngaylam;
	}

	public void setNgaylam(Date ngaylam) {
		this.ngaylam = ngaylam;
	}

	public Time getGiobatdau() {
		return giobatdau;
	}

	public void setGiobatdau(Time giobatdau) {
		this.giobatdau = giobatdau;
	}

	public Time getGioketthuc() {
		return gioketthuc;
	}

	public void setGioketthuc(Time gioketthuc) {
		this.gioketthuc = gioketthuc;
	}

	public NhanVienEntity getManvql() {
		return manvql;
	}

	public void setManvql(NhanVienEntity manvql) {
		this.manvql = manvql;
	}

	public Collection<CT_LLVEntity> getDsllv() {
		return dsllv;
	}

	public void setDsllv(Collection<CT_LLVEntity> dsllv) {
		this.dsllv = dsllv;
	}

	public Collection<ChamCongEntity> getIdllv() {
		return idllv;
	}

	public void setIdllv(Collection<ChamCongEntity> idllv) {
		this.idllv = idllv;
	}

	
	
	
}
