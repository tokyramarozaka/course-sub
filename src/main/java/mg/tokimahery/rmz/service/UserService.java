package mg.tokimahery.rmz.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import mg.tokimahery.rmz.mapper.UserMapper;
import mg.tokimahery.rmz.model.User;
import mg.tokimahery.rmz.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  private final UserMapper mapper;
  private final UserRepository repository;

  public User getById(UUID id) {
    return mapper.toModel(
        repository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
  }
}
