package ezgiyazici.masraftakip.controller;

import ezgiyazici.masraftakip.dto.KisiDto;
import ezgiyazici.masraftakip.services.KisiService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor //Injection
@Log4j2

// API
@RestController
@RequestMapping("/kisi")
@Api
public class KisiApi implements IGenericApi<KisiDto>{

    private final KisiService kisiService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody KisiDto kisiDto) {
        return ResponseEntity.ok(kisiService.create(kisiDto));
    }

    @GetMapping("/list")
    @Override
    public ResponseEntity<List<KisiDto>> list() {
        return ResponseEntity.ok(kisiService.list());
    }

    @Override
    @GetMapping({"/find/{id}"})
    public ResponseEntity<?> findById(@PathVariable(name = "id", required = false)Long id) {
        return ResponseEntity.ok(kisiService.findById(id));
    }

    @Override
    @DeleteMapping({ "/delete/{id}"})
    public ResponseEntity<?> deleteById(@PathVariable(name = "id", required = false)Long id)  {
        return ResponseEntity.ok(kisiService.deleteById(id));
    }

    @Override
    @PutMapping({ "/update/{id}"})
    public ResponseEntity<?> updateById(
            @PathVariable(name = "id", required = false)Long id,
            @Valid @RequestBody KisiDto kisiDto) {
        return ResponseEntity.ok(kisiService.updateById(id, kisiDto));
    }
}
