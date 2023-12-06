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
    int length; // in minutes
    List<String> actors;

    public Movie(String title, String director, int releaseYear, int length, List<String> actors) {
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

class DatabaseManager {
    public static void listMovies(Path filePath){
        FileReader fread = null;
        BufferedReader reader = null;
        
        try {
            fread = new FileReader(filePath.toFile());
            reader = new BufferedReader(fread);

            String line;
            List<Movie> movies = new ArrayList<>(); 

            try{
                while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                    String title = columns[0];
                    String director = columns[1];
                    System.out.println(director);
                    int releaseYear = Integer.parseInt(columns[2]);
                    int length = Integer.parseInt(columns[3]);
                    String[] actorNames = columns[4].split(",");
                    Movie movie = new Movie(title, director, releaseYear, length, List.of(actorNames));
                    movies.add(movie);
                } 

                for (Movie movie : movies){
                    System.out.println(movie.title + "; ");
                    System.out.println(movie.releaseYear + "; ");
                    System.out.println(movie.director + "; ");
                }
            
            }catch (IOException e){
                System.err.println("Something is wrong! ");
            }      
            
            
        } catch (FileNotFoundException e) {
            System.err.println("File cannot be opened! ");
        }


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
            DatabaseManager.listMovies(filePath);
        } catch (FileNotFoundException f){
            f.printStackTrace();
            System.err.println("Error opening the file: " + f.getMessage());

            System.exit(1); 
        
        }

        
    }
}
