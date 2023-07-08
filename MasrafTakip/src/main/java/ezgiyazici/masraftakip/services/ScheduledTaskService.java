package ezgiyazici.masraftakip.services;

import ezgiyazici.masraftakip.dto.KisiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduledTaskService {

    private final KisiService kisiService;
    private final MasrafService masrafService;
    @Scheduled(cron = "0 0 0 * * *")
    public void executeDaily() throws InterruptedException {
        masrafCalculate();
    }

    @Scheduled(cron = "0 0 0 * * 0")
    public void executeWeekly() throws InterruptedException {
        masrafCalculate();
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void executeMonthly() throws InterruptedException {
        masrafCalculate();
    }

    public void masrafCalculate(){
        List<KisiDto> list = kisiService.list();
        for(KisiDto kisiDto : list){
            masrafService.getAllMasrafById(kisiDto.getId());
        }
    }
}

