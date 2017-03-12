package foo.presentation;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Created by juliakram on 15/06/16.
 */
public class CompletableFutureDemo {

  public static final String PLAYER1 = "Player1";
  public static final String PLAYER2 = "Player2";
  public static final String PLAYER3 = "Player3";

  Tournament tournament = new Tournament();
  private Logger log;

  public void will_register_player_java7(TestContext context) {
    Async async = context.async();

    tournament.addPlayer(new Player(1000, PLAYER1), new Consumer<AsyncResult<String>>() {

      @Override
      public void accept(AsyncResult<String> stringAsyncResult) {
        tournament.addPlayer(new Player(1001, PLAYER2), new Consumer<AsyncResult<String>>() {

          @Override
          public void accept(AsyncResult<String> stringAsyncResult) {
            tournament.addPlayer(new Player(1002, PLAYER3), new Consumer<AsyncResult<String>>() {

              @Override
              public void accept(AsyncResult<String> stringAsyncResult) {
                log.info("All players has been added");
                tournament.registerPlayer(new Consumer<AsyncResult<JsonArray>>() {

                  @Override
                  public void accept(AsyncResult<JsonArray> resultHandler) {
                    log.info("Looking for results");
                    int registerdPlayers = resultHandler.result().size();
                    context.assertEquals(registerdPlayers, 3, "All Players were registered");
                    async.complete();
                  }
                });
              }
            });
          }
        });
      }
    });
  }


  public void will_register_player_lambda(TestContext context) {
    Async async = context.async();

    tournament.addPlayer(new Player(1000, PLAYER1), (AsyncResult<String> player1Handler) ->
            tournament.addPlayer(
                    new Player(1001, PLAYER2),
                    (AsyncResult<String> player2Handler) ->
                            tournament.addPlayer(
                                    new Player(1002, PLAYER3),
                                    (AsyncResult<String> player3Handler) -> {
                                      log.info("All players has been added");
                                      tournament.registerPlayer((AsyncResult<JsonArray> resultHandler) -> {
                                        log.info("Looking for results");
                                        int
                                                count
                                                = resultHandler.result()
                                                               .size();
                                        context.assertEquals(
                                                count,
                                                3,
                                                "All Players were registered"
                                        );
                                        async.complete();
                                      });
                                    }
                            )
            )
    );
  }

  public void will_register_player_CF(TestContext context) {
    Async async = context.async();

    tournament.addPlayer(new Player(1000, PLAYER1))
              .thenCompose(v -> tournament.addPlayer(new Player(1001, PLAYER2)))
              .thenCompose(v -> tournament.addPlayer(new Player(1002, PLAYER3)))
              .thenCompose(v -> tournament.registerPlayer())
              .thenAccept(count -> {
                context.assertEquals(count, 3, "All Player were registerd");
                async.complete();
              });
  }

  private class Async {
    public void complete() {
    }
  }

  private class TestContext {
    public Async async() {
      return null;
    }

    public void assertEquals(int registerdPlayers, int i, String s) {

    }
  }

  private class Tournament {

    private RClient redisClient;

    void addPlayer(Player player, Consumer<AsyncResult<String>> handler) {
      Map<String, String> hmset = new HashMap<>();
      hmset.put("name", player.getName());
      redisClient.hmset("PLAYERS:" + player.getId(), hmset, handler);
    }

    CompletableFuture<Void> addPlayer(Player p) {
      CompletableFuture<Void> promise = new CompletableFuture<>();
      Map<String, String>     hmset   = new HashMap<>();
      hmset.put("name", p.getName());
      redisClient.hmset("PLAYERS:" + p.getId(), hmset, (AsyncResult<String> event) -> {
        promise.complete(null);
      });
      return promise;
    }

    public void registerPlayer(Consumer<AsyncResult<JsonArray>> handler) {

    }

    public CompletableFuture<Integer> registerPlayer() {
      return null;
    }
  }

  private class AsyncResult<T> {
    public ArrayList<Player> result() {
      return null;
    }
  }

  @Getter
  @AllArgsConstructor
  private class Player {
    private int id;
    private String name;
  }

  private class JsonArray {
  }

  private class RClient {
    public void hmset(
            String s,
            Map<String, String> hmset,
            Consumer<AsyncResult<String>> handler
    ) {

    }
  }
}