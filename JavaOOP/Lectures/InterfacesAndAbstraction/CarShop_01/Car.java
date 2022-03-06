package CarShop_01;

public interface Car extends Serializable{
    public Integer TIRES = 4;

    public String getModel();

    public String getColor();

    public Integer getHorsePower();

    public String countryProduced();
}
