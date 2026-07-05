package mg.tokimahery.rmz.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import mg.tokimahery.rmz.mapper.CourseMapper;
import mg.tokimahery.rmz.model.Course;
import mg.tokimahery.rmz.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {
  private final CourseMapper mapper;
  private final CourseRepository repository;

  public Course getById(UUID id) {
    return mapper.toModel(
        repository.findById(id).orElseThrow(() -> new RuntimeException("Course not found")));
  }
}
