package pl.bykowski.videoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bykowski.videoapp.model.VideoCassette;

public interface VideoCassetteRepository extends JpaRepository<VideoCassette, Long> {

}
