package pl.bykowski.videoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.bykowski.videoapp.model.VideoCassette;
import pl.bykowski.videoapp.repository.VideoCassetteRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class VideoCassetteService {

    private VideoCassetteRepository videoCassetteRepository;

    @Autowired
    public VideoCassetteService(VideoCassetteRepository videoCassetteRepository) {
        this.videoCassetteRepository = videoCassetteRepository;
    }


    public Optional<VideoCassette> findById(Long id){ return videoCassetteRepository.findById(id); }
    public Iterable<VideoCassette> findAll(){
        return videoCassetteRepository.findAll();
    }
    public void save(VideoCassette videoCassette){
        videoCassetteRepository.save(videoCassette);
    }

    public void modify(Long id, VideoCassette newVideoCassette){

        /*VideoCassette oldVideoCassette = videoCassetteRepository.findById(id).get();
        oldVideoCassette.setTitle(newVideoCassette.getTitle());
        oldVideoCassette.setProductionYear(newVideoCassette.getProductionYear());
        videoCassetteRepository.save(oldVideoCassette);*/

        // oba działają, ale ten mi lepiej wygląda
        videoCassetteRepository.findById(id).get().setTitle(newVideoCassette.getTitle());
        videoCassetteRepository.findById(id).get().setProductionYear(newVideoCassette.getProductionYear());
        videoCassetteRepository.save(videoCassetteRepository.findById(id).get());
    }

    public void deleteById(Long id){
        videoCassetteRepository.deleteById(id);
    }

    // TO TYLKO RAZ WYWOŁAŁEM, ŻEBY STWORZYĆ POCZĄTKOWE ELEMENTY:
    //@EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        videoCassetteRepository.save(new VideoCassette(1L, "Titanic", LocalDate.of(1995, 1, 1)));
        videoCassetteRepository.save(new VideoCassette(2L, "Pulp Fiction", LocalDate.of(1990, 2, 2)));
    }

}
