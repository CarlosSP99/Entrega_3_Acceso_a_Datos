package utad.Entrega_3_Acceso_a_Datos.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utad.Entrega_3_Acceso_a_Datos.model.Song;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findBySinger(String singer);

    List<Song> findByPopularity(String popularity);

    List<Song> findByGenre(String genre);

    List<Song> findByColabAndSinger(String colab, String singer);

    @Query("SELECT s FROM Song s WHERE s.duration > :duration")
    List<Song> searchByDuration(@Param("duration") int duration);
}
