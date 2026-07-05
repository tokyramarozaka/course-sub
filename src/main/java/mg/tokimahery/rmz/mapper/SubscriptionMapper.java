package mg.tokimahery.rmz.mapper;

import static mg.tokimahery.rmz.model.SubscriptionStatus.ACTIVE;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import mg.tokimahery.rmz.endpoint.rest.controller.dto.SubscriptionRequest;
import mg.tokimahery.rmz.model.Subscription;
import mg.tokimahery.rmz.repository.model.JSubscription;
import mg.tokimahery.rmz.service.CourseService;
import mg.tokimahery.rmz.service.UserService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubscriptionMapper {
  private final UserService userService;
  private final CourseService courseService;
  private final UserMapper userMapper;
  private final CourseMapper courseMapper;

  public List<Subscription> toModel(List<JSubscription> entities) {
    return entities.stream().map(this::toModel).toList();
  }

  public Subscription toModel(JSubscription entity) {
    return Subscription.builder()
        .id(entity.getId())
        .createdAt(entity.getCreatedAt())
        .status(entity.getStatus())
        .courseId(entity.getCourse().getId())
        .userId(entity.getUser().getId())
        .build();
  }

  public List<JSubscription> toEntity(List<Subscription> models) {
    return models.stream().map(this::toEntity).toList();
  }

  public JSubscription toEntity(Subscription model) {
    var user = userService.getById(model.userId());
    var course = courseService.getById(model.courseId());
    return JSubscription.builder()
        .id(model.id())
        .createdAt(model.createdAt())
        .status(model.status())
        .user(userMapper.toEntity(user))
        .course(courseMapper.toEntity(course))
        .build();
  }

  public JSubscription toEntity(UUID courseId, SubscriptionRequest subscriptionRequest) {
    var user = userService.getById(subscriptionRequest.userId());
    var course = courseService.getById(courseId);
    return JSubscription.builder()
        .id(UUID.randomUUID())
        .createdAt(Instant.now())
        .status(ACTIVE)
        .user(userMapper.toEntity(user))
        .course(courseMapper.toEntity(course))
        .build();
  }
}
