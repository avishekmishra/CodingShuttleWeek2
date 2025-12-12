package com.techviews.Week2Practice.services;

import com.techviews.Week2Practice.dto.DepartmentDTO;
import com.techviews.Week2Practice.dto.EmployeeDTO;
import com.techviews.Week2Practice.entities.DepartmentEntity;
import com.techviews.Week2Practice.entities.EmployeeEntity;
import com.techviews.Week2Practice.exceptions.ResourceNotFoundException;
import com.techviews.Week2Practice.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;

    public DepartmentService(DepartmentRepository repo, ModelMapper modelMapper){
        this.departmentRepository = repo;
        this.mapper = modelMapper;
    }

    public Optional<DepartmentDTO> getDepartmentById(Long id){
        return departmentRepository.findById(id).map(entity-> mapper.map(entity, DepartmentDTO.class));
    }

    public List<DepartmentDTO> getAllDepartments(){
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(entity-> mapper.map(entity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createDepartment(DepartmentDTO request){
        DepartmentEntity entity = mapper.map(request, DepartmentEntity.class);
        DepartmentEntity savedEntity = departmentRepository.save(entity);
        return mapper.map(savedEntity, DepartmentDTO.class);
    }

    public void isExistsByDepartmentId(Long departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);
        if(!exists) throw new ResourceNotFoundException("Department not found with id: "+departmentId);
    }

    public boolean deleteDepartmentById(Long departmentId) {
        isExistsByDepartmentId(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }

    public DepartmentDTO updateEmployeeById(Long departmentId, DepartmentDTO request) {
        isExistsByDepartmentId(departmentId);
        DepartmentEntity entity = mapper.map(request, DepartmentEntity.class);
        entity.setId(departmentId);
        DepartmentEntity savedEntity = departmentRepository.save(entity);
        return mapper.map(savedEntity, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentRecord(Long departmentId, Map<String, Object> params){
        isExistsByDepartmentId(departmentId);
        DepartmentEntity entity = departmentRepository.findById(departmentId).get();
        params.forEach((field, value)-> {
            Field fieldToBeUpdated = ReflectionUtils.getRequiredField(DepartmentEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, entity, value);
        });
        DepartmentEntity departmentEntity = departmentRepository.save(entity);
        return mapper.map(departmentEntity, DepartmentDTO.class);
    }

}
