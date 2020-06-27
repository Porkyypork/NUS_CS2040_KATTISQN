package AssiginingWorkstations;

class Researcher {
    private final int arr;
    private final int stays;
    public Researcher(int arr, int stays) {
        this.arr = arr;
        this.stays = stays;
    }

    public int getStays() {
        return this.stays;
    }
    public int getArr() {
        return this.arr;
    }

    public String toString() {
        return String.format("%d %d", this.arr, this.stays); 
   }
}