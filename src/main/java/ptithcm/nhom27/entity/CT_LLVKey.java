package ptithcm.nhom27.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CT_LLVKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "MANV")
	private String manv;
	
	@Column(name = "IDLLV")
	private int idllv;

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public int getIdllv() {
		return idllv;
	}

	public void setIdllv(int idllv) {
		this.idllv = idllv;
	}

	public CT_LLVKey(String manv, int idllv) {
		super();
		this.manv = manv;
		this.idllv = idllv;
	}

	public CT_LLVKey() {
		super();
	}
}
