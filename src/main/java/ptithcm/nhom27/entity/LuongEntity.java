package ptithcm.nhom27.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "LUONG")
public class LuongEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BACLUONG")
	private int bacluong;
	
	@Column(name = "LUONGCOBAN")
	private int luongcoban;
	
	@OneToMany(mappedBy = "bacluongentity")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<CTLuongEntity> dsbacluong;

	@OneToMany(mappedBy = "luongentity")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<HDLDEntity> hdld;

	public LuongEntity(int bacluong, int luongcoban, Collection<CTLuongEntity> dsbacluong,
			Collection<HDLDEntity> hdld) {
		super();
		this.bacluong = bacluong;
		this.luongcoban = luongcoban;
		this.dsbacluong = dsbacluong;
		this.hdld = hdld;
	}

	public LuongEntity() {
		super();
	}

	public int getBacluong() {
		return bacluong;
	}

	public void setBacluong(int bacluong) {
		this.bacluong = bacluong;
	}

	public int getLuongcoban() {
		return luongcoban;
	}

	public void setLuongcoban(int luongcoban) {
		this.luongcoban = luongcoban;
	}

	public Collection<HDLDEntity> getHdld() {
		return hdld;
	}

	public void setHdld(Collection<HDLDEntity> hdld) {
		this.hdld = hdld;
	}

	public Collection<CTLuongEntity> getDsbacluong() {
		return dsbacluong;
	}

	public void setDsbacluong(Collection<CTLuongEntity> dsbacluong) {
		this.dsbacluong = dsbacluong;
	}
}
