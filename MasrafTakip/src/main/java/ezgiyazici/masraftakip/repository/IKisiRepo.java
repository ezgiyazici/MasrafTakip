package ezgiyazici.masraftakip.repository;

import ezgiyazici.masraftakip.entity.KisiEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IKisiRepo extends CrudRepository<KisiEntity, Long> {
}
