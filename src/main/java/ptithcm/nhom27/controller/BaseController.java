package ptithcm.nhom27.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.ThongBaoEntity;
import ptithcm.nhom27.services.CT_TBService;
import ptithcm.nhom27.services.ChamCongService;
import ptithcm.nhom27.services.NhanVienService;
import ptithcm.nhom27.services.PhongBanService;
import ptithcm.nhom27.services.ThongBaoService;

@Controller
public class BaseController {
	@Autowired
	NhanVienService nvService;
	
	@Autowired
	ChamCongService ccService;
	
	@Autowired
	ThongBaoService tbService;
	
	@Autowired
	CT_TBService cttbService;
	
	@Autowired
	PhongBanService pbService;
	
	protected static boolean keepAlive = false;
	protected static String tagName = "empty";
	
	@RequestMapping("/")
	String showLogin() {
		return "login";
	}
	
	@RequestMapping(value = "/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {
		ccService.autoCheckChamCong();
		if (keepAlive) {
			if (tagName.equals("admin"))
				return "redirect:/admin/home";
			else
				return "redirect:/employee/home";
		}

		if (error != null) {
			model.addAttribute("message", "Tài khoản hoặc mật khẩu không chính xác!");
		}

		return "login";
	}
	
	@RequestMapping(value = "/check")
	public String checkauth() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth!=null)
		{
			if (auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_A")))
				return "redirect:/admin";	
			else
			if (auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_E")))
				return "redirect:/employee";
			else
				return "redirect:/404";
		}
		return "redirect:/404";
	}
	
	@RequestMapping("/admin")
	public String admin(ModelMap model) {
		keepAlive = true;
		tagName = "admin";
		
		String username = nvService.getNameUser();
		model.addAttribute("user_header", username);
		
		String office = nvService.getPhongBanUser();
		model.addAttribute("user_office", office);
		
		NhanVienEntity nv = nvService.getNVLogin();
		LocalDate date = LocalDate.now();
		
		int thanght = date.getMonthValue();
		int namht = date.getYear();
		
		model.addAttribute("nv", nv);
		model.addAttribute("slnvpb", pbService.slNVconlam(nv.getMapb().getMapb()));
		model.addAttribute("ngaylam", ccService.soNgayLam(nv, thanght, namht));
		model.addAttribute("ditre", ccService.soCaDiTreThang(nv, thanght, namht) + ccService.sCaDiTreVesomThang(nv, thanght, namht));
		model.addAttribute("vesom", ccService.soCaVesomThang(nv, thanght, namht) + ccService.sCaDiTreVesomThang(nv, thanght, namht));
		
		List<ThongBaoEntity> ltb = cttbService.getAllTBMaNV(nvService.getNVLogin().getManv());
		Collections.reverse(ltb);
		model.addAttribute("tbs", ltb);
		
		return "admin";
	}
	
	@RequestMapping("/employee")
	public String employee(ModelMap model) {
		keepAlive = true;
		tagName = "employee";
		
		String username = nvService.getNameUser();
		model.addAttribute("user_header", username);
		
		String office = nvService.getPhongBanUser();
		model.addAttribute("user_office", office);
		
		NhanVienEntity nv = nvService.getNVLogin();
		LocalDate date = LocalDate.now();
		int thanght = date.getMonthValue();
		int namht = date.getYear();
		
		model.addAttribute("nv", nv);
		model.addAttribute("slnvpb", pbService.slNVconlam(nv.getMapb().getMapb()));
		model.addAttribute("ngaylam", ccService.soNgayLam(nv, thanght, namht));
		model.addAttribute("ditre", ccService.soCaDiTreThang(nv, thanght, namht) + ccService.sCaDiTreVesomThang(nv, thanght, namht));
		model.addAttribute("vesom", ccService.soCaVesomThang(nv, thanght, namht) + ccService.sCaDiTreVesomThang(nv, thanght, namht));
		
		List<ThongBaoEntity> ltb = cttbService.getAllTBMaNV(nvService.getNVLogin().getManv());
		Collections.reverse(ltb);
		model.addAttribute("tbs", ltb);
		
		return "employee";
	}

	@RequestMapping("/logout")
	public String logout(final Model model, HttpServletRequest request, HttpServletResponse response) {
//		model.addAttribute("message", "Logged out!");
		keepAlive = false;
		tagName = "empty";
		return "redirect:/login";
	}

	@RequestMapping("/403")
	public String showError(Model model, Principal principal) {
		model.addAttribute("message", "Truy cập bị từ chối " + principal.getName());
		return "403Page";
	}
	
	
}
