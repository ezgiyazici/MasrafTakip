package ezgiyazici.masraftakip.services;

import ezgiyazici.masraftakip.bean.ModelMapperBean;
import ezgiyazici.masraftakip.dto.MasrafDto;
import ezgiyazici.masraftakip.entity.MasrafEntity;
import ezgiyazici.masraftakip.exception.BadRequestException;
import ezgiyazici.masraftakip.exception.ResourceNotFoundException;
import ezgiyazici.masraftakip.repository.IMasrafRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor // Injection
@Log4j2

// SERVICE
@Service
public class MasrafService implements IGenericService<MasrafDto, MasrafEntity> {
    private final ModelMapperBean modelMapperBean;
    private final IMasrafRepo iMasrafRepo;

    private final KisiService kisiService;
    @Override
    public MasrafDto EntityToDto(MasrafEntity masrafEntity) {
        return modelMapperBean.modelMapperMethod().map(masrafEntity, MasrafDto.class);
    }

    @Override
    public MasrafEntity DtoToEntity(MasrafDto masrafDto) {
        return modelMapperBean.modelMapperMethod().map(masrafDto, MasrafEntity.class);
    }

    @Transactional
    @Override
    public MasrafDto create(MasrafDto masrafDto) {
        if(masrafDto!=null){
            MasrafEntity masrafEntityModel= DtoToEntity(masrafDto);
            MasrafEntity masrafEntity=iMasrafRepo.save(masrafEntityModel);
            masrafDto.setId(masrafEntity.getId());
            masrafDto.setMasraf_miktari((long) masrafEntity.getMasraf_miktari());
            masrafDto.setMasraf_adi(masrafEntity.getMasraf_adi());
            masrafDto.setKisi(kisiService.EntityToDto(masrafEntity.getKisi()));
        }
        else if (masrafDto == null)
            throw new BadRequestException("Body'yi kontrol ediniz");
        return masrafDto;
    }

    @Override
    public List<MasrafDto> list() {
        Iterable<MasrafEntity> masrafEntityIterable = iMasrafRepo.findAll();
        List<MasrafDto> list = new ArrayList<>();
        for(MasrafEntity entity : masrafEntityIterable){
            MasrafDto masrafDto = EntityToDto(entity);
            list.add(masrafDto);
        }
        return list;
    }

    @Override
    public MasrafDto findById(Long id){
        MasrafEntity masrafEntity = null;
        if(id!=null){
            masrafEntity = iMasrafRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id + " nolu ID bulunamadı"));

        }
        else if (id == null)
            throw new BadRequestException(id + "Id Null Geldi"); //
        return EntityToDto(masrafEntity);
    }

    @Transactional
    @Override
    public MasrafDto deleteById(Long id) {
        MasrafDto masrafDtoDeleteFind = findById(id);
        MasrafEntity masrafEntity=DtoToEntity(masrafDtoDeleteFind);
        iMasrafRepo.delete(masrafEntity);
        return masrafDtoDeleteFind;
    }

    @Transactional
    @Override
    public MasrafDto updateById(Long id, MasrafDto masrafDto){
        MasrafEntity masrafEntity = DtoToEntity(findById(id));
        if(masrafEntity!=null){
            masrafEntity.setId(id);
            masrafEntity.setMasraf_adi(masrafDto.getMasraf_adi());
            masrafEntity.setMasraf_miktari(Math.toIntExact(masrafDto.getMasraf_miktari()));
            masrafEntity.setKisi(kisiService.DtoToEntity(masrafDto.getKisi()));
            iMasrafRepo.save(masrafEntity);
            masrafDto.setId(masrafEntity.getId());
            masrafDto.setMasraf_adi(masrafEntity.getMasraf_adi());
            masrafDto.setMasraf_miktari((long) masrafEntity.getMasraf_miktari());
            masrafDto.setKisi(kisiService.EntityToDto(masrafEntity.getKisi()));
        }
        return null;
    }

    @Transactional
    public List<MasrafDto> getAllMasrafById(@Param("name") Long id) {
        List<MasrafDto> list = list();
        List<MasrafDto> masrafList = new ArrayList<>();
        for(MasrafDto masrafDto : list){
            if(masrafDto.getKisi().getId()==id){
                masrafList.add(masrafDto);
            }
        }
        return list;
    }
}
