package ir.dotin.core.model.entity.generic;

public class InformationPojo {

    private String informationCount;
    private String informationDate;

    public InformationPojo() {
    }

    public InformationPojo(String informationCount, String informationDate) {
        this.informationCount = informationCount;
        this.informationDate = informationDate;
    }

    public String getInformationCount() {
        return informationCount;
    }

    public void setInformationCount(String informationCount) {
        this.informationCount = informationCount;
    }

    public String getInformationDate() {
        return informationDate;
    }

    public void setInformationDate(String informationDate) {
        this.informationDate = informationDate;
    }
}
