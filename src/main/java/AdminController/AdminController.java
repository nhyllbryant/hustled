package AdminController; // 📦 Package declaration

// 📥 Import necessary Spring Framework classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 🎯 Marks this class as a Spring MVC Controller
@RequestMapping("/admin") // 🌐 Base URL path for all methods in this controller
public class AdminController {

    @Autowired // 🔗 Automatically injects AdminService dependency
    private AdminService adminService;

    // 📊 Handles GET request for admin dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("visits", adminService.getVisitCount()); // 👁️ Add visit count to model
        model.addAttribute("signups", adminService.getSignupCount()); // 📝 Add signup count to model
        return "admin_dashboard"; // 🖼️ Return view name (Thymeleaf or JSP)
    }

    // 💼 Handles GET request for job listings
    @GetMapping("/jobs")
    public String jobs(Model model) {
        model.addAttribute("jobs", adminService.getAllJobs()); // 📋 Add job list to model
        return "admin_jobs"; // 🖼️ Return view name
    }

    // 👥 Handles GET request for candidate listings
    @GetMapping("/candidates")
    public String candidates(Model model) {
        model.addAttribute("candidates", adminService.getAllCandidates()); // 👤 Add candidate list to model
        return "admin_candidates"; // 🖼️ Return view name
    }
}