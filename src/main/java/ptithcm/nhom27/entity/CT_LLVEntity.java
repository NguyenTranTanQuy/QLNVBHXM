package ptithcm.nhom27.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "CT_LICHLAMVIEC")
public class CT_LLVEntity {
	@EmbeddedId
	CT_LLVKey idctllv;
		
	@ManyToOne
	@MapsId("manv")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "MANV")
	private NhanVienEntity manvlv;
	
	@ManyToOne
	@MapsId("idllv")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "IDLLV")
	private LichLamViecEntity id;

	public CT_LLVEntity() {
		super();
	}

	public CT_LLVKey getIdctllv() {
		return idctllv;
	}

	public void setIdctllv(CT_LLVKey idctllv) {
		this.idctllv = idctllv;
	}

	public NhanVienEntity getManvlv() {
		return manvlv;
	}

	public void setManvlv(NhanVienEntity manvlv) {
		this.manvlv = manvlv;
	}

	public LichLamViecEntity getId() {
		return id;
	}

	public void setId(LichLamViecEntity id) {
		this.id = id;
	}

	public CT_LLVEntity(CT_LLVKey idctllv, NhanVienEntity manvlv, LichLamViecEntity id) {
		super();
		this.idctllv = idctllv;
		this.manvlv = manvlv;
		this.id = id;
	}
}
