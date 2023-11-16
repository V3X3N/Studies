class Wektor {
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    private double x;
    private double y;
    private double z;

    public Wektor(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Wektor dodajWektory(Wektor other) {
        return new Wektor(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Wektor odejmijWektory(Wektor other) {
        return new Wektor(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public double obliczIloczynSkalarny(Wektor other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Wektor obliczIloczynWektorowy(Wektor other) {
        double newX = this.y * other.z - this.z * other.y;
        double newY = this.z * other.x - this.x * other.z;
        double newZ = this.x * other.y - this.y * other.x;
        return new Wektor(newX, newY, newZ);
    }

    public double obliczDlugosc() {
        return Math.sqrt(x * x + y * y + z * z);
    }
}