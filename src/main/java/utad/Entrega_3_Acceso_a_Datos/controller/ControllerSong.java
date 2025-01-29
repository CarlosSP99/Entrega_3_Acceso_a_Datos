package utad.Entrega_3_Acceso_a_Datos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utad.Entrega_3_Acceso_a_Datos.model.ApiResponse;
import utad.Entrega_3_Acceso_a_Datos.model.Song;
import utad.Entrega_3_Acceso_a_Datos.repository.SongRepository;
import utad.Entrega_3_Acceso_a_Datos.service.SongService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/song")
public class ControllerSong {

    @Autowired
    private SongService songService;

    // ---------- GET REQUEST ----------

    // Para otener una canción por su id
    @GetMapping(path = ("/id/{id}"))
    private ResponseEntity<ApiResponse<Song>> getOneSong(@PathVariable Long id){
        try{
            Song song = songService.getOneSong(id);
            return ResponseEntity
                    .ok(new ApiResponse<>(true, "200",song));
        } catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(false, "error",null));
        }
    }

    @GetMapping(path = "/popularity/{popularity}")
    private ResponseEntity<ApiResponse<List<Song>>> getAllSongs(
            @PathVariable String popularity
        ){
        try {
            List<Song> listOfSongs = songService.getSongsFromDeterminatePopularity(popularity);
            return ResponseEntity
                    .ok(new ApiResponse<>(true, "200",listOfSongs));
        } catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(false, "error",null));
        }
    }

    // Obtener el listado de canciones
    @GetMapping
    private ResponseEntity<ApiResponse<List<Song>>>  getAllSongs(
            // indicamos aqui integer ya que un int no puede ser nulo en cambi el interger SI
            @RequestParam(required = false) Integer  duration
    ) {
       try{
           if (duration == null){
               List<Song> listOfSongs = songService.getAllSongs();
               return ResponseEntity
                       .ok(new ApiResponse<>(true, "200",listOfSongs));
           }
           else {
               List<Song> listOfSongs = songService.getSongsWhichDurationIsHigherThan(duration);
               return ResponseEntity
                       .ok(new ApiResponse<>(true, "200",listOfSongs));
           }
       } catch (Exception e){
           return ResponseEntity
                   .badRequest()
                   .body(new ApiResponse<>(false, "error",null));
       }
    }

    // Buscar canción por genero
    @GetMapping(path = "/genre/{genre}")
    private ResponseEntity<ApiResponse<List<Song>>>  getSongsByGenre(
            @PathVariable String genre
    ){
        try {
            List<Song> listOfSongs = songService.getSongsByGenre(genre);
            return ResponseEntity
                    .ok(new ApiResponse<>(true, "200",listOfSongs));
        }
        catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(false, "error",null));
        }
    }

    // Buscar canción por cantante y poder indicar su indice de popularidad
    @GetMapping(path = "/singer/{singer}")
    private ResponseEntity<ApiResponse<List<Song>>>  getSongsFromDeterminateSinger(
            @PathVariable String singer,
            @RequestParam(required = false) String colab
    ){
        try{
            if (colab==null){
                List<Song> listOfSongs = songService.getSongsFromDeterminateSinger(singer);
                return ResponseEntity
                        .ok(new ApiResponse<>(true, "200",listOfSongs));
            }
            else  {
                List<Song> listOfSongs = songService.getSongsByColabAndSinger(colab, singer);
                return ResponseEntity
                        .ok(new ApiResponse<>(true, "200",listOfSongs));
            }
        } catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(false, "error",null));
        }

    }




    // ---------- POST REQUEST ----------

    // Para añadir un canción
    @PostMapping
    private ResponseEntity<ApiResponse<Song>> saveOneSong(@RequestBody Song song){
        try{
            Song songToSave =  songService.addOneSong(song);
            return ResponseEntity
                    .ok(new ApiResponse<>(true, "202",songToSave));
        } catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(false, "error",null));        }
    }



    // te dejaré un JSON y este metodo para que puedas meter muchas consultas
    // y luego tu ya enrear con los datos
    @PostMapping(path = "/lista")
    private ResponseEntity<ApiResponse<List<Song>>> saveCoupleSongs(@RequestBody List<Song> songList){
        try{
            List<Song> songs =  songService.addCoupleSongs(songList);
            return ResponseEntity
                    .ok(new ApiResponse<>(true, "202",songs));
        } catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(false, "error",null));
        }
    }

    // ---------- PATCH REQUEST ----------
    // no usar String para la request ya que no puede convertirse automáticamente en un String,
    // porque un String es un tipo de dato plano (texto sin estructura),
    // mientras que el JSON tiene una estructura clave-valor.
    @PatchMapping(path = "/{id}")
    private ResponseEntity<ApiResponse<Song>> updateSinger(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        try {
            String newSinger = request.get("singer");
            if (newSinger == null) {
                return ResponseEntity
                        .badRequest()
                        .body(new ApiResponse<>(false, "400",null));
            }

            Song updatedSong = songService.modifySong(id, newSinger);
            return ResponseEntity
                    .ok(new ApiResponse<>(true, "202",updatedSong));
        }
        catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(false, "404",null));
        }
    }

    // ---------- DELETE REQUEST ----------

    @DeleteMapping(path = ("/{id}"))
    private ResponseEntity<ApiResponse<Song>> deleteOneSong(@PathVariable Long id){
        try {
            songService.deleteOneSong(id);
            return ResponseEntity
                    .ok(new ApiResponse<>(true, "202",null));
        } catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(false, "error",null));
        }
    }



}
