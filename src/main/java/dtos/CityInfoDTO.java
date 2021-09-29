import entities.CityInfo;

public class CityInfoDTO{
    
    private Integer id;
    private String city;
    private String zipCode;

    public CityInfoDTO() {
    }
    
    public CityInfoDTO(CityInfo c){
        this.city = c.getCity();
        this.zipCode = c.getZipCode();
        this.id = c.getId();
    }

    public CityInfoDTO(String city, String zipCode) {
        this.city = city;
        this.zipCode = zipCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String City) {
        this.city = City;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    
    
}