package com.vikki.week8_employee_management_system.controller;
import com.vikki.week8_employee_management_system.model.Employee;
import com.vikki.week8_employee_management_system.repository.EmployeeRepository;
import com.vikki.week8_employee_management_system.service.EmployeeService;
import com.vikki.week8_employee_management_system.service.EmployeeServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/home")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // display a list of employees
    @GetMapping("/view_employee")
    public  String viewHomePage(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        Employee employee1 = employeeService.getEmployeeByEmail(employee.getEmail());
        System.out.println(employee1);
        if(employee1 != null){
            employee1.setFirstName(employee.getFirstName());
            employee1.setLastName(employee.getLastName());
            employee1.setDob(employee.getDob());
            employee1.setEmail(employee.getEmail());
            employeeService.saveEmployee(employee1);
        }
        employeeService.saveEmployee(employee);
        return "redirect:/home/view_employee";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("Employee", employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id) {
        System.out.println("Employee deleted");
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/home/view_employee";
    }
}