package ptithcm.nhom27.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.nhom27.entity.CTLuongEntity;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.services.CTLuongService;
import ptithcm.nhom27.services.CT_XuphatService;
import ptithcm.nhom27.services.ChamCongService;
import ptithcm.nhom27.services.NhanVienService;

@Controller
public class SalaryController {
	@Autowired
	private ChamCongService ccService; 
	
	@Autowired
	private CTLuongService ctluongService;
	
	@Autowired
	private NhanVienService nvService;
	
	@Autowired
	private CT_XuphatService xpService;
	
	@RequestMapping("admin/salary")
	public String caculatorSalary() {
		return "admin/salary";
	}
	
	@RequestMapping("admin/list-salary")
	public String showListSalary() {
		return "admin/listSalary";
	}
	
	@RequestMapping("employee/list-salary")
	public String showListSalary_() {
		return "employee/listSalary";
	}
	
	@RequestMapping("admin/loadDataSalary")
	public String getDataSalary(ModelMap model, @RequestParam("manv") String manv) {
		int month = getMonth();
		int year = getYear();
		NhanVienEntity nv = nvService.getNhanVien(manv);
		
		double tonggiolam = ccService.tongSoCanGioLam(nv, month, year);
		double sogioditre = ccService.soGioDiTreThang(nv, month, year);
		double sogiovesom = ccService.soGioVeSomThang(nv, month, year);
		double sogiokhongdilam = ccService.soGioKhongDiLamThang(nv, month, year);
		
		double phantramthoigianlamviec = ccService.phanTramDiLam(nv, month, year);
		
		model.addAttribute("totalTime", tonggiolam);
		model.addAttribute("timeLate", sogioditre);
		model.addAttribute("timeEarly", sogiovesom);
		model.addAttribute("timeNotWorking", sogiokhongdilam);
		model.addAttribute("percentageTime", phantramthoigianlamviec);
		
		return "admin/salary";
	}
	
	@ModelAttribute("listEmployee")
	public List<NhanVienEntity> getListEmployee() {
		List<NhanVienEntity> lstEmployee = nvService.getAllNhanVienOfPb();
		return lstEmployee;
	}
	
	@ModelAttribute("month")
	public int getMonth() {
		LocalDate date = LocalDate.now();
		date = date.minusMonths(1);
		return date.getMonthValue();
	}
	
	@ModelAttribute("year")
	public int getYear() {
		LocalDate date = LocalDate.now();
		date = date.minusMonths(1);
		return date.getYear();
	}
	
	@ModelAttribute("listSalary")
	public List<CTLuongEntity> lctluong(ModelMap model) {
		NhanVienEntity nv = nvService.getNVLogin();
		Map<String, Integer> tienphat = new HashMap<>();
		List<CTLuongEntity> lctluong = ctluongService.getAllCTluongPB(nv.getMapb().getMapb());
		lctluong.forEach(o->{
			int sotienphat = xpService.getTongTienPhatThang(o.getIdctluong().getThang(), o.getIdctluong().getNam(), nv);
			tienphat.put(o.getManvluong().getManv(), sotienphat);
		});
		model.addAttribute("tienphat", tienphat);
		return lctluong;
	}
	
	@ModelAttribute("listSalaryPersonal")
	public List<CTLuongEntity> lctluong_(ModelMap model) {
		NhanVienEntity nv = nvService.getNVLogin();
		Map<String, Integer> tienphat = new HashMap<>();
		List<CTLuongEntity> lctluong = ctluongService.getAllCTluongMANV(nv.getManv());
		lctluong.forEach(o->{
			int sotienphat = xpService.getTongTienPhatThang(o.getIdctluong().getThang(), o.getIdctluong().getNam(), nv);
			tienphat.put(o.getManvluong().getManv(), sotienphat);
		});
		model.addAttribute("tienphat", tienphat);
		return lctluong;
	}
}
