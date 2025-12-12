package com.techviews.Week2Practice.controllers;

import com.techviews.Week2Practice.dto.DepartmentDTO;
import com.techviews.Week2Practice.exceptions.ResourceNotFoundException;
import com.techviews.Week2Practice.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService repo){
        this.departmentService = repo;
    }

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable("departmentId") Long id){
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(id);
        return departmentDTO
                .map(entity-> ResponseEntity.ok(entity))
                .orElseThrow(()-> new ResourceNotFoundException("Department record does not exist with id : "+id));
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment(){
        List<DepartmentDTO> allDepartment = departmentService.getAllDepartments();
        return ResponseEntity.ok(allDepartment);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewEmployee(@RequestBody @Valid DepartmentDTO inputDepartment) {
        DepartmentDTO savedDepartment = departmentService.createDepartment(inputDepartment);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateEmployeeById(@RequestBody @Valid DepartmentDTO departmentDTO,
                                                          @PathVariable Long departmentId) {
        return ResponseEntity.ok(departmentService.updateEmployeeById(departmentId, departmentDTO));
    }

    @PatchMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                                 @PathVariable Long departmentId) {
        DepartmentDTO departmentDTO = departmentService.updateDepartmentRecord(departmentId, updates);
        if (departmentDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(departmentDTO);
    }
}
