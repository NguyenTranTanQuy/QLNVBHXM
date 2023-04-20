package ptithcm.nhom27.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CT_TBKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "MANV")
	private String manv;
	
	@Column(name="IDTB")
	private int idtb;

	public CT_TBKey() {
		super();
	}

	public CT_TBKey(String manv, int idtb) {
		super();
		this.manv = manv;
		this.idtb = idtb;
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public int getIdtb() {
		return idtb;
	}

	public void setIdtb(int idtb) {
		this.idtb = idtb;
	}
}
