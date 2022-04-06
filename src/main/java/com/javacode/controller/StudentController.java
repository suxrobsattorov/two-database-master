package com.javacode.controller;

import com.javacode.dto.StudentDTO;
import com.javacode.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO dto) {
        StudentDTO response = studentService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/imtiyoz")
    public ResponseEntity<?> getImtiyozAll() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:9000/imtiyoz";
        return restTemplate.getForEntity(fooResourceUrl, String.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @GetMapping("/groupId/{groupId}")
    public ResponseEntity<?> getByGroupId(@PathVariable("groupId") Integer id) {
        return ResponseEntity.ok(studentService.getByGroupId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentDTO dto) {
        studentService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
