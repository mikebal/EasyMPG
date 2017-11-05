package thesolocoder.com.easympg;

public class FillUpInfoStruct {

    private final int UNSET = -1;
    private int _id;
    private int _vehicleID;
    private boolean _missedPreviousFillUp = false;
    private double _fuelQuantiy;
    private double _price;
    private String _date;
    private double _odometer;
    private int _child = UNSET;
    private boolean _isOutDated = false;

    FillUpInfoStruct(int fillID, int vehicleID, int missedPreviousFillUp, double fuelQuantity, double price, String date, int odometer, int childFill, int isOutdated){
        _id = fillID;
        _vehicleID = vehicleID;
        if(missedPreviousFillUp == 0) {
            _missedPreviousFillUp = false;
        }
        else{
            _missedPreviousFillUp = true;
        }
        _fuelQuantiy = fuelQuantity;
        _price = price;
        _date = date;
        _odometer = odometer;
        _child = childFill;
        if(isOutdated == 0) {
            _isOutDated = false;
        }
        else{
            _isOutDated = true;
        }
    }

    public int get_id() {
        return _id;
    }

    public int get_vehicleID() {
        return _vehicleID;
    }

    public int getIsMissedPreviousFillUp() {
        if(_missedPreviousFillUp){
            return 1;
        }
        else{
            return 0;
        }
    }

    public double get_fuelQuantity() {
        return _fuelQuantiy;
    }

    public double get_price() {
        return _price;
    }

    public String get_date() {
        return _date;
    }

    public double get_odometer() {
        return _odometer;
    }

    public int getChild() {
        return _child;
    }

    public void setIsOutdated(boolean isOutdated){
        _isOutDated = isOutdated;
    }

    public int getIsOutdated(){
        if(_isOutDated){
            return 1;
        }
        else{
            return 0;
        }
    }
}
