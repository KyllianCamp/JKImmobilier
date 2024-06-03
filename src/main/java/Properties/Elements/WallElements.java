package Properties.Elements;

import jakarta.persistence.*;

@Entity
@Table(name = "wallelements", schema = "public")
public class WallElements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWallElement", nullable = false)
    private Integer idWallElement;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "idElement")
    private Elements element;

    public WallElements() {
    }
}
