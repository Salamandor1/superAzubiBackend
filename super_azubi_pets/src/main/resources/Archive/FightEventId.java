package Archive;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class FightEventId implements Serializable {
    private Long fightId;
    private Long index;

    public FightEventId() {
    }

    public FightEventId(Long fightId, Long index) {
        this.fightId = fightId;
        this.index = index;
    }

    public Long getFightId() {
        return this.fightId;
    }

    public Long getIndex() {
        return this.index;
    }

    public void setFightId(Long fightId) {
        this.fightId = fightId;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FightEventId that = (FightEventId) o;
        return Objects.equals(fightId, that.fightId) && Objects.equals(index, that.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fightId, index);
    }

}
