package ptithcm.nhom27.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.nhom27.entity.CT_LLVEntity;
import ptithcm.nhom27.entity.ChamCongEntity;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.services.CT_LLVService;
import ptithcm.nhom27.services.ChamCongService;
import ptithcm.nhom27.services.LichLamViecService;
import ptithcm.nhom27.services.NhanVienService;

@Controller
public class TimekeepingController {

	@Autowired
	LichLamViecService llvService;

	@Autowired
	ChamCongService ccService;

	@Autowired
	NhanVienService nvService;

	@Autowired
	CT_LLVService ctllvService;

	@RequestMapping(value = { "admin/timekeeping", "employee/timekeeping" })
	public String showTimekeeping() {
		return "timekeeping";
	}

	@RequestMapping("admin/checkin")
	public String checkin(ModelMap model) {
		NhanVienEntity nv = nvService.getNVLogin();
		List<CT_LLVEntity> ctllv = llvService.getCTLLVnow();
		long milis = System.currentTimeMillis();
		Date checkindate = new Date(milis);
		Time checkintime = new Time(milis);
		Time clockcheckintime = Time.valueOf(checkintime.toString());
		if (ctllv.size() == 0) {
			model.addAttribute("status", "Không khả dụng");
		} else {
			for (int i = 0; i < ctllv.size(); i++) {
				ChamCongEntity cc = ccService.getCCtheoLLV(ctllv.get(i).getId(), nv.getManv());
				if (cc == null && ctllv.get(i).getId().getGioketthuc().after(clockcheckintime)) {
					ccService.nvCheckin(clockcheckintime, ctllv.get(i).getId(), nv);
					break;
				}
			}
		}
		model.addAttribute("checkin", checkindate.toString() + " " + checkintime.toString());
		return "redirect:/admin/timekeeping";
	}

	@RequestMapping(value = "admin/checkout")
	public String checkout(ModelMap model) {
		NhanVienEntity nv = nvService.getNVLogin();
		long milis = System.currentTimeMillis();
		Time timenow = new Time(milis);
		Time checkout = Time.valueOf(timenow.toString());
		ccService.nvCheckout(checkout, nv.getManv());
		model.addAttribute("checkout", checkout);
		return "redirect:/admin/timekeeping";
	}

	@ModelAttribute("checkin")
	public String getcheckin() {
		NhanVienEntity nv = nvService.getNVLogin();
		ChamCongEntity ccCheckin = ccService.getCheckin(nv.getManv());
		
//		long milis = System.currentTimeMillis();
//		Time checkintime = new Time(milis);

		if (ccCheckin != null)
			return ccCheckin.getNgaycc().toString() + " " + ccCheckin.getGiovao().toString();
		return "";
	}

	@ModelAttribute("status")
	public String getstatus() {
		NhanVienEntity nv = nvService.getNVLogin();
		ChamCongEntity ccCheckin = ccService.getCheckin(nv.getManv());
		
//		long milis = System.currentTimeMillis();
//		Time checkintime = new Time(milis);
//		Time clockcheckintime = Time.valueOf(checkintime.toString());
		
		if (ccCheckin != null)
			return ccCheckin.getTrangthai();
		return "";
	}

	@ModelAttribute("vaoca")
	public String giovaoca() {
		List<CT_LLVEntity> ctllv = llvService.getCTLLVnow();
		NhanVienEntity nv = nvService.getNVLogin();
		if (ctllv.size() == 0)
			return "Không có ca làm việc có thể chấm công";
		long milis = System.currentTimeMillis();
		Time checkintime = new Time(milis);
		Time lockcheckintime = Time.valueOf(checkintime.toString());
		for (int i = 0; i < ctllv.size(); i++) {
			ChamCongEntity cc = ccService.getCCtheoLLV(ctllv.get(i).getId(), nv.getManv());
			if (cc == null || cc.getGiora() == null) {
				LocalTime tnow = lockcheckintime.toLocalTime();
				LocalTime tstart = ctllv.get(i).getId().getGiobatdau().toLocalTime();
				if (tstart.getHour() - tnow.getHour() > 1) {
					return "Không có ca làm việc có thể chấm công";
				}
				if (ctllv.get(i).getId().getGioketthuc().after(lockcheckintime))
					return ctllv.get(i).getId().getNgaylam() + " " + ctllv.get(i).getId().getGiobatdau();
			}
		}
		return "Không có ca làm việc có thể chấm công";
	}

	@ModelAttribute("tanca")
	public String giotanca() {
		List<CT_LLVEntity> ctllv = llvService.getCTLLVnow();
		NhanVienEntity nv = nvService.getNVLogin();
		if (ctllv.size() == 0)
			return "Không có ca làm việc có thể chấm công";
		long milis = System.currentTimeMillis();
		Time checkintime = new Time(milis);
		Time lockcheckintime = Time.valueOf(checkintime.toString());
		for (int i = 0; i < ctllv.size(); i++) {
			ChamCongEntity cc = ccService.getCCtheoLLV(ctllv.get(i).getId(), nv.getManv());
			if (cc == null || cc.getGiora() == null) {
				LocalTime tnow = lockcheckintime.toLocalTime();
				LocalTime tstart = ctllv.get(i).getId().getGiobatdau().toLocalTime();
				if (tstart.getHour() - tnow.getHour() > 1)
					return "Không có ca làm việc có thể chấm công";
				if (ctllv.get(i).getId().getGioketthuc().after(lockcheckintime))
					return ctllv.get(i).getId().getNgaylam() + " " + ctllv.get(i).getId().getGioketthuc();
			}
		}
		return "Không có ca làm việc có thể chấm công";
	}
}
