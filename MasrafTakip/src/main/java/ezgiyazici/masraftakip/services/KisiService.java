package ezgiyazici.masraftakip.services;

import ezgiyazici.masraftakip.bean.ModelMapperBean;
import ezgiyazici.masraftakip.dto.KisiDto;
import ezgiyazici.masraftakip.entity.KisiEntity;
import ezgiyazici.masraftakip.exception.BadRequestException;
import ezgiyazici.masraftakip.exception.ResourceNotFoundException;
import ezgiyazici.masraftakip.repository.IKisiRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2

@Service
@Qualifier("KisiService")
public class KisiService implements IGenericService<KisiDto, KisiEntity> {


    private final ModelMapperBean modelMapperBean;

    private final IKisiRepo iKisiRepo;

    @Override
    public KisiDto EntityToDto(KisiEntity kisiEntity) {
        return modelMapperBean.modelMapperMethod().map(kisiEntity, KisiDto.class);
    }

    @Override
    public KisiEntity DtoToEntity(KisiDto kisiDto) {
        return  modelMapperBean.modelMapperMethod().map(kisiDto, KisiEntity.class);
    }

    @Transactional
    @Override
    public KisiDto create(KisiDto kisiDto) {
        if(kisiDto !=null){
            KisiEntity kisiEntityModel = DtoToEntity(kisiDto);
            KisiEntity kisiEntity = iKisiRepo.save(kisiEntityModel);
            kisiDto.setId(kisiEntity.getId());
            kisiDto.setName(kisiEntity.getName());
            kisiDto.setSurname(kisiEntity.getSurname());

        }
        else if (kisiDto == null)
            throw new BadRequestException("Body'yi kontrol ediniz");
        return kisiDto;
    }

    @Override
    public List<KisiDto> list() {
        Iterable<KisiEntity> kisiEntityIterable  = iKisiRepo.findAll();
        List<KisiDto> list = new ArrayList<>();
        for(KisiEntity entity : kisiEntityIterable){
            KisiDto kisiDto =EntityToDto(entity);
            list.add(kisiDto);
        }
        return list;
    }

    @Override
    public KisiDto findById(Long id){
        KisiEntity kisiEntity = null;
        if(id!=null){
            kisiEntity = iKisiRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id + " nolu ID bulunamadı"));
        }
        else if (id == null)
            throw new BadRequestException(id + "Id Null Geldi"); //
        return EntityToDto(kisiEntity);
    }

    @Transactional
    @Override
    public KisiDto deleteById(Long id)  {
        KisiDto kisiDtoDeleteFind = findById(id);
        KisiEntity kisiEntity = DtoToEntity(kisiDtoDeleteFind);
        iKisiRepo.delete(kisiEntity);
        return kisiDtoDeleteFind;
    }

    @Transactional
    @Override
    public KisiDto updateById(Long id, KisiDto kisiDto){
        KisiEntity kisiEntity = DtoToEntity(findById(id));
        if(kisiEntity !=null){
            kisiEntity.setId(id);
            kisiEntity.setName(kisiDto.getName());
            kisiEntity.setSurname(kisiDto.getSurname());
            iKisiRepo.save(kisiEntity);
            kisiDto.setId(kisiEntity.getId());
            kisiDto.setName(kisiEntity.getName());
            kisiDto.setSurname(kisiEntity.getSurname());
        }
        return EntityToDto(kisiEntity);
    }

}
