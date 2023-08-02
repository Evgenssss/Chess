//Задача 8
//Мы почти сделали полноценную игру. У вас уже есть все фигуры, все они правильно ходят, и даже реализована рокировка,
//но есть одно но. Наши фигуры не умеют атаковать, а главное — проходят друг через друга.
//
//Допишите методы canMoveToPosition (у всех фигур) так, чтобы фигура не могла проходить через другую (для этого мы
//передаем в этот метод ChessBoard, у которого есть публичная переменная board). Также давайте сделаем так, чтобы
//фигуры могли есть друг друга. То есть, если мы двигаем белую фигуру на поле, на котором стоит черная, то черная
//фигура просто съедается (съедание фигуры уже реализовано в классе ChessBoard, вам надо сделать только так, чтобы
//метод canMoveToPosition() возвращал true при таком раскладе).
//
//В этот раз сдайте все написанные вами классы в систему проверки (классы ChessPiece, Pawn, Horse, Bishop,
//Rook, Queen, King, ChessBoard).
//
//В итоге мы имеем почти что идеальные шахматы, в которых не реализовано лишь проведение пешки в ферзи, если хотите,
//то можете реализовать это сами. Также консольный интерфейс нашей игры выглядит не очень привлекательно, но написание
//чего-то сложнее потребует гораздо больше времени. Думаю, у нас получились очень неплохие шахматы.
public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // check that we can move to position and can't moved out from board or in not empty position
        if (line != toLine && column != toColumn &&
                getMax(line, toLine) - getMin(line, toLine) == getMax(column, toColumn) - getMin(column, toColumn) &&
                checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn) &&
                (chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&
                chessBoard.board[line][column] != null) {
            if (!chessBoard.board[line][column].equals(this)) {
                return false;
            }
            int dirY = Integer.compare(toLine, line);
            int dirX = Integer.compare(toColumn, column);
            int lineLength = Math.abs(toLine - line);
            for (int i = 1; i < lineLength; i++) {
                if (chessBoard.board[line + i * dirX][column + i * dirY] != null) {
                    return false;
                }
            }
            return true;
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    public int getMax(int a, int b) {
        return Math.max(a, b);
    }

    public int getMin(int a, int b) {
        return Math.min(a, b);
    }

    @Override
    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}