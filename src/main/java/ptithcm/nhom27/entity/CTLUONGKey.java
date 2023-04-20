package ptithcm.nhom27.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CTLUONGKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BACLUONG")
	private int bacluong;
	
	@Column(name="THANG")
	private int thang;
	
	@Column(name="NAM")
	private int nam;
	
	@Column(name="MANV")
	private String manv;
	
	public int getBacluong() {
		return bacluong;
	}

	public void setBacluong(int bacluong) {
		this.bacluong = bacluong;
	}

	public int getThang() {
		return thang;
	}

	public void setThang(int thang) {
		this.thang = thang;
	}

	public int getNam() {
		return nam;
	}

	public void setNam(int nam) {
		this.nam = nam;
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public CTLUONGKey(int bacluong, int thang, int nam, String manv) {
		super();
		this.bacluong = bacluong;
		this.thang = thang;
		this.nam = nam;
		this.manv = manv;
	}

	public CTLUONGKey() {
		super();
	}
}
