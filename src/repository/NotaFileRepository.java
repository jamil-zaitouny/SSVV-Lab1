package repository;

import domain.Nota;
import java.time.LocalDate;

public class NotaFileRepository extends AbstractFileRepository<String, Nota> {

    /**
     * Class constructor
     * @param filename - The name of the file
     */
    public NotaFileRepository(String filename) {
        super( filename);
    }

    /**
     * Extract grade information from a string
     * @param line - the string that contains the note
     * @return nota
     */
    @Override
    public Nota extractEntity(String line) {
        String[] words = line.split(",");
        String[] data = words[3].split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        return new Nota(words[0]+"#"+words[1], words[0], words[1], Double.parseDouble(words[2]), date);
    }
}
