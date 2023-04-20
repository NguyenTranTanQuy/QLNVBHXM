package ptithcm.nhom27.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.TaiKhoanEntity;
import ptithcm.nhom27.services.NhanVienService;
import ptithcm.nhom27.services.TaiKhoanService;

@Controller
@RequestMapping("/reset-password")
public class ResetPasswordController {

	@Autowired
	TaiKhoanService tkService;

	@Autowired
	NhanVienService nvService;

	private int errorcode = 0;

	@RequestMapping(method = RequestMethod.GET)
	public String showResetpw() {
		return "resetpassword";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String resetPassword(@RequestParam("oldpw") String oldpw, @RequestParam("newpw") String newpw,
			@RequestParam("confirmpw") String confirmpw) {
		NhanVienEntity nv = nvService.getNVLogin();
		TaiKhoanEntity tk = tkService.getTaiKhoan(nv.getManv());

		if (!tkService.checkPassword(oldpw, tk.getMk())) {
			errorcode = 101;
			return "redirect:/reset-password";
		}
		
		tk.setMk(newpw);
		tkService.changeTaiKhoan(tk);
		errorcode = 100;
		return "redirect:/reset-password";
	}

	@ModelAttribute("success")
	public String showSuccess() {
		if (errorcode == 100) {
			errorcode = 0;
			return "Đổi mật khẩu thành công";
		}
		return "";
	}

	@ModelAttribute("error_oldpw")
	public String error_oldpw() {
		if (errorcode == 101) {
			errorcode = 0;
			return "Mật khẩu không chính xác";
		}
		return "";
	}
}
