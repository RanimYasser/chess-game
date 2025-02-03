package ChessCore;

public enum Player {
    WHITE,
    BLACK;
    public Player swap() {
        return (this == WHITE) ? BLACK : WHITE;
    }
}
