package ch.uzh.group8.assignment1.exercise3.movevalidator;

import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.*;

import ch.uzh.group8.assignment1.exercise3.Board;
import ch.uzh.group8.assignment1.exercise3.BoardCoordinates;
import ch.uzh.group8.assignment1.exercise3.Move;

public class OpponentPieceBetweenJump implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    var start = move.start();
    var end = move.end();

    var rowDiff = end.row().ordinal() - start.row().ordinal();
    var colDiff = end.column().ordinal() - start.column().ordinal();

    if (Math.abs(rowDiff) != 2 || Math.abs(colDiff) != 2) {
      return true;
    }

    var rows = Row.values();
    Row rowBetween;
    if (rowDiff > 0) {
      rowBetween = rows[start.row().ordinal() + 1];
    } else {
      rowBetween = rows[start.row().ordinal() - 1];
    }

    var columns = Column.values();
    Column colBetween;
    if (colDiff > 0) {
      colBetween = columns[start.column().ordinal() + 1];
    } else {
      colBetween = columns[start.column().ordinal() - 1];
    }

    var pieceAt = board.getPieceAt(new BoardCoordinates(rowBetween, colBetween));
    if (pieceAt.isEmpty()) {
      return false;
    }
    var pieceBetween = pieceAt.get();
    return pieceBetween.owner() != move.player();
  }
}
