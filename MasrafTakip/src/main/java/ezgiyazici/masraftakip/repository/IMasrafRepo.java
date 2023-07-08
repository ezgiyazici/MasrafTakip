package ezgiyazici.masraftakip.repository;

import ezgiyazici.masraftakip.entity.MasrafEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMasrafRepo extends CrudRepository<MasrafEntity, Long> {
}
