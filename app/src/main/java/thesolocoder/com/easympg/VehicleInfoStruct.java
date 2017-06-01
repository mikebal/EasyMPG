package thesolocoder.com.easympg;

public class VehicleInfoStruct {
    private static final int UNINITIALIZED_PK = -1;
    private int _vehiclePK = UNINITIALIZED_PK;
    private String _nickName;
    private String _make;
    private String _model;
    private String _odometerUnits;
    private String _fuelUnits;
    private String _year;


    public VehicleInfoStruct(int vehiclePK, String nickName, String make, String model, String year, String odometerUnits, String fuelUnits){
        _vehiclePK = vehiclePK;
        _nickName = nickName;
        _make = make;
        _model = model;
        _year = year;
        _odometerUnits = odometerUnits;
        _fuelUnits = fuelUnits;
    }

    public int getVehiclePK() {
        return _vehiclePK;
    }

    public String getNickName() {
        return _nickName;
    }

    public String getMake() {
        return _make;
    }

    public String getModel() {
        return _model;
    }

    public String getOdometerUnits() {
        return _odometerUnits;
    }

    public String getFuelUnits(){
        return _fuelUnits;
    }

    public String  getYear() {
        return _year;
    }

    public void setVehiclePK(int vehiclePK){
        _vehiclePK = vehiclePK;
    }
}
