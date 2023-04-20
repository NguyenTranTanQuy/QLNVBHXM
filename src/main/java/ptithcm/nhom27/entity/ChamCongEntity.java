package ptithcm.nhom27.entity;

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
import javax.persistence.Table;


@Entity
@Table(name = "CHAMCONG")
public class ChamCongEntity {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "NGAYCHAMCONG")
	private Date ngaycc;
	
	@Column(name = "GIORA")
	private Time giora;
	
	@Column(name = "GIOVAO")
	private Time giovao;
	
	@Column(name = "TRANGTHAI")
	private String trangthai;
	
	@ManyToOne
	@JoinColumn(name = "IDLLV")
	private LichLamViecEntity idcm;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANV")
	private NhanVienEntity manv;
	
	public NhanVienEntity getManv() {
		return manv;
	}

	public void setManv(NhanVienEntity manv) {
		this.manv = manv;
	}

	public ChamCongEntity(int id, Date ngaycm, Time giora, Time giovao, String trangthai, LichLamViecEntity idcm,
			NhanVienEntity manv) {
		super();
		this.id = id;
		this.ngaycc = ngaycm;
		this.giora = giora;
		this.giovao = giovao;
		this.trangthai = trangthai;
		this.idcm = idcm;
		this.manv = manv;
	}

	public ChamCongEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getNgaycc() {
		return ngaycc;
	}

	public void setNgaycc(Date ngaycc) {
		this.ngaycc = ngaycc;
	}

	public Time getGiora() {
		return giora;
	}

	public void setGiora(Time giora) {
		this.giora = giora;
	}

	public Time getGiovao() {
		return giovao;
	}

	public void setGiovao(Time giovao) {
		this.giovao = giovao;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public LichLamViecEntity getIdcm() {
		return idcm;
	}

	public void setIdcm(LichLamViecEntity idcm) {
		this.idcm = idcm;
	}
	
	
}
