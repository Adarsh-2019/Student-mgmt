package com.lkm.controller;

import com.lkm.entity.StudentDTO;
import com.lkm.service.StudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/students")
public class StudentWebController {

    private final StudentService studentService;

    @Autowired
    public StudentWebController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String listStudents(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
        Page<StudentDTO> studentPage = studentService.getStudentsPage(page, size);
        log.info("Fetched {} students for page {}", studentPage.getNumberOfElements(), page);
        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("pageSize", size);
        log.info("Total pages: {}", studentPage.getTotalPages());
        return "students";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDTO student,
                              BindingResult result) {

        if (result.hasErrors()) {
            return "add-student";
        }
        studentService.saveStudent(student);
        return "redirect:/students?page";
    }

    @GetMapping("/add")
    public String showAddForm(Model model,
                              @RequestParam(defaultValue = "0") int page) {
        log.info("Navigating to add student form on page {}", page);
        model.addAttribute("student", new StudentDTO());
        model.addAttribute("page", page);
        log.info("Added empty StudentDTO to model");
        return "add-student";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model,
                                 @RequestParam(defaultValue = "0") int page) {
        log.info("Navigating to update form for student ID: {} on page {}", id, page);
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("page", page);
        log.info("Added StudentDTO to model for ID: {}", id);
        return "update-student";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id,
                                @RequestParam(defaultValue = "0") int page) {
        log.info("Deleting student with ID: {} on page {}", id, page);
        studentService.deleteStudentById(id);
        return "redirect:/students?page=" + page;
    }
}



