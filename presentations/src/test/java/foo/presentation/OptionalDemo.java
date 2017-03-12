package foo.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

/**
 * Created by juliakram on 15/06/16.
 */
public class OptionalDemo {

  public static final String              PLAYER1_ID = "player1";
  public static final String              PLAYER2_ID = "player2";
  public static final String              PLAYER3_ID = "player2";
  static              Map<String, Player> players    = new HashMap<>();
  private final       Lock                lock       = new ReentrantLock();
  PlayerRepository repository = new PlayerRepository() {

    @Override
    public Player findById(String id) {
      return players.get(id);
    }
  };

  ;

  {
    players.put(PLAYER1_ID, new Player(PLAYER1_ID, "Mulder", 1000));
    players.put(PLAYER2_ID, new Player(PLAYER2_ID, "Scully", 1001));
  }

  @Test
  public void oldWay() {
    Player player1 = repository.findById(PLAYER1_ID);
    Player player2 = repository.findById(PLAYER2_ID);

    Player player3 = repository.findById(PLAYER3_ID);

    if (player1 == null || player2 == null) {
      System.out.println("No players");
      return;
    }

//      otherwise  NullPointerException
    String name = player1.getName();

    name.toUpperCase();

    sayWhoWon(isWin(player1.getScore(), player2.getScore()));

  }

  public void sayWhoWon(boolean b) {
    System.out.println(
            (b ? PLAYER1_ID : PLAYER2_ID) + " wins");
  }

  @Test
  public void java8Way() {
    Optional<Player> maybePlayer1 = Optional.ofNullable(repository.findById(PLAYER1_ID));
    Optional<Player> maybePlayer2 = Optional.ofNullable(repository.findById(PLAYER2_ID));

    maybePlayer1
            .map(Player::getName)
            .ifPresent(String::toUpperCase);

    maybePlayer1
            .map(Player::getScore)
            .flatMap(isWin(maybePlayer2)) //<- this is bind
            .ifPresent(this::sayWhoWon);
  }

  public Function<Integer, Optional<Boolean>> isWin(Optional<Player> maybePlayer2) {
    return score -> maybePlayer2.map(isWin(score));
  }

  public Function<Player, Boolean> isWin(Integer score) {
    return that -> isWin(score, that.getScore());
  }

  public boolean isWin(Integer thisScore, Integer thatScore) {
    return thisScore >= thatScore;
  }

  private interface PlayerRepository {
    Player findById(String id);
  }

  @Data
  @AllArgsConstructor
  private static class Player {
    private String id;
    private String name;
    private int    score;
  }
}
