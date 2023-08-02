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
public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {
            if (Math.abs(line - toLine) > 1 || Math.abs(column - toColumn) > 1) return false;

            if (isUnderAttack(chessBoard, toLine, toColumn)) return false;

            if (chessBoard.board[toLine][toColumn] != null) {
                return !chessBoard.board[toLine][toColumn].getColor().equals(color);
            }

            return true;
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        if (checkPos(line) && checkPos(column)) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if (chessBoard.board[i][j] != null) {
                        if (!chessBoard.board[i][j].getColor().equals(color) && chessBoard.board[i][j].canMoveToPosition(chessBoard, i, j, line, column)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else return false;
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}