package ptithcm.nhom27.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.nhom27.entity.CT_XuphatEntity;
import ptithcm.nhom27.entity.CT_XuphatKey;
import ptithcm.nhom27.entity.MucdoEntity;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.QuyDinhEntity;
import ptithcm.nhom27.entity.ThongBaoEntity;
import ptithcm.nhom27.services.CT_TBService;
import ptithcm.nhom27.services.CT_XuphatService;
import ptithcm.nhom27.services.MucDoService;
import ptithcm.nhom27.services.NhanVienService;
import ptithcm.nhom27.services.QuyDinhService;
import ptithcm.nhom27.services.ThongBaoService;

@Controller
public class PurnishController {
	
	@Autowired
	private MucDoService mdService;
	
	@Autowired CT_XuphatService xpService;
	
	@Autowired
	private NhanVienService nvService;
	
	@Autowired
	private QuyDinhService qdService;
	
	@Autowired
	private ThongBaoService tbService;
	
	@Autowired
	private CT_TBService cttbService;
	
	@RequestMapping("admin/list-purnish")
	public String showListPurnish() {
		return "admin/listPurnish";
	}
	
	@RequestMapping("employee/list-purnish")
	public String showListPurnish_() {
		return "employee/listPurnish";
	}
	
	@RequestMapping("admin/add-purnish")
	public String showAddPurnish(ModelMap model) {
		model.addAttribute("success", "");
		
		return "admin/addPurnish";
	}
	
	@RequestMapping( value = "admin/add-purnish", method = RequestMethod.POST)
	public String addpurnish(ModelMap model, @RequestParam("manv") String manv, @RequestParam("mucdo") String mucdo, @RequestParam("maqd") String maqd, @RequestParam("date") String thoigian) {
		NhanVienEntity nv = nvService.getNhanVien(manv);
		
		CT_XuphatEntity xp = new CT_XuphatEntity();
		CT_XuphatKey key = new CT_XuphatKey();
		System.out.println(mucdo);
		MucdoEntity md = mdService.getMucdobyid(Integer.parseInt(mucdo));
		key.setManv(manv);
		key.setMucdo(Integer.parseInt(mucdo));
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		try {
			date = formatter.parse(thoigian);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		key.setThoigianxuphat(date);
		
		xp.setKey(key);
		xp.setNventity(nv);
		QuyDinhEntity qd = qdService.getqdbyID(Integer.parseInt(maqd));
		xp.setIdmd(md);
		xpService.addXuphat(xp);
		
		ThongBaoEntity tb = tbService.autoCreateTBXP(qd.getTenqd(), date, md.getTienphat());
		tbService.addThongBao(tb);
		cttbService.addOneNV(nv, tb);
		
		model.addAttribute("success", "Thêm xử phạt thành công!");
		
		return "admin/addPurnish";
	}
	
	@RequestMapping(value = "admin/loadMucdo")
	public String getMucDo(ModelMap model, @RequestParam("maqd") String maqd, @RequestParam("manv") String manv) {
		List<MucdoEntity> lstMucdo = mdService.getAllmucdo();
		lstMucdo = lstMucdo.stream().filter(o -> o.getIdqd().getId() == Integer.parseInt(maqd)).collect(Collectors.toList());
		
		model.addAttribute("success", "");
		model.addAttribute("lstMucdo", lstMucdo);
		model.addAttribute("nvchon", manv);
		model.addAttribute("qdchon", Integer.parseInt(maqd));
		
		return "admin/addPurnish";
	}
	
	@ModelAttribute("nvs")
	public List<NhanVienEntity> listnv(){
		List<NhanVienEntity> list = nvService.getAllNhanVienOfPb();
		return list;
	}
	
	@ModelAttribute("qds")
	public List<QuyDinhEntity> listqd(){
		NhanVienEntity nv = nvService.getNVLogin();
		List<QuyDinhEntity> list = qdService.getAllQDPB(nv.getMapb());
		return list;
	}
	
	@ModelAttribute("listPurnish")
	public List<CT_XuphatEntity> lxp() {
		NhanVienEntity nv = nvService.getNVLogin();
		List<CT_XuphatEntity> lxuphat = xpService.getXuPhatofPB(nv.getMapb().getMapb());
		return lxuphat;
	}
	
	@ModelAttribute("listPurnishPersonal")
	public List<CT_XuphatEntity> showLxp() {
		NhanVienEntity nv = nvService.getNVLogin();
		List<CT_XuphatEntity> lxuphat = xpService.getXuPhatofMANV(nv);
		return lxuphat;
	}
}
