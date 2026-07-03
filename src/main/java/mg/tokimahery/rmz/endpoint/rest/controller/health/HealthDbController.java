package mg.tokimahery.rmz.endpoint.rest.controller.health;

import static mg.tokimahery.rmz.endpoint.rest.controller.health.PingController.KO;
import static mg.tokimahery.rmz.endpoint.rest.controller.health.PingController.OK;

import lombok.AllArgsConstructor;
import mg.tokimahery.rmz.PojaGenerated;
import mg.tokimahery.rmz.repository.DummyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PojaGenerated
@RestController
@AllArgsConstructor
public class HealthDbController {

  DummyRepository dummyRepository;

  @GetMapping("/health/db")
  public ResponseEntity<String> dummyTable_should_not_be_empty() {
    return dummyRepository.findAll().isEmpty() ? KO : OK;
  }
}
