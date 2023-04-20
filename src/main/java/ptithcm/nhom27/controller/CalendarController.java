package ptithcm.nhom27.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.nhom27.entity.CT_LLVEntity;
import ptithcm.nhom27.entity.LichLamViecEntity;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.services.CT_LLVService;
import ptithcm.nhom27.services.LichLamViecService;
import ptithcm.nhom27.services.NhanVienService;

@Controller
public class CalendarController {

	@Autowired
	LichLamViecService llvService;

	@Autowired
	CT_LLVService ctllService;

	@Autowired
	NhanVienService nvService;

	// Lưu trữ lịch làm việc của nhân viên trong ngày bạn chọn	
	private List<LichLamViecEntity> llv;

	// Lưu trữ lịch làm việc nhân việc	
	private LichLamViecEntity llvchon;

	private Date date;

	@RequestMapping("admin/calendar")
	public String showCalendar() {
		return "admin/calendar";
	}

	@RequestMapping("admin/addworking")
	public String addWorking(@RequestParam("date") String dateString, ModelMap model) {
		LocalDate currentDate = LocalDate.now();
		LocalDate anyDate = LocalDate.parse(dateString);

		Date date = Date.valueOf(dateString);
		this.date = date;
		List<LichLamViecEntity> llv = llvService.getLLVPBofDate(date);
		if (llv != null) {
			this.llv = llv;
		}

		model.addAttribute("llvs", llv);
		model.addAttribute("dateNow", dateString);
		model.addAttribute("disabled", currentDate.compareTo(anyDate));
		model.addAttribute("success", "");
		
		return "admin/calendar/modal";
	}
	
	@RequestMapping("employee/addworking")
	public String addWorking_(@RequestParam("date") String dateString, ModelMap model) {

		Date date = Date.valueOf(dateString);

		List<LichLamViecEntity> llv = llvService.getLLVPBofDate(date);
		if (llv != null) {
			this.llv = llv;
		}

		model.addAttribute("llvs", llv);

		return "employee/calendar/modal";
	}
	

	@RequestMapping(value = "admin/addworking", method = RequestMethod.POST)
	public String addWorkingTime(ModelMap model, @RequestParam("giovao") String s_giovao, @RequestParam("giora") String s_giora) {
		model.addAttribute("llvs", llv);
		
		model.addAttribute("dateNow", date.toString());
		if (s_giora.isEmpty() || s_giovao.isEmpty()) {
			model.addAttribute("success", "Giờ vào hoặc giờ ra không được rỗng");
			return "admin/calendar/modal";
		}
		Time giovao = Time.valueOf(s_giovao + ":00");
		Time giora = Time.valueOf(s_giora + ":00");

		LichLamViecEntity llvchon = llv.stream().filter(o -> o.getGiobatdau().toString().equals(giovao.toString())
				&& o.getGioketthuc().toString().equals(giora.toString())).findFirst().orElse(null);
		
		if (llvchon != null) {
			model.addAttribute("success", "Lịch làm việc đã tồn tại");
			return "admin/calendar/modal";
		}
		
		if (giovao.after(giora)) {
			model.addAttribute("success", "Giờ vào phải nhỏ hơn giờ ra");
			return "admin/calendar/modal";
		}
					
		model.addAttribute("success", "Thêm ca làm việc thành công!");
		
		LichLamViecEntity llv = new LichLamViecEntity();
		llv.setNgaylam(date);
		llv.setManvql(nvService.getNVLogin());
		llv.setGiobatdau(giovao);
		llv.setGioketthuc(giora);
		llvService.addoreditLichLamViec(llv);
		
		// Nhận llv đã thêm		
		List<LichLamViecEntity> llv_ = llvService.getLLVPBofDate(date);
		model.addAttribute("llvs", llv_);
		this.llv = llv_;
		
		return "admin/calendar/modal";
	}

	@RequestMapping("admin/timeworking")
	public String timeWorking(ModelMap model, @RequestParam("fromTime") String fromTime,
			@RequestParam("toTime") String toTime) {
		Time t1 = Time.valueOf(fromTime);
		Time t2 = Time.valueOf(toTime);

		Map<String, Boolean> map = new HashMap<>();
		Map<String, Boolean> maps = new HashMap<>();

		LichLamViecEntity llvchon = llv.stream().filter(o -> o.getGiobatdau().toString().equals(t1.toString())
				&& o.getGioketthuc().toString().equals(t2.toString())).findFirst().orElse(null);
		List<CT_LLVEntity> ctllv = llvchon.getDsllv().stream().collect(Collectors.toList());
		List<NhanVienEntity> lnv = nvService.getAllNhanVienOfPb();
		lnv.forEach(o -> {
			CT_LLVEntity ct = ctllv.stream().filter(cts -> cts.getIdctllv().getManv().equals(o.getManv())).findFirst().orElse(null);
			if (ct == null)
				map.put(o.getManv(), false);
			else
				map.put(o.getManv(), true);
			maps.put(o.getManv(), llvService.nvCoTheNhanLichLamViec(llvchon, o));
		});

		this.llvchon = llvchon;

		model.addAttribute("map", map);
		model.addAttribute("lnv", lnv);
		model.addAttribute("maps", maps);
		
		return "admin/calendar/listEmployeeOfTime";
	}
	
	@RequestMapping("employee/timeworking")
	public String timeWorking_(ModelMap model, @RequestParam("fromTime") String fromTime,
			@RequestParam("toTime") String toTime) {
		Time t1 = Time.valueOf(fromTime);
		Time t2 = Time.valueOf(toTime);

		Map<String, Boolean> map = new HashMap<>();

		LichLamViecEntity llvchon = llv.stream().filter(o -> o.getGiobatdau().toString().equals(t1.toString())
				&& o.getGioketthuc().toString().equals(t2.toString())).findFirst().orElse(null);
		List<CT_LLVEntity> ctllv = llvchon.getDsllv().stream().collect(Collectors.toList());
		List<NhanVienEntity> lnv = nvService.getAllNhanVienOfPb();
		lnv.forEach(o -> {
			CT_LLVEntity ct = ctllv.stream().filter(cts -> cts.getIdctllv().getManv().equals(o.getManv())).findFirst().orElse(null);
			if (ct == null)
				map.put(o.getManv(), false);
			else
				map.put(o.getManv(), true);
		});

		this.llvchon = llvchon;

		model.addAttribute("map", map);
		model.addAttribute("lnv", lnv);
		
		return "employee/calendar/listEmployeeOfTime";
	}

	@RequestMapping(value = "admin/add_employee_working")
	public String addEmployeeWorking(ModelMap model, @RequestParam("lstEmployee") String lstEmployee) {
		String[] arrEmployee = lstEmployee.split("-");
		ctllService.deleteCTLLV(llvchon);
		
		for (int i = 0; i < arrEmployee.length; i++) {
			NhanVienEntity nv = nvService.getNhanVien(arrEmployee[i]);
			ctllService.addoreditLLV(llvchon, nv);
		}

		return "admin/calendar";
	}
	
}
