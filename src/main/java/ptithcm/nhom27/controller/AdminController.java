package ptithcm.nhom27.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.nhom27.entity.ChamCongEntity;
import ptithcm.nhom27.entity.HDLDEntity;
import ptithcm.nhom27.entity.LuongEntity;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.PhongBanEntity;
import ptithcm.nhom27.entity.TaiKhoanEntity;
import ptithcm.nhom27.services.ChamCongService;
import ptithcm.nhom27.services.HDLDService;
import ptithcm.nhom27.services.LuongService;
import ptithcm.nhom27.services.NhanVienService;
import ptithcm.nhom27.services.PhongBanService;
import ptithcm.nhom27.services.TaiKhoanService;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	private NhanVienService nvserService;

	@Autowired
	private PhongBanService pbService;

	@Autowired
	private HDLDService hdldService;

	@Autowired
	private TaiKhoanService tkService;
	
	@Autowired
	private ChamCongService ccService;
	
	@Autowired
	private LuongService luongService;

	// Trả về giao diện: Thêm nhân viên
	@RequestMapping("add-employee")
	public String getAddEmployee(ModelMap model) {
		model.addAttribute("success", "");
		return "admin/addEmployee";
	}
	
	// Xử lý khi thêm nhân viên
	@RequestMapping(value = "add-employee", method = RequestMethod.POST)
	public String submitForm(ModelMap model, @RequestParam("avatar") String avatar, @RequestParam("manv") String manv,
			@RequestParam("cmnd") String cmnd, @RequestParam("ho") String ho, @RequestParam("ten") String ten,
			@RequestParam("ngaysinh") String ngaysinh, @RequestParam("gioitinh") String gioitinh,
			@RequestParam("diachi") String diachi, @RequestParam("sdt") String sdt, @RequestParam("email") String email,
			@RequestParam("mahopdong") String mahopdong, @RequestParam("ngaybatdauhd") String ngaybatdauhd,
			@RequestParam("ngayketthuchd") String ngayketthuchd, @RequestParam("bacluong") String bacluong) {
		// Code xử lý CSDL ở đây
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date datens = format.parse(ngaysinh);
			Date datenbdhd = format.parse(ngaybatdauhd);
			Date datenkthd = format.parse(ngayketthuchd);
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
			nv.setMapb(nvserService.getPhongBan());
			HDLDEntity hdld = new HDLDEntity();
			hdld.setMahdld(mahopdong);
			hdld.setNgaybd(datenbdhd);
			hdld.setNgaykt(datenkthd);
			hdld.setManv(nv);
			
			LuongEntity luong = luongService.getBacLuong(Integer.parseInt(bacluong));
			hdld.setLuongentity(luong);

			if (nvserService.addNhanVien(nv) && hdldService.addHDLD(hdld) && tkService.autoAddTaiKhoan(nv)) {
				model.addAttribute("manv", nvserService.Createmanv());
				model.addAttribute("mahdld", hdldService.createHDLD());
				model.addAttribute("success", "Thêm nhân viên thành công!");
			} else {
				model.addAttribute("success", "Có vấn đề liên quan đến cơ sở dữ liệu");
			}
		} catch (Exception e) {
			model.addAttribute("success", "Có vấn đề liên quan đến cơ sở dữ liệu");
		}

		return "admin/addEmployee";
	}

	// Trả về giao diện: Danh sách nhân viên.	
	@RequestMapping("list-employee")
	public String getListEmployee(ModelMap model) {
		model.addAttribute("");
		return "admin/listEmployee";
	}
	
	// Xử lý khi sửa nhân viên.	
	@RequestMapping("edit")
	public String editEmployee(ModelMap model, @RequestParam("manv") String manv, @RequestParam("cmnd") String cmnd,
			@RequestParam("ho") String ho, @RequestParam("ten") String ten, @RequestParam("ngaysinh") String ngaysinh,
			@RequestParam("gioitinh") String gioitinh, @RequestParam("diachi") String diachi,
			@RequestParam("sdt") String sdt, @RequestParam("mapb") String mapb, @RequestParam("email") String email) {

//		model.addAttribute("avatar", null);
		model.addAttribute("manv", manv);
		model.addAttribute("cmnd", cmnd);
		model.addAttribute("ho", ho);
		model.addAttribute("ten", ten);
		model.addAttribute("ngaysinh", ngaysinh);
		model.addAttribute("gioitinh", gioitinh);
		model.addAttribute("diachi", diachi);
		model.addAttribute("sdt", sdt);
		model.addAttribute("email", email);
		model.addAttribute("mapb", mapb);
		model.addAttribute("display", "flex");
		model.addAttribute("disabled", "on");
		return "admin/listEmployee";
	}
	
	// Xử lý khi xác nhận sửa nhân viên
	@RequestMapping(value = "confirm_edit", method = RequestMethod.POST)
	public String confirmEditEmployee(ModelMap model, @RequestParam("manv") String manv,
			@RequestParam("cmnd") String cmnd, @RequestParam("ho") String ho, @RequestParam("ten") String ten,
			@RequestParam("ngaysinh") String ngaysinh, @RequestParam("gioitinh") String gioitinh,
			@RequestParam("diachi") String diachi, @RequestParam("sdt") String sdt, @RequestParam("phongban") String mapb, @RequestParam("email") String email) {
		
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
			nv.setMapb(pbService.getPhongBan(mapb));
			HDLDEntity hdld = new HDLDEntity();
			hdld.setManv(nv);

			if (nvserService.addNhanVien(nv) && hdldService.addHDLD(hdld)) {
				model.addAttribute("success", "Bạn đã sửa thành công!");
			} else {
				model.addAttribute("success", "Có lỗi xảy ra ở CSDL");
			}
		} catch (Exception e) {
			model.addAttribute("success", "Có lỗi xảy ra ở CSDL");
		}

		return "redirect:/admin/list-employee";
	}
	
	// Trả về giao diện: Danh sách tài khoản nhân viên.
	@RequestMapping("list-account")
	public String showListAccount() {
		return "admin/listAccount";
	}
	
	// Trả về giao diện: Bảng chấm công.	
	@RequestMapping("table-timekeeping")
	public String showTableTimekeeping() {
		return "admin/listTimekeeping";
	}
	
	// Xử lý khi nhấn resetpassword của nhân viên nào đó.
	@RequestMapping("resetPassword")
	public String resetPassword(@RequestParam("manv") String manv) {
		if (manv.isEmpty()) return "/404";
		TaiKhoanEntity tk = tkService.getTaiKhoan(manv);
		if (tkService.resetPassword(tk))
			return "redirect:/admin/list-account";
		else
			return "/404";
	}

	// Xử lý khi nhấn change status của nhân viên nào đó
	@RequestMapping("change-status")
	public String changeStatus(@RequestParam("manv") String manv, @RequestParam("status") String status) {
		TaiKhoanEntity tk = tkService.getTaiKhoan(manv);
		if (status.equals("true")) {
			if(!tkService.changeStatus(tk, false)) return "/404";
		}
		else
		if (status.equals("false")) {
			if (!tkService.changeStatus(tk, true)) return "/404";
		}
		return "redirect:/admin/list-account";
	}
	
	// Lấy ra dữ liệu trong CSDL đổ ra giao diện
	@ModelAttribute("employees")
	public List<NhanVienEntity> fillTableEmployee() {
		List<NhanVienEntity> listEmployee = nvserService.getAllNhanVienOfPb();
		return listEmployee;
	}
	
	@ModelAttribute("dstk")
	public List<TaiKhoanEntity> getTaiKhoans() {
		return tkService.getTaiKhoanPB();
	}

	@ModelAttribute("listOffice")
	public List<PhongBanEntity> getListOffice() {
		List<PhongBanEntity> listOffice = pbService.getListOffice();
		return listOffice;
	}
	
	@ModelAttribute("listSalary")
	public List<LuongEntity> getListSalary() {
		List<LuongEntity> listSalary = luongService.getAllBacluong();
		return listSalary;
	}
	
	@ModelAttribute("listTimekeeping")
	public List<ChamCongEntity> getListTimekeeping() {
		List<ChamCongEntity> listTimekeeping = ccService.getAllChamCongPB();
		return listTimekeeping;
	}

	@ModelAttribute("manv")
	public String countNhanVien() {
		return nvserService.Createmanv();
	}
	
	@ModelAttribute("mahdld")
	public String countHDLD() {
		return hdldService.createHDLD();
	}
}
