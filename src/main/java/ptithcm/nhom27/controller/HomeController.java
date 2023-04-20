package ptithcm.nhom27.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.nhom27.entity.HDLDEntity;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.PhongBanEntity;
import ptithcm.nhom27.services.ChamCongService;
import ptithcm.nhom27.services.HDLDService;
import ptithcm.nhom27.services.NhanVienService;
import ptithcm.nhom27.services.PhongBanService;

@Controller
public class HomeController {

	
	@Autowired
	private NhanVienService nvService;
	
	@Autowired
	private PhongBanService pbService;
	
	@Autowired
	private ChamCongService ccService;
	
	@Autowired
	private HDLDService hdldService;
	
	@RequestMapping("home")
	public String showHome(ModelMap model) {
		model.addAttribute("display", "");
		return "home";
	}
	
	@RequestMapping("edit-personal")
	public String editPersonal(ModelMap model) {
		NhanVienEntity nv = getnv();
		model.addAttribute("manv", nv.getManv());
		model.addAttribute("cmnd", nv.getCmnd());
		model.addAttribute("ho", nv.getHo());
		model.addAttribute("ten", nv.getTen());
		model.addAttribute("ngaysinh", nv.getNgaysinh());
		model.addAttribute("gioitinh", nv.getGioitinh());
		model.addAttribute("diachi", nv.getDiachi());
		model.addAttribute("sdt", nv.getSdt());
		model.addAttribute("email", nv.getEmail());
		model.addAttribute("display", "flex");
		
		return "home";
	}
	
	@RequestMapping(value = "confirm_edit-personal", method = RequestMethod.POST)
	public String confirmEditEmployee(ModelMap model, @RequestParam("manv") String manv,
			@RequestParam("cmnd") String cmnd, @RequestParam("ho") String ho, @RequestParam("ten") String ten,
			@RequestParam("ngaysinh") String ngaysinh, @RequestParam("gioitinh") String gioitinh,
			@RequestParam("diachi") String diachi, @RequestParam("sdt") String sdt, 
			@RequestParam("email") String email) {
		
//		@RequestParam("avatar") String avatar
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date datens = format.parse(ngaysinh);
			NhanVienEntity nv = new NhanVienEntity();
			nv.setManv(manv);
			nv.setCmnd(cmnd);
			nv.setHo(ho);
			nv.setTen(ten);
			nv.setNgaysinh(datens);
			nv.setGioitinh(gioitinh.equals("male") ? "Nam" : "Nữ");
			nv.setDiachi(diachi);
			nv.setSdt(sdt);
			nv.setEmail(email);
			nv.setAnh(null);
			
			PhongBanEntity pb = nvService.getPhongBan();
			nv.setMapb(pb);
			HDLDEntity hdld = new HDLDEntity();
			hdld.setManv(nv);

			if (nvService.addNhanVien(nv) && hdldService.addHDLD(hdld)) {
				model.addAttribute("success", "Bạn đã sửa thành công!");
			} else {
				model.addAttribute("success", "Có lỗi xảy ra ở CSDL");
			}
		} catch (Exception e) {
			model.addAttribute("success", "Có lỗi xảy ra ở CSDL");
		}

		return "redirect:/home";
	}
	
	@ModelAttribute("slnvpb")
	public long soLuongNVPB() {
		NhanVienEntity nv = nvService.getNVLogin();
		return pbService.slNVconlam(nv.getMapb().getMapb());
	}
	
	@ModelAttribute("nv")
	public NhanVienEntity getnv() {
		NhanVienEntity nv = nvService.getNVLogin();
		return nv;
	}
	@ModelAttribute("ngaylam")
	public long soNgayLam() {
		NhanVienEntity nv = nvService.getNVLogin();
		LocalDate date = LocalDate.now();
		return ccService.soNgayLam(nv, date.getMonthValue(), date.getYear());
	}
	
	@ModelAttribute("ditre")
	public int soNgayDitre() {
		NhanVienEntity nv = nvService.getNVLogin();
		LocalDate date = LocalDate.now();
		return ccService.soCaDiTreThang(nv, date.getMonthValue(), date.getYear())+ccService.sCaDiTreVesomThang(nv, date.getMonthValue(), date.getYear());
	}
	
	@ModelAttribute("vesom")
	public int soNgayVesom() {
		NhanVienEntity nv = nvService.getNVLogin();
		LocalDate date = LocalDate.now();
		return ccService.soCaVesomThang(nv, date.getMonthValue(), date.getYear()) + ccService.sCaDiTreVesomThang(nv, date.getMonthValue(), date.getYear());
	}
}
