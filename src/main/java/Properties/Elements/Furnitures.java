package Properties.Elements;

import Properties.Rooms;
import jakarta.persistence.*;

@Entity
@Table(name = "furnitures", schema = "public")
public class Furnitures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFurniture", nullable = false)
    private Integer idFurniture;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "idRoom")
    private Rooms room;

    public Furnitures() {
    }

}
