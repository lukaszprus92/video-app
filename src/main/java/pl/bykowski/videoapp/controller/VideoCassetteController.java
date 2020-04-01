package pl.bykowski.videoapp.controller;


import org.springframework.web.bind.annotation.*;
import pl.bykowski.videoapp.model.VideoCassette;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cassettes")
public class VideoCassetteController {

    private List<VideoCassette> videoCassettes;

    public VideoCassetteController() {
        videoCassettes = new ArrayList<>();
        videoCassettes.add(new VideoCassette(1L, "Titanic", LocalDate.of(1995, 1, 1)));
        videoCassettes.add(new VideoCassette(2L, "Pulp Fiction", LocalDate.of(1990, 2, 2)));
    }

    @GetMapping
    public List<VideoCassette> getAll(){
        return videoCassettes;
    }

    @GetMapping("/{index}")
    public VideoCassette getById(@PathVariable Long index){
        Optional<VideoCassette> first = videoCassettes.stream().filter(element -> element.getId() == index).findFirst();
        return first.get();
    }

    @PostMapping()
    public void addVideo(@RequestBody VideoCassette videoCassette) {
        videoCassettes.add(videoCassette);
    }

    @PutMapping("/{index}")
    public void updateVideo (@PathVariable Long index, @RequestBody VideoCassette videoCassette){
       VideoCassette chuj = videoCassettes.stream().filter(element -> element.getId() == index).findFirst().get();
       chuj.setProductionYear(videoCassette.getProductionYear());
       chuj.setTitle(videoCassette.getTitle());


        // To .set chce na początku inta który jest indexem w tabeli, a nie moim id, które sie różni
       // videoCassettes.set(Math.toIntExact(index), videoCassette);
    }

    @DeleteMapping("/{index}")
    public void deleteVideo (@PathVariable Long index){
        videoCassettes.removeIf(element -> element.getId() == index);

    }




}
