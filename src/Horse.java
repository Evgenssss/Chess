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
public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {
            if (line != toLine && column != toColumn && (chessBoard.board[toLine][toColumn] == null || // check that horse
                    !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&                   // can't move out
                    chessBoard.board[line][column] != null) {                                          // position is empty
                if (!chessBoard.board[line][column].equals(this)) {
                    return false;
                }

                // all positions for horse
                int[][] positions = new int[][]{
                        {line - 2, column - 1}, {line - 2, column + 1},
                        {line + 2, column - 1}, {line + 2, column + 1},
                        {line - 1, column - 2}, {line - 1, column + 2},
                        {line + 1, column - 2}, {line + 1, column + 2}};

                for (int i = 0; i < positions.length; i++) {
                    if (positions[i][0] == toLine && positions[i][1] == toColumn)
                        return true;  // check that toLine and toColumn
                }                                                                               // in positions
            }
        } else return false;
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    public boolean checkPos(int pos) {   // check that our position is correct
        if (pos >= 0 && pos <= 7) return true;
        else return false;
    }
}