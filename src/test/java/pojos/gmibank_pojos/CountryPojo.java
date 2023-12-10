package pojos.gmibank_pojos;
import pojos.gmibank_pojos.StatePojo;

import java.util.List;
public class CountryPojo {
    private Integer id;
    private String name;
    private List<StatePojo> states;
    public CountryPojo() {
    }
    public CountryPojo(Integer id, String name, List<StatePojo> states) {
        this.id = id;
        this.name = name;
        this.states = states;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<StatePojo> getStates() {
        return states;
    }
    public void setStates(List<StatePojo> states) {
        this.states = states;
    }
    @Override
    public String toString() {
        return "CountryPojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", states=" + states +
                '}';
    }
}