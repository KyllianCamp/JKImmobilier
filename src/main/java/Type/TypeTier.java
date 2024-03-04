package Type;

import jakarta.persistence.*;

@Entity
@Table(name = "TYPE_TIERS", schema = "public")
public class TypeTier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TYPE_TIERS", nullable = false)
    private Integer idTypeTiers;

}
