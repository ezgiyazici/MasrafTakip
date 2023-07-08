package ezgiyazici.masraftakip.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGenericApi<D> {

    // ### CRUD ###############################
    // CREATE
    public ResponseEntity<?>  create(D d);

    // LIST
    public ResponseEntity<List<D>>  list();

    // FIND
    public ResponseEntity<?> findById(Long id);

    // DELETE
    public  ResponseEntity<?> deleteById(Long id) ;

    // UPDATE
    public ResponseEntity<?>  updateById(Long id, D d);

}
