package mg.tokimahery.rmz.service;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import mg.tokimahery.rmz.endpoint.rest.controller.dto.SubscriptionRequest;
import mg.tokimahery.rmz.mail.Email;
import mg.tokimahery.rmz.mail.Mailer;
import mg.tokimahery.rmz.mapper.SubscriptionMapper;
import mg.tokimahery.rmz.model.Subscription;
import mg.tokimahery.rmz.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubscriptionService {
  private final Mailer mailer;
  private final SubscriptionRepository repository;
  private final SubscriptionMapper mapper;
  private final UserService userService;

  public Subscription create(UUID id, SubscriptionRequest subscriptionRequest)
      throws AddressException {
    var asEntity = mapper.toEntity(id, subscriptionRequest);
    var saved = mapper.toModel(repository.save(asEntity));
    sendEmailToUserId(subscriptionRequest.userId());
    return saved;
  }

  private void sendEmailToUserId(UUID userId) throws AddressException {
    var user = userService.getById(userId);
    var to = user.email();
    var subject = "Subscription confirmation";
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
