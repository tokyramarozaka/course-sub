package mg.tokimahery.rmz.repository;

import java.util.List;
import mg.tokimahery.rmz.PojaGenerated;
import mg.tokimahery.rmz.repository.model.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@PojaGenerated
@Repository
public interface DummyRepository extends JpaRepository<Dummy, String> {

  @Override
  List<Dummy> findAll();
}
