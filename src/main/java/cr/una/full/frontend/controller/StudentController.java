/*
 *
 * Copyright (C)  2020  mike.education
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Universidad Nacional de Costa Rica, Prof: Maikol Guzman Alan.
 */

package cr.una.full.frontend.controller;

import cr.una.full.frontend.model.Student;
import cr.una.full.frontend.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StudentController {

    // Student Service
    private StudentService studentService = new StudentService();

    @GetMapping("/students")
    public String students(Model model) {
        List<Student> studentList;
        studentList = studentService.loadAllStudents();

        model.addAttribute("studentList", studentList);

        // Esta es la vista (students.html)
        return "students";
    }

    @PostMapping("/students")
    public String students(Model model, HttpServletRequest request) {
        List<Student> studentList;
        String filterTxt = request.getParameter("filterTxt");

        if (filterTxt != "") {
            studentList = studentService.searchStudentsByTerm(filterTxt);
        } else {
            studentList = studentService.loadAllStudents();
        }
        model.addAttribute("studentList", studentList);

        // Esta es la vista (students.html)
        return "students";
    }

    @GetMapping("/student-new")
    public String addStudent(Model model) {
        Student student = new Student();

        model.addAttribute("student", student);

        // Esta es la vista (student-new.html)
        return "student-new";
    }

    @PostMapping("/student-new")
    public String addStudent(Student _student, BindingResult result, Model model) {
        List<Student> studentList;
        Student student = null;
        if (result.hasErrors()) {
            return "student-new";
        }

        if (_student.getName() != "") {
            student = studentService.saveStudent(_student);
        } else {
            student = new Student();
        }

        studentList = studentService.loadAllStudents();
        model.addAttribute("studentList", studentList);
        model.addAttribute("student", student);

        // Esta es la vista (student.html)
        return "students";
    }
}
