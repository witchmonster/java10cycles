package com.jkramr.java10cycles.hackerrank;


import java.util.*;

/**
 * https://www.hackerrank.com/challenges/journey-to-the-moon
 */
class JourneyToTheMoon {
  public static long solve(HashMap<Integer, Astronaut> astronauts, int allNodes) {

    long possibilities = 0;

    TreeSet<Astronaut> unvisited = new TreeSet<>(astronauts.values());

    int nodes = unvisited.size();

    List<Integer> countries = new ArrayList<>();

    while (!unvisited.isEmpty()) {
      Astronaut current = unvisited.first();

      int astronautsFromCountry = dfs(current, unvisited, 0);

      possibilities += possibilitiesForNewCountry(countries, astronautsFromCountry);
    }

    return addSingleNodes(allNodes, possibilities, nodes);
  }

  private static long addSingleNodes(int allNodes, long possibilities, int nodes) {
    int singleNodes = allNodes - nodes;

    for (int i = 0; i < singleNodes; i++) {
      possibilities += nodes + i;
    }

    return possibilities;
  }

  private static long possibilitiesForNewCountry(List<Integer> countries, int country) {
    long newCountryPossibilities = 0;

    for (Integer existingCountry : countries) {
      newCountryPossibilities += existingCountry * country;
    }

    countries.add(country);

    return newCountryPossibilities;
  }

  public static int dfs(
          Astronaut current,
          TreeSet<Astronaut> unvisited,
          int astronautsFromCountry
  ) {
    astronautsFromCountry++;
    unvisited.remove(current);

    for (Astronaut neighbor : current.neighbors) {
      if (unvisited.contains(neighbor)) {
        astronautsFromCountry = dfs(neighbor, unvisited, astronautsFromCountry);
      }
    }

    return astronautsFromCountry;
  }

  public static void main(String[] args) {

    //reading from STDIN
    Scanner inputScanner = new Scanner(System.in);

    String[] nAndP = inputScanner.nextLine().split(" ");

    int n = Integer.parseInt(nAndP[0]);
    int p = Integer.parseInt(nAndP[1]);

    HashMap<Integer, Astronaut> graph = parseStdin(inputScanner, p);
//        HashMap<Integer, Astronaut> graph = mockInput();
//        int n = 10;

    System.out.println(solve(graph, n));
  }

  private static HashMap<Integer, Astronaut> parseStdin(Scanner inputScanner, int p) {
    HashMap<Integer, Astronaut> graph = new HashMap<>();

    for (int i = 0; i < p; i++) {
      String[] inputLine = inputScanner.nextLine().split(" ");

      int a = Integer.parseInt(inputLine[0]);
      int b = Integer.parseInt(inputLine[1]);

      addEdge(graph, a, b);
    }

    return graph;
  }

  private static HashMap<Integer, Astronaut> mockInput() {

    HashMap<Integer, Astronaut> input = new HashMap<>();

    addEdge(input, 4, 3);
    addEdge(input, 3, 0);
    addEdge(input, 1, 2);

    return input;
  }

  private static void addEdge(HashMap<Integer, Astronaut> nodes, int a, int b) {
    nodes.putIfAbsent(a, new Astronaut(a));
    nodes.putIfAbsent(b, new Astronaut(b));

    Astronaut astronaut1 = nodes.get(a);
    Astronaut astronaut2 = nodes.get(b);

    astronaut1.add(astronaut2);
    astronaut2.add(astronaut1);
  }

  static class Astronaut
          implements Comparable<Astronaut> {

    private Integer id;
    private HashSet<Astronaut> neighbors = new HashSet<>();

    public Astronaut(int id) {
      this.id = id;
    }

    public void add(Astronaut astronaut) {
      this.neighbors.add(astronaut);
    }

    @Override
    public int compareTo(Astronaut o) {
      return this.id.compareTo(o.id);
    }
  }

}
