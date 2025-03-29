package formula1.example.FormulaOneStatTracker.request;

public class UpdateDriver {
    private DriverRequest oldDriver;
    private DriverRequest newDriver;

    public DriverRequest getOldDriver() {
        return oldDriver;
    }

    public void setOldDriver(DriverRequest oldDriver) {
        this.oldDriver = oldDriver;
    }

    public DriverRequest getNewDriver() {
        return newDriver;
    }

    public void setNewDriver(DriverRequest newDriver) {
        this.newDriver = newDriver;
    }
}
