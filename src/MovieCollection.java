import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class MovieCollection {
    private ArrayList<Movie> movies;
    private Scanner scanner;
    private static ArrayList<String> allCastMembers = new ArrayList<String>();
    private static String[] allMovieWithGenres = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", };
    private final String[] ALLGENRES = {"Thriller", "Action", "Drama", "War", "Comedy", "Romance", "Family", "Fantasy", "Science Fiction", "Crime", "Mystery", "Adventure", "TV Movie", "History", "Horror", "Documentary", "Western", "Music", "Animation", "Foreign"};

    public MovieCollection(String fileName) {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
       sortResults(movies);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void menu() {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q")) {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option) {
        if (option.equals("t")) {
            searchTitles();
        } else if (option.equals("c")) {
            searchCast();
        } else if (option.equals("k")) {
            searchKeywords();
        } else if (option.equals("g")) {
            listGenres();
        } else if (option.equals("r")) {
            listHighestRated();
        } else if (option.equals("h")) {
            listHighestRevenue();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles() {
        System.out.print("Enter a title search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++) {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1) {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++) {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort) {
        for (int j = 1; j < listToSort.size(); j++) {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0) {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie) {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast() {
        // arraylist to hold cast members
        ArrayList<String> results = new ArrayList<String>();
        System.out.print("Enter a Cast search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();
        for (String string : allCastMembers) {
            if (string.toLowerCase().contains(searchTerm)) {
                results.add(string);
            }
        }
        for (int i = 0; i < results.size(); i++) {
            String castMember = results.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + castMember);
        }
        System.out.print("Select an actor (number): ");
        String select = scanner.nextLine();

        String chosenActor = results.get(Integer.parseInt(select)-1);
        int i = 1;
        for (Movie movie : movies) {
            if (movie.getCast().contains(chosenActor)) {
                System.out.println("" + i + ". " + movie.getTitle());
                i++;
            }
        }
    }

        public static String[] returnGenres() {
        return allMovieWithGenres;
        }
        private void searchKeywords() {
            System.out.print("Enter a keyword search term: ");
            String searchTerm = scanner.nextLine();

            // prevent case sensitivity
            searchTerm = searchTerm.toLowerCase();

            // arraylist to hold search results
            ArrayList<Movie> results = new ArrayList<Movie>();

            // search through ALL movies in collection
            for (int i = 0; i < movies.size(); i++) {
                String movieKeywords = movies.get(i).getKeywords();
                movieKeywords = movieKeywords.toLowerCase();

                if (movieKeywords.indexOf(searchTerm) != -1) {
                    //add the Movie objest to the results list
                    results.add(movies.get(i));
                }
            }

            // sort the results by title
            sortResults(results);

            // now, display them all to the user
            for (int i = 0; i < results.size(); i++) {
                String title = results.get(i).getTitle();

                // this will print index 0 as choice 1 in the results list; better for user!
                int choiceNum = i + 1;

                System.out.println("" + choiceNum + ". " + title);
            }

            System.out.println("Which movie would you like to learn more about?");
            System.out.print("Enter number: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            Movie selectedMovie = results.get(choice - 1);

            displayMovieInfo(selectedMovie);

            System.out.println("\n ** Press Enter to Return to Main Menu **");
            scanner.nextLine();
        }

        private void listGenres () {
        for (Movie movie : movies) {
            String[] genres = movie.getGenres().split("\\|");
            for (String g : genres) {
                    switch (g.toLowerCase()) {
                        case "thriller" -> allMovieWithGenres[0] = allMovieWithGenres[0] + movie.getTitle() + "|";
                        case "action" -> allMovieWithGenres[1] = allMovieWithGenres[1] + movie.getTitle() + "|";
                        case "drama" -> allMovieWithGenres[2] = allMovieWithGenres[2] + movie.getTitle() + "|";
                        case "war" -> allMovieWithGenres[3] = allMovieWithGenres[3] + movie.getTitle() + "|";
                        case "comedy" -> allMovieWithGenres[4] = allMovieWithGenres[4] + movie.getTitle() + "|";
                        case "romance" -> allMovieWithGenres[5] = allMovieWithGenres[5] + movie.getTitle() + "|";
                        case "family" -> allMovieWithGenres[6] = allMovieWithGenres[6] + movie.getTitle() + "|";
                        case "fantasy" -> allMovieWithGenres[7] = allMovieWithGenres[7] + movie.getTitle() + "|";
                        case "science fiction" -> allMovieWithGenres[8] = allMovieWithGenres[8] + movie.getTitle() + "|";
                        case "crime" -> allMovieWithGenres[9] = allMovieWithGenres[9] + movie.getTitle() + "|";
                        case "mystery" -> allMovieWithGenres[10] = allMovieWithGenres[10] + movie.getTitle() + "|";
                        case "adventure" -> allMovieWithGenres[11] = allMovieWithGenres[11] + movie.getTitle() + "|";
                        case "tv movie" -> allMovieWithGenres[12] = allMovieWithGenres[12] + movie.getTitle() + "|";
                        case "history" -> allMovieWithGenres[13] = allMovieWithGenres[13] + movie.getTitle() + "|";
                        case "horror" -> allMovieWithGenres[14] =  allMovieWithGenres[14] + movie.getTitle() + "|";
                        case "documentary" -> allMovieWithGenres[15] = allMovieWithGenres[15] + movie.getTitle() + "|";
                        case "western" -> allMovieWithGenres[16] = allMovieWithGenres[16] + movie.getTitle() + "|";
                        case "music" -> allMovieWithGenres[17] = allMovieWithGenres[17] + movie.getTitle() + "|";
                        case "animation" -> allMovieWithGenres[18] = allMovieWithGenres[18] + movie.getTitle() + "|";
                        case "foreign" -> allMovieWithGenres[19] = allMovieWithGenres[19] + movie.getTitle() + "|";
                    }
            }
        }
        for (int i = 0; i < allMovieWithGenres.length; i++) {
            System.out.println(allMovieWithGenres[i]);
        }
        //FileCreator.createFile();
        }

        private void listHighestRated ()
        {

        }

        private void listHighestRevenue ()
        {

        }

        private void importMovieList (String fileName)
        {
            try {
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();

                movies = new ArrayList<Movie>();

                while ((line = bufferedReader.readLine()) != null) {
                    String[] movieFromCSV = line.split(",");

                    String title = movieFromCSV[0];
                    String cast = movieFromCSV[1];
                    String director = movieFromCSV[2];
                    String tagline = movieFromCSV[3];
                    String keywords = movieFromCSV[4];
                    String overview = movieFromCSV[5];
                    int runtime = Integer.parseInt(movieFromCSV[6]);
                    String genres = movieFromCSV[7];
                    double userRating = Double.parseDouble(movieFromCSV[8]);
                    int year = Integer.parseInt(movieFromCSV[9]);
                    int revenue = Integer.parseInt(movieFromCSV[10]);

                    Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                    movies.add(nextMovie);
                }
                bufferedReader.close();
            } catch (IOException exception) {
                // Print out the exception that occurred
                System.out.println("Unable to access " + exception.getMessage());
            }
        }

        public void importCastList(String fileName) {
            try {
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();


                while ((line = bufferedReader.readLine()) != null) {
                    allCastMembers.add(line);
                }
                bufferedReader.close();
            } catch (IOException exception) {
                // Print out the exception that occurred
                System.out.println("Unable to access " + exception.getMessage());
            }
        }

    public void importGenreList(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i = 0;
            while ((bufferedReader.readLine()) != null) {
                String line = bufferedReader.readLine();
                ALLGENRES[i] = line;
                i++;
                System.out.println("cock");
            }
            bufferedReader.close();
        } catch (IOException exception) {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
    }