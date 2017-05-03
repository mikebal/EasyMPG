package thesolocoder.com.easympg;

public class VehicleInfoStruct {
    private int _vehiclePK = -1;
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

    public int get_vehiclePK() {
        return _vehiclePK;
    }

    public String get_nickName() {
        return _nickName;
    }

    public String get_make() {
        return _make;
    }

    public String get_model() {
        return _model;
    }

    public String get_odometerUnits() {
        return _odometerUnits;
    }

    public String get_fuelUnits(){
        return _fuelUnits;
    }

    public String  get_year() {
        return _year;
    }

    public void set_vehiclePK(int vehiclePK){
        _vehiclePK = vehiclePK;
    }
}
