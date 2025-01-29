package utad.Entrega_3_Acceso_a_Datos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import utad.Entrega_3_Acceso_a_Datos.model.Song;
import utad.Entrega_3_Acceso_a_Datos.repository.SongRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Song addOneSong(Song song){
        return songRepository.save(song);
    }

    public List<Song> addCoupleSongs(List<Song> listOfSongs) {
        if (listOfSongs == null || listOfSongs.isEmpty()) {
            throw new IllegalArgumentException("La lista de canciones no puede ser nula o vacía");
        }
        return songRepository.saveAll(listOfSongs);
    }

    public Song getOneSong(Long id){
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent()){
            return optionalSong.get();
        }  else {
            throw new RuntimeException("Song not found with id: " + id); // Lanza una excepción si no se encuentra la canción
        }
    }

    public List<Song> getAllSongs(){
        return songRepository.findAll();
    }

    public void deleteOneSong(Long ID){
        songRepository.deleteById(ID);
    }

    public Song modifySong(Long id, String newSinger) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent()) {
            Song song = optionalSong.get();
            song.setSinger(newSinger);
            return songRepository.save(song);
        } else {
            throw new RuntimeException("Song not found with id: " + id); // Lanza una excepción si no se encuentra la canción
        }
    }

    public List<Song> getSongsFromDeterminateSinger(String singer){
        List<Song> tracklist=songRepository.findBySinger(singer);
        if (tracklist == null || tracklist.isEmpty()){
            throw new RuntimeException("Not found");
        }
        return tracklist;
    }

    public List<Song> getSongsFromDeterminatePopularity(String popularity){
        List<Song> tracklist=songRepository.findByPopularity(popularity);
        if (tracklist == null || tracklist.isEmpty()){
            throw new RuntimeException("Not found");
        }
        return tracklist;
    }

    public List<Song> getSongsByGenre(String genre){
        List<Song> tracklist=songRepository.findByGenre(genre);
        if (tracklist == null || tracklist.isEmpty()){
            throw new RuntimeException("Not found");
        }
        return tracklist;
    }

    public List<Song> getSongsWhichDurationIsHigherThan(int duration){
        List<Song> tracklist=songRepository.searchByDuration(duration);
        if (tracklist == null || tracklist.isEmpty()){
            throw new RuntimeException("Not found");
        }
        return tracklist;
    }

    public List<Song> getSongsByColabAndSinger(String colab, String singer){
        List<Song> tracklist=songRepository.findByColabAndSinger(colab, singer);
        if (tracklist == null || tracklist.isEmpty()){
            throw new RuntimeException("Not found");
        }
        return tracklist;
    }



}
