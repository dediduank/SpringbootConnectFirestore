package myproject.connectFirebase.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import myproject.connectFirebase.model.entity.Student;
import myproject.connectFirebase.service.studentService;


@RestController
@RequestMapping("/api")
public class studentController {
    
    @Autowired
    private studentService studentService;

    @GetMapping("/check")
    @ResponseBody
    public String checkAPI(){
        return "hello";
    }

    @PostMapping("/student")
    public String saveStudent(@RequestBody Student student) throws InterruptedException, ExecutionException{
        
        return studentService.save(student);
        
    }

    @GetMapping("/studentall")
    public List<Student> viewStudentAll() throws InterruptedException, ExecutionException{
        
        return studentService.getStudentAll();
        
    }

    @GetMapping("/student/{name}")
    public Student viewStudent(@PathVariable String name) throws InterruptedException, ExecutionException{
        
        return studentService.getStudent(name);
        
    }

    @PutMapping("/student")
    public String updateStudent(@RequestBody Student student) throws InterruptedException, ExecutionException{
        
        return studentService.update(student);
        
    }

    @DeleteMapping("/student/{name}")
    public String deleteStudent(@PathVariable String name) throws InterruptedException, ExecutionException{
        
        return studentService.delete(name);
        
    }

}
