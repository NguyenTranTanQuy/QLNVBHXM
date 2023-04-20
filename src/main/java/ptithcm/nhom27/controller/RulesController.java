package ptithcm.nhom27.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.PhongBanEntity;
import ptithcm.nhom27.entity.QuyDinhEntity;
import ptithcm.nhom27.services.NhanVienService;
import ptithcm.nhom27.services.QuyDinhService;

@Controller
public class RulesController {
	
	@Autowired
	private QuyDinhService qdService;
	
	@Autowired
	private NhanVienService nvService;
	
	@RequestMapping("admin/rules")
	public String showRules(ModelMap model) {
		return "admin/rules";
	}
	
	@RequestMapping("employee/rules")
	public String showRules_() {
		return "employee/rules";
	}
	
	@ModelAttribute("qds")
	public List<QuyDinhEntity> listQD(){
		PhongBanEntity pb = nvService.getNVLogin().getMapb();
		return qdService.getAllQDPB(pb);
	}
	
	@RequestMapping("admin/addrule")
	public String addRule(ModelMap model,@RequestParam("name") String name, @RequestParam("description") String description) {
		QuyDinhEntity qd = new QuyDinhEntity();
		NhanVienEntity nv = nvService.getNVLogin();
		qd.setManvql(nv);
		qd.setTenqd(name);
		qd.setMota(description);
		qdService.addQuyDinh(qd);

		return "redirect:/admin/rules";
	}
}
