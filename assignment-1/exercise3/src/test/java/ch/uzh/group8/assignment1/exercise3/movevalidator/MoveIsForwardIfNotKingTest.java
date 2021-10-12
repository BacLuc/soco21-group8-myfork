package ch.uzh.group8.assignment1.exercise3.movevalidator;

import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ch.uzh.group8.assignment1.exercise3.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoveIsForwardIfNotKingTest {

  public static final Piece WHITE_PAWN = new Piece(Player.PLAYER_WHITE, false);
  public static final Piece RED_PAWN = new Piece(Player.PLAYER_RED, false);
  public static final Piece WHITE_KING = new Piece(Player.PLAYER_WHITE, true);

  private MoveIsForwardIfNotKing moveIsForwardIfNotKing;
  private Board board;

  @BeforeEach
  public void setup() {
    moveIsForwardIfNotKing = new MoveIsForwardIfNotKing();
    board = mock(Board.class);
  }

  @Test
  public void return_true_if_there_is_no_piece() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.empty());
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.B));

    assertThat(moveIsForwardIfNotKing.validate(move, board), is(true));
  }

  @Test
  public void return_true_if_row_diff_is_0() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.of(WHITE_PAWN));
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_1, Column.B));

    assertThat(moveIsForwardIfNotKing.validate(move, board), is(true));
  }

  @Test
  public void return_true_if_piece_is_king() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.of(WHITE_KING));
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_1, Column.B));

    assertThat(moveIsForwardIfNotKing.validate(move, board), is(true));
  }

  @Test
  public void return_true_if_white_player_makes_simple_move() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.of(WHITE_PAWN));
    var move =
        new Move(
            Player.PLAYER_WHITE,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.B));

    assertThat(moveIsForwardIfNotKing.validate(move, board), is(true));
  }

  @Test
  public void return_true_if_white_player_makes_jump_move() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.of(WHITE_PAWN));
    var move =
        new Move(
            Player.PLAYER_WHITE,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_3, Column.C));

    assertThat(moveIsForwardIfNotKing.validate(move, board), is(true));
  }

  @Test
  public void return_false_if_white_player_moves_back() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.of(WHITE_PAWN));
    var move =
        new Move(
            Player.PLAYER_WHITE,
            new BoardCoordinates(Row.ROW_2, Column.B),
            new BoardCoordinates(Row.ROW_1, Column.A));

    assertThat(moveIsForwardIfNotKing.validate(move, board), is(false));
  }

  @Test
  public void return_true_if_red_player_makes_simple_move() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.of(RED_PAWN));
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_2, Column.B),
            new BoardCoordinates(Row.ROW_1, Column.A));

    assertThat(moveIsForwardIfNotKing.validate(move, board), is(true));
  }

  @Test
  public void return_true_if_red_player_makes_jump_move() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.of(RED_PAWN));
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_3, Column.C),
            new BoardCoordinates(Row.ROW_1, Column.A));

    assertThat(moveIsForwardIfNotKing.validate(move, board), is(true));
  }

  @Test
  public void return_false_if_red_player_moves_back() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.of(RED_PAWN));
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.B));

    assertThat(moveIsForwardIfNotKing.validate(move, board), is(false));
  }
}
