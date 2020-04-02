package pl.bykowski.videoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bykowski.videoapp.exception.VideoCassetteNotFound;
import pl.bykowski.videoapp.model.VideoCassette;
import pl.bykowski.videoapp.repository.VideoCassetteRepository;

import java.time.LocalDate;

@Service
public class VideoCassetteService {

    private VideoCassetteRepository videoCassetteRepository;

    @Autowired
    public VideoCassetteService(VideoCassetteRepository videoCassetteRepository) {
        this.videoCassetteRepository = videoCassetteRepository;
    }


    public VideoCassette findById(Long id){
        return videoCassetteRepository.findById(id)
            .orElseThrow(() -> new VideoCassetteNotFound("Video Cassette Not Found for id " + id));
    }



    public Iterable<VideoCassette> findAll(){
        return videoCassetteRepository.findAll();
    }
    public void save(VideoCassette videoCassette){
        videoCassetteRepository.save(videoCassette);
    }

    public void modify(Long id, VideoCassette newVideoCassette){
        VideoCassette oldVideoCassette = findById(id);
        oldVideoCassette.setTitle(newVideoCassette.getTitle());
        oldVideoCassette.setProductionYear(newVideoCassette.getProductionYear());
        videoCassetteRepository.save(oldVideoCassette);
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
