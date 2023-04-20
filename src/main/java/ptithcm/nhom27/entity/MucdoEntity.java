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
@Table(name = "MUCDO")
public class MucdoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "TENMUCDO")
	private String tenmucdo;
	
	
	@Column(name = "TIENPHAT")
	private int tienphat;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDQD")
	private QuyDinhEntity idqd;
	
	@OneToMany(mappedBy = "idmd" ,fetch = FetchType.EAGER)
	private List<CT_XuphatEntity> ctxp;

	public MucdoEntity(int id, String tenmucdo, int tienphat, QuyDinhEntity idqd, List<CT_XuphatEntity> ctxp) {
		super();
		this.id = id;
		this.tenmucdo = tenmucdo;
		this.tienphat = tienphat;
		this.idqd = idqd;
		this.ctxp = ctxp;
	}

	public MucdoEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenmucdo() {
		return tenmucdo;
	}

	public void setTenmucdo(String tenmucdo) {
		this.tenmucdo = tenmucdo;
	}

	public int getTienphat() {
		return tienphat;
	}

	public void setTienphat(int tienphat) {
		this.tienphat = tienphat;
	}

	public QuyDinhEntity getIdqd() {
		return idqd;
	}

	public void setIdqd(QuyDinhEntity idqd) {
		this.idqd = idqd;
	}

	public List<CT_XuphatEntity> getCtxp() {
		return ctxp;
	}

	public void setCtxp(List<CT_XuphatEntity> ctxp) {
		this.ctxp = ctxp;
	}

	
	
	
}
