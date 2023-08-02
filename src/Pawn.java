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
public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn) && chessBoard.board[line][column] != null) { // check that position in board
            if (column == toColumn) { // check that we don't want to eat
                int dir;
                int start;

                if (color.equals("White")) {  // for white piece
                    dir = 1;
                    start = 1;
                } else { // for black piece
                    dir = -1;
                    start = 6;
                }

                if (line + dir == toLine) {  //check direction
                    return chessBoard.board[toLine][toColumn] == null;
                }

                if (line == start && line + 2 * dir == toLine) {
                    return chessBoard.board[toLine][toColumn] == null && chessBoard.board[line + dir][column] == null; // check that positions is null
                }

            } else { // want to eat piece

                if ((column - toColumn == 1 || column - toColumn == -1) && (line - toLine == 1 || line - toLine == -1) && // check that piece another color
                        chessBoard.board[toLine][toColumn] != null) {
                    return !chessBoard.board[toLine][toColumn].getColor().equals(color);
                } else return false;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}