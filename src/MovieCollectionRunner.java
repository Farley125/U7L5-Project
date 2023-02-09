import java.util.ArrayList;

public class MovieCollectionRunner
{
    public static void main(String arg[])
    {
        MovieCollection myCollection = new MovieCollection("src/movies_data.csv");
        myCollection.importCastList("src/output.txt");
        //myCollection.importGenreList("src/genres.txt");
        myCollection.menu();
    }
}