import java.util.*;

class CoconutSplat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int syllables = sc.nextInt();
        int players = sc.nextInt();
        LinkedList<Player> playerList = new LinkedList<Player>();
        for (int i = 0; i < players; i++) {
            playerList.add(new Player(i + 1, 0));
        }
        while (playerList.size() > 1) {
            for (int i = 0; i < syllables - 1; i++) {
                playerList.offer(playerList.poll());
            }
            Player curr = playerList.peek();
            if (curr.getState() == 0) {
                playerList.remove();
                playerList.addFirst(new Player(curr.getPlayer(), 1));
                playerList.addFirst(new Player(curr.getPlayer(), 1));
            } else if (curr.getState() == 1) {
                playerList.offer(new Player(curr.getPlayer(), 2));
                playerList.remove();
            } else {
                playerList.remove();
            }
        }
        System.out.println(playerList.peek());
        sc.close();
    }
}

class Player {
    private int player;
    private int state;

    public Player(int player, int state) {
        this.player = player;
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    public int getPlayer() {
        return this.player;
    }

    public Player setState(int state) {
        return new Player(this.player, state);
    }

    @Override
    public String toString() {
        return "" + this.getPlayer();
    }
}
