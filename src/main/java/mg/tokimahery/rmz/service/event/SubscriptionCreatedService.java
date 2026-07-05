package mg.tokimahery.rmz.service.event;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import mg.tokimahery.rmz.endpoint.event.model.SubscriptionCreated;
import mg.tokimahery.rmz.mail.Email;
import mg.tokimahery.rmz.mail.Mailer;
import mg.tokimahery.rmz.service.CourseService;
import mg.tokimahery.rmz.service.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubscriptionCreatedService implements Consumer<SubscriptionCreated> {
  private final Mailer mailer;
  private final UserService userService;
  private final CourseService courseService;

  @Override
  @SneakyThrows
  public void accept(SubscriptionCreated subscriptionCreated) {
    sendCourseConfirmationEmailToUser(
        subscriptionCreated.getSubscription().userId(),
        subscriptionCreated.getSubscription().courseId());
  }

  private void sendCourseConfirmationEmailToUser(UUID userId, UUID courseId)
      throws AddressException {
    var user = userService.getById(userId);
    var course = courseService.getById(courseId);
    var to = user.email();
    var subject = "Subscription confirmation(asynchronous): %s".formatted(course.title());
    var htmlBody =
        """
        <html>
          <body>
            <p>Dear %s,</p>
            <p>Your subscription has been confirmed. You now have full access to your course.</p>
            <p>Thank you for joining us!</p>
            <p>Best regards,</p>
            <p>The Team</p>
          </body>
        </html>
        """
            .formatted(user.userName());
    var email =
        new Email(new InternetAddress(to), List.of(), List.of(), subject, htmlBody, List.of());
    mailer.accept(email);
  }
}
