package ptithcm.nhom27.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.ThongBaoEntity;
import ptithcm.nhom27.services.CT_TBService;
import ptithcm.nhom27.services.NhanVienService;
import ptithcm.nhom27.services.ThongBaoService;

@Controller
public class NotificationController {
	@Autowired
	NhanVienService nvService;
	
	@Autowired
	ThongBaoService tbService;
	
	@Autowired
	CT_TBService cttbService;
	
	@RequestMapping("notification")
	public String createNotification(ModelMap model) {
		model.addAttribute("success", "");
		return "notification";
	}
	
	@RequestMapping(value="send-notification", method=RequestMethod.POST)
	public String sendNotification(ModelMap model, @RequestParam("receiver") String receiver, @RequestParam("content") String content) {
		ThongBaoEntity tb = new ThongBaoEntity();
		NhanVienEntity nv = nvService.getNVLogin();
		NhanVienEntity nvReceiver = nvService.getNhanVien(receiver)
;		
		long miliseconds = System.currentTimeMillis();
		Date now = new Date(miliseconds);
		
		tb.setManvql(nv);
		tb.setNgaytb(now);
		tb.setNoidung(content);
		
		tbService.addThongBao(tb);
		
		if(receiver.equals("1")) {
			cttbService.autoAddAllNV(getListEmployeeOfPB(), tb);
		}
		else {
			cttbService.addOneNV(nvReceiver, tb);
		}
		
		model.addAttribute("success", "Tạo thông báo thành công");
		
		return "notification";
	}
	
	@ModelAttribute("nameOffice")
	public String getOffice() {
		NhanVienEntity nv = nvService.getNVLogin();
		return nv.getMapb().getTenpb();
	}
	
	@ModelAttribute("listEmployee")
	public List<NhanVienEntity> getListEmployeeOfPB() {
		List<NhanVienEntity> lstEmployee = nvService.getAllNhanVienOfPb();
		return lstEmployee;
	}
}
