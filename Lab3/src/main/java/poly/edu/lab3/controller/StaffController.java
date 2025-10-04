package poly.edu.lab3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.lab3.util.Staff;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class StaffController {

    // Bài 2: hiển thị chi tiết 1 nhân viên
    @RequestMapping("/staff/detail")
    public String detail(Model model) {
        Staff staff = Staff.builder()
                .id("hungnguyenlq123@gmail.com")
                .fullname("Nguyễn Hưng Nguyên")
                .photo("nguyen.jpg")
                .gender(true)
                .birthday(new GregorianCalendar(2007, Calendar.MARCH, 18).getTime())
                .salary(12345.6789)
                .level(2)
                .build();

        model.addAttribute("staff", staff);
        return "demo/staff-detail";
    }

    // Bài 3: hiển thị danh sách nhân viên (ảnh, tên, lương)
    @RequestMapping("/staff/list")
    public String list(Model model) {
        List<Staff> list = List.of(
                Staff.builder().id("user1@gmail.com").fullname("nguyễn văn user1").level(0).build(),
                Staff.builder().id("user2@gmail.com").fullname("nguyễn văn user2").level(1).build(),
                Staff.builder().id("user3@gmail.com").fullname("nguyễn văn user3").level(2).build(),
                Staff.builder().id("user4@gmail.com").fullname("nguyễn văn user4").level(2).build(),
                Staff.builder().id("user5@gmail.com").fullname("nguyễn văn user5").level(1).build(),
                Staff.builder().id("user6@gmail.com").fullname("nguyễn văn user6").level(0).build()
        );
        model.addAttribute("list", list);
        return "demo/staff-list"; // view cho bài 3
    }

    // Bài 4: hiển thị trạng thái danh sách (index, count, first, last, odd, even)
    @RequestMapping("/staff/status")
    public String status(Model model) {
        List<Staff> list = List.of(
                Staff.builder().id("user1@gmail.com").fullname("nguyễn văn user1").level(0).build(),
                Staff.builder().id("user2@gmail.com").fullname("nguyễn văn user2").level(1).build(),
                Staff.builder().id("user3@gmail.com").fullname("nguyễn văn user3").level(2).build(),
                Staff.builder().id("user4@gmail.com").fullname("nguyễn văn user4").level(2).build(),
                Staff.builder().id("user5@gmail.com").fullname("nguyễn văn user5").level(1).build(),
                Staff.builder().id("user6@gmail.com").fullname("nguyễn văn user6").level(0).build()
        );
        model.addAttribute("list", list);
        return "demo/staff-status"; // view cho bài 4
    }

}
