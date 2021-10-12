package ch.uzh.group8.assignment1.exercise3.movevalidator;

import ch.uzh.group8.assignment1.exercise3.Board;
import ch.uzh.group8.assignment1.exercise3.Move;
import ch.uzh.group8.assignment1.exercise3.Player;

public class MoveIsForwardIfNotKing implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    var pieceOptional = board.getPieceAt(move.start());
    if (pieceOptional.isEmpty()) {
      // this is for another validator to check
      return true;
    }

    var piece = pieceOptional.get();
    if (piece.isKing()) {
      return true;
    }
    var rowDiff = move.end().row().ordinal() - move.start().row().ordinal();
    if (rowDiff == 0) {
      // this is for another validator to check
      return true;
    }
    if (move.player() == Player.PLAYER_WHITE) {
      return rowDiff > 0;
    } else {
      return rowDiff < 0;
    }
  }
}
