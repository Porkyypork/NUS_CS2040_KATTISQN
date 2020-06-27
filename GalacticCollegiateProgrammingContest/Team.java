package GalacticCollegiateProgrammingContest;

class Team implements Comparable<Team> {
    public int id;
    public int solved;
    public int pen;

    public Team(int id, int pen) {
        this.id = id;
        this.pen = pen;
        this.solved = 1;
    }

    public Team(int id, int pen, int solved) {
        this.id = id;
        this.pen = pen;
        this.solved = solved;
    }

    public Team update(int pen) {
        return new Team(this.id, this.pen + pen, this.solved + 1);
    }

    public int compareTo(Team t) {
        if (this.solved > t.solved) {
            return 1;
        }
        if (t.solved > this.solved) {
            return -1;
        }
        if (this.pen > t.pen) {
            return -1;
        }
        if (t.pen > this.pen) {
            return 1;
        }
        if (this.id < t.id) {
            return 1;
        }
        if (this.id > t.id) {
            return -1;
        }
        return 0;
    }

    public boolean equals(Team t) {
        return this.id == t.id;
    }

    public String toString() {
        return String.format("%d %d %d", this.id, this.pen, this.solved);
    }
}