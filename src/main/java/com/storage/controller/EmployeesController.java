package com.storage.controller;

import org.springframework.stereotype.Controller;

@Controller
public class EmployeesController {
/*

    @Autowired
    EmployeeDAO employeeDAO;
    @Autowired
    CountryDAO countryDAO;
    @Autowired
    CityDAO cityDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/employees")
    public ModelAndView getEmployees() {
        ModelAndView model = new ModelAndView("employees.jsp");
        model.addObject("employees", employeeDAO.getAllEmployees());
        return model;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/removeEmployee{id}")
    public ModelAndView removeEmployee(@PathVariable("id") int id) {
        employeeDAO.removeEmployee(id);
        return getEmployees();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editEmployee{id}")
    public ModelAndView editEmployee(@PathVariable("id") int id) {

        ModelAndView modelAndView = new ModelAndView("editEmployee.jsp",
                "employee", employeeDAO.getEmployeeById(id));
        modelAndView.addObject("cities", cityDAO.getAllCityNames());
        modelAndView.addObject("message", "Add new employee");
        return modelAndView;
    }

    @RequestMapping(value = {"/editEmployee{id}"}, method = RequestMethod.POST)
    public ModelAndView addEmployee(@PathVariable("id") int id, Model model, @ModelAttribute("employee") Employee employee) {
        model.addAttribute("countryName", employee.getContactInfo().getCountry());
        model.addAttribute("city", employee.getContactInfo().getCity());
        model.addAttribute("street", employee.getContactInfo().getStreet());
        model.addAttribute("streetNumber", employee.getContactInfo().getStreetNumber());
        model.addAttribute("phoneNumber", employee.getContactInfo().getPhoneNumber());
        model.addAttribute("firstName", employee.getFirstName());
        model.addAttribute("lastName", employee.getLastName());
        model.addAttribute("birthday", employee.getBirthday());
        model.addAttribute("salary", employee.getSalary());
        employee.setId(id);
        employeeDAO.editEmployee(employee);
        return getEmployees();
    }

    @RequestMapping(value = {"/addEmployee"}, method = RequestMethod.GET)
    public ModelAndView addEmployeeShowForm(@ModelAttribute("employee") Employee employee) {
        ModelAndView modelAndView = new ModelAndView("addEmployee.jsp", "employee", new Employee());
        modelAndView.addObject("cities", cityDAO.getAllCityNames());
        return modelAndView;
    }

    @RequestMapping(value = {"/addEmployee"}, method = RequestMethod.POST)
    public ModelAndView addEmployee(Model model, @ModelAttribute("employee") Employee employee) {
        model.addAttribute("countryName", employee.getContactInfo().getCountry());
        model.addAttribute("city", employee.getContactInfo().getCity());
        model.addAttribute("street", employee.getContactInfo().getStreet());
        model.addAttribute("streetNumber", employee.getContactInfo().getStreetNumber());
        model.addAttribute("phoneNumber", employee.getContactInfo().getPhoneNumber());
        model.addAttribute("firstName", employee.getFirstName());
        model.addAttribute("lastName", employee.getLastName());
        model.addAttribute("birthday", employee.getBirthday());
        model.addAttribute("salary", employee.getSalary());
        model.addAttribute("gender", employee.isGender());
        employeeDAO.addEmployee(employee);
        return getEmployees();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/manageCitiesAndCountries")
    public ModelAndView getManageCitiesAndCountries() {
        ModelAndView model = new ModelAndView("manageCitiesAndCountries.jsp");
        model.addObject("citiesAndCountries", cityDAO.getCityAndCountryNames());
        model.addObject("countries", countryDAO.getCountryNames());
        model.addObject("employee", new Employee());
        return model;
    }

    @RequestMapping(value = {"/manageCitiesAndCountries"}, method = RequestMethod.POST)
    public String manageCitiesAndCountries(Model model, @ModelAttribute("employee") Employee employee) {
        model.addAttribute("city", employee.getContactInfo().getCity());
        model.addAttribute("country", employee.getContactInfo().getCountry());
        model.addAttribute("street", employee.getContactInfo().getStreet());

        String countryName = employee.getContactInfo().getStreet().length() < 3 ?
                employee.getContactInfo().getCountry() : employee.getContactInfo().getStreet();

        cityDAO.addNewCity(employee.getContactInfo().getCity(), countryName);
        return "redirect:manageCitiesAndCountries";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/removeCity{name}")
    public String removeCityAndCountry(@PathVariable("name") String cityName) {
        cityDAO.removeCity(cityName);
        return "redirect:manageCitiesAndCountries";
    }

*/

}
