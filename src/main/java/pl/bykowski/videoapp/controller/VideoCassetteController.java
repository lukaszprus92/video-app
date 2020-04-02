package pl.bykowski.videoapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bykowski.videoapp.exception.VideoCassetteNotFound;
import pl.bykowski.videoapp.model.VideoCassette;
import pl.bykowski.videoapp.service.VideoCassetteService;

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
    public VideoCassette getById(@PathVariable Long id){
        return videoCassetteService.findById(id);
    }

    @PostMapping()
    public void addVideo(@RequestBody VideoCassette videoCassette) {
        videoCassetteService.save(videoCassette);
    }

    @PutMapping("/{id}")
    public void updateVideo(@PathVariable Long id, @RequestBody VideoCassette newVideoCassette) {
        videoCassetteService.modify(id, newVideoCassette);
    }

    @DeleteMapping("/{id}")
    public void deleteVideo (@PathVariable Long id){
        videoCassetteService.deleteById(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleVideoCassetteNotFound(VideoCassetteNotFound exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
