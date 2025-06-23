package dev.samuel.yugigo.services.response;

import java.util.List;

// classe p/ filtrar apenas os atributos relevantes e outros tratamentos caso necessário
public class APICard {
    private Long id;
    private String name;
    private String desc;
    private String attribute;
    private String race;
    private Integer atk;
    private Integer def;
    private Integer level;
    private List<CardImage> card_images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc != null ? desc.replace("''", "") : null;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Integer getAtk() {
        return atk;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<CardImage> getCard_images() {
        return card_images;
    }

    public void setCard_images(List<CardImage> card_images) {
        this.card_images = card_images;
    }

    public String getImageUrl() {
        if (card_images != null && !card_images.isEmpty()) {
            return card_images.getFirst().getImage_url();
        }
        return null;
    }

    @Override
    public String toString() {
        return "APICard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", attribute='" + attribute + '\'' +
                ", race='" + race + '\'' +
                ", atk=" + atk +
                ", def=" + def +
                ", level=" + level +
                ", card_url=" + card_images.getFirst().getImage_url() +
                '}';
    }
}

