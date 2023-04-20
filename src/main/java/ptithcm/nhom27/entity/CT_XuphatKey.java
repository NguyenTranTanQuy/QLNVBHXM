package ptithcm.nhom27.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class CT_XuphatKey implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "IDMD")
	private int mucdo;
	
	@Column(name = "MANV")
	private String manv;
	
	@Column(name = "THOIGIANXUPHAT")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date thoigianxuphat;

	public CT_XuphatKey(int mucdo, String manv, Date thoigianxuphat) {
		super();
		this.mucdo = mucdo;
		this.manv = manv;
		this.thoigianxuphat = thoigianxuphat;
	}

	public CT_XuphatKey() {
		super();
	}

	public int getMucdo() {
		return mucdo;
	}

	public void setMucdo(int mucdo) {
		this.mucdo = mucdo;
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public Date getThoigianxuphat() {
		return thoigianxuphat;
	}

	public void setThoigianxuphat(Date thoigianxuphat) {
		this.thoigianxuphat = thoigianxuphat;
	}
	
	
}
