package thesolocoder.com.easympg;

class VehicleInfoStruct {
    private static final int UNINITIALIZED_PK = -1;
    private int _vehiclePK = UNINITIALIZED_PK;
    private String _nickName;
    private String _make;
    private String _model;
    private String _odometerUnits;
    private String _fuelUnits;
    private String _year;
    private static final String SINGLE_QUOTE_SUBSTITUTE = "#%QUOTE%#";


    VehicleInfoStruct(int vehiclePK, String nickName, String make, String model, String year, String odometerUnits, String fuelUnits){
        _vehiclePK = vehiclePK;
        _nickName = nickName.replaceAll("'", SINGLE_QUOTE_SUBSTITUTE);
        _make = make.replaceAll("'", SINGLE_QUOTE_SUBSTITUTE);
        _model = model.replaceAll("'", SINGLE_QUOTE_SUBSTITUTE);
        _year = year;
        _odometerUnits = odometerUnits;
        _fuelUnits = fuelUnits;
    }

    String getVehiclePK() {
        return String.valueOf(_vehiclePK);
    }

    String getNickName() { return _nickName.replaceAll(SINGLE_QUOTE_SUBSTITUTE, "'");
    }

    String getNickNameSqlSafe() { return _nickName; }

    String getMake() {
        return _make.replaceAll(SINGLE_QUOTE_SUBSTITUTE, "'");
    }

    String getModel() { return _model.replaceAll(SINGLE_QUOTE_SUBSTITUTE, "'");
    }

    String getOdometerUnits() {
        return _odometerUnits;
    }

    String getFuelUnits(){
        return _fuelUnits;
    }

    String  getYear() {
        return _year;
    }

    void setVehiclePK(int vehiclePK){
        _vehiclePK = vehiclePK;
    }
}
