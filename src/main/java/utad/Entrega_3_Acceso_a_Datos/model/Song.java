package utad.Entrega_3_Acceso_a_Datos.model;


import jakarta.persistence.*;

@Entity
@Table(name="Song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column
    private String popularity;

    @Column
    private String genre;

    @Column
    private String colab;

    @Column
    private String title;

    @Column
    private int duration;

    @Column
    private String singer;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColab() {
        return colab;
    }

    public void setColab(String colab) {
        this.colab = colab;
    }
}
