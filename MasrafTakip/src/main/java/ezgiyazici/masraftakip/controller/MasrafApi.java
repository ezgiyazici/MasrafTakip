package ezgiyazici.masraftakip.controller;


import ezgiyazici.masraftakip.dto.MasrafDto;
import ezgiyazici.masraftakip.services.MasrafService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Log4j2

// API
@RestController
@RequestMapping("/masraf")
@Api
public class MasrafApi implements IGenericApi<MasrafDto>{
    private final MasrafService masrafService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody MasrafDto masrafDto) {
        return ResponseEntity.ok(masrafService.create(masrafDto));
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<MasrafDto>> list() {
        return ResponseEntity.ok(masrafService.list());
    }

    @Override
    @GetMapping({"/find/{id}"})
    public ResponseEntity<?> findById(@PathVariable(name = "id", required = false)Long id){
        return ResponseEntity.ok(masrafService.deleteById(id));
    }

    @Override
    @DeleteMapping({ "/delete/{id}"})
    public ResponseEntity<?> deleteById(@PathVariable(name = "id", required = false)Long id) {
        return ResponseEntity.ok(masrafService.deleteById(id));
    }

    @Override
    @PutMapping({ "/update/{id}"})
    public ResponseEntity<?> updateById(
            @PathVariable(name = "id", required = false)Long id,
            @Valid @RequestBody MasrafDto masrafDto)  {
        return ResponseEntity.ok(masrafService.updateById(id,masrafDto));

    }
    @GetMapping({ "/tummasraf/{id}"})
    public ResponseEntity<?> getAllMasraf(@PathVariable(name = "id", required = false)Long id){
        return new ResponseEntity<>(
                "Masraf:" + masrafService.getAllMasrafById(id), HttpStatus.OK);
    }
}
