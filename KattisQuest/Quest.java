package KattisQuest;

class Quest {
    int energy;
    int gold;
    int id;
    public Quest(int id, int energy, int gold) {
        this.id = id;
        this.energy = energy;
        this.gold = gold;
    }

    public String toString(){
        return String.format("%d %d %d", this.id, this.energy, this.gold);
    }
}