import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

class Person {
    String name;
    int birthYear;

    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }
}

class Movie {
    String title;
    String director;
    int releaseYear;
    String length; // in minutes
    List<String> actors;

    public Movie(String title, String director, int releaseYear, String length, List<String> actors) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.length = length;
        this.actors = actors;
    }
}

class FileModifier {
    public void modifyFile(Path filePath){
        try {
            String fileContent = Files.readString(filePath);
            Files.writeString(filePath, fileContent);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

class ProcessingInputToObjects {
    public static List<Movie> listMovies(Path filePath) {
        List<Movie> movies = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(";");
                    String title = columns[0];
                    String director = columns[1];
                    int releaseYear = Integer.parseInt(columns[2]);
                    String length = columns[3];
                    String[] actorNames = columns[4].split(",");
                    Movie movie = new Movie(title, director, releaseYear, length, List.of(actorNames));
                    movies.add(movie);
            }

        } catch (FileNotFoundException e) {
            System.err.println("File cannot be opened! ");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            System.err.println("Something is wrong! " + e.getMessage());
        }

        return movies;
    }
}



public class moviesNokia{
    private static List<Person> people = new ArrayList<>();
    private static List<Movie> movies = new ArrayList<>();

    public static void main(String[] args) {
    
        Path filePath = Paths.get("file.txt");
        
        FileReader fread = null; 

        try {
            fread = new FileReader(filePath.toFile());
            System.out.println("Console App has been connected to DataBase Successfully!");

            List<Movie> movieList = ProcessingInputToObjects.listMovies(filePath);

            for (Movie movie : movieList) {
                System.out.println("Title: " + movie.title);
                System.out.println("Director: " + movie.director);
                System.out.println("Release Year: " + movie.releaseYear);
                System.out.println("Length: " + movie.length);
                System.out.println("Actors: " + String.join(", ", movie.actors));
                System.out.println(); // Add a newline for better readability
            }
        } catch (FileNotFoundException f){
            f.printStackTrace();
            System.err.println("Error opening the file: " + f.getMessage());

            System.exit(1); 
        
        }

        
    }
}
