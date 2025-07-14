package Archive;

public class CreateAndUpdateFightEventDTO {
    private Long fightId;
    private Long index;
    private String event;

    public CreateAndUpdateFightEventDTO() {
    }

    public CreateAndUpdateFightEventDTO(Long fightId, Long index, String event) {
        this.fightId = fightId;
        this.index = index;
        this.event = event;
    }

    public Long getFightId() {
        return fightId;
    }

    public void setFightId(Long fightId) {
        this.fightId = fightId;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
