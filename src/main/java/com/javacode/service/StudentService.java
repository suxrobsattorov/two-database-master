package com.javacode.service;

import com.javacode.dto.StudentDTO;
import com.javacode.entity.profile.GroupEntity;
import com.javacode.entity.profile.StudentEntity;
import com.javacode.repository.profile.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO create(StudentDTO dto) {
        GroupEntity groupEntity = groupService.get(dto.getGroupId());
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setLevel(dto.getLevel());
        entity.setGroup(groupEntity);
        studentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<StudentDTO> getAll() {
        List<StudentEntity> entityList = studentRepository.findAll();
        return entityList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public StudentDTO getById(Integer id) {
        StudentEntity entity = get(id);
        return toDTO(entity);
    }

    public List<StudentDTO> getByGroupId(Integer groupId) {
        return getAll().stream().filter(dto -> dto.getGroupId().equals(groupId)).collect(Collectors.toList());
    }

    public void update(Integer id, StudentDTO dto) {
        StudentEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        studentRepository.save(entity);
    }

    public void delete(Integer id) {
        StudentEntity entity = get(id);
        studentRepository.delete(entity);
    }

    public StudentDTO toDTO(StudentEntity entity) {
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setLevel(entity.getLevel());
        dto.setGroupId(entity.getGroup().getId());
        return dto;
    }

    public StudentEntity get(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }
}
