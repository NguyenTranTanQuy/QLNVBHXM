package ptithcm.nhom27.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.nhom27.entity.ChamCongEntity;
import ptithcm.nhom27.services.ChamCongService;

@Controller
@RequestMapping("/employee/")
public class EmployeeController {
	@Autowired
	ChamCongService ccService;
	
	@RequestMapping("table-timekeeping")
	public String showTableTimekeepingPersonal() {
		return "employee/listTimekeeping";
	}
	
	@RequestMapping("calendar")
	public String showCalendarWorking() {
		return "employee/calendar";
	}
	
	@ModelAttribute("listTimekeepingPersonal")
	public List<ChamCongEntity> listTimekeepingPersonal() {
		List<ChamCongEntity> list = ccService.getAllChamCongPersonal();
		return list;
	}
}
