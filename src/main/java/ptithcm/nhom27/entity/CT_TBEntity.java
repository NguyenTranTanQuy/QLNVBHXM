package ptithcm.nhom27.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "CT_TB")
public class CT_TBEntity {
	@EmbeddedId
	CT_TBKey idtb;
	
	@ManyToOne(fetch =FetchType.EAGER)
	@MapsId("manv")
	@JoinColumn(name = "MANV")
	private NhanVienEntity manventity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("idtb")
	@JoinColumn(name = "IDTB")
	private ThongBaoEntity matbentity;

	public CT_TBKey getIdtb() {
		return idtb;
	}

	public void setIdtb(CT_TBKey idtb) {
		this.idtb = idtb;
	}

	public NhanVienEntity getManventity() {
		return manventity;
	}

	public void setManventity(NhanVienEntity manventity) {
		this.manventity = manventity;
	}

	public ThongBaoEntity getMatbentity() {
		return matbentity;
	}

	public void setMatbentity(ThongBaoEntity matbentity) {
		this.matbentity = matbentity;
	}

	public CT_TBEntity(CT_TBKey idtb, NhanVienEntity manventity, ThongBaoEntity matbentity) {
		super();
		this.idtb = idtb;
		this.manventity = manventity;
		this.matbentity = matbentity;
	}

	public CT_TBEntity() {
		super();
	}
}
