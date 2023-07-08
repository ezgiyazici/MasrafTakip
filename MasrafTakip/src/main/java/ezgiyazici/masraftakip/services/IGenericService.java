package ezgiyazici.masraftakip.services;

import java.util.List;

public interface IGenericService <D,E>{

    // ### Model Mapper ###############################
    public D EntityToDto(E e);

    public E DtoToEntity(D d);

    // ### CRUD ###############################
    // CREATE
    public D create(D d);

    // LIST
    public List<D> list();

    // FIND
    public D findById(Long id) throws Exception;

    // DELETE
    public D deleteById(Long id) throws Exception;

    // UPDATE
    public D updateById(Long id, D d) throws Exception;
}
