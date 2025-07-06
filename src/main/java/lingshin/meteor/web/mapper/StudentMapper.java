package lingshin.meteor.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import lingshin.meteor.web.DTO.StudentDTO;
import lingshin.meteor.web.entity.Student;
import lingshin.meteor.web.repository.MentorRepository;
import lingshin.meteor.web.entity.Mentor;

@Mapper(componentModel = "spring")
public interface StudentMapper {
  StudentMapper instance = Mappers.getMapper(StudentMapper.class);

  @Autowired
  MentorRepository mentorRepository;

  @Mapping(source = "mentor.name", target = "mentor")
  StudentDTO toDto(Student student);

  @Mapping(source = "mentor", target = "mentor", qualifiedByName = "mentorFromName")
  Student toEntity(StudentDTO dto);
}
