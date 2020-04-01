package pl.bykowski.videoapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bykowski.videoapp.model.VideoCassette;
import pl.bykowski.videoapp.service.VideoCassetteService;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cassettes")
public class VideoCassetteController {

    private VideoCassetteService videoCassetteService;

    @Autowired
    public VideoCassetteController(VideoCassetteService videoCassetteService) {
        this.videoCassetteService = videoCassetteService;
    }

    @GetMapping
    public Iterable<VideoCassette> getAll(){
        return videoCassetteService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<VideoCassette> getById(@PathVariable Long id){
        return videoCassetteService.findById(id);
    }

    @PostMapping()
    public void addVideo(@RequestBody VideoCassette videoCassette) {
        videoCassetteService.save(videoCassette);
    }

    @PutMapping()
    public void updateVideo(@RequestBody VideoCassette videoCassette) {
        videoCassetteService.save(videoCassette);
    }

    /*@PutMapping("/{id}")
    public void updateVideo (@PathVariable Long id, @RequestParam String title, @RequestParam LocalDate productionYear){
        videoCassetteService.modify(id, title, productionYear);
    }*/

    @DeleteMapping("/{id}")
    public void deleteVideo (@PathVariable Long id){
        videoCassetteService.deleteById(id);
    }



}
