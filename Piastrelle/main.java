import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        try (final Scanner s = new Scanner(System.in)) {
            final List<Rivestimento> rivestimento = new ArrayList<>();
            final List<Pavimentazione> pavimentazione = new ArrayList<>();

            while (s.hasNextLine()) {
                try (final Scanner line = new Scanner(s.nextLine())) {
                    System.out.println();
                    switch (line.next().charAt(0)) {
                        case 'Q':
                            rivestimento.add(new Quadrata(line.nextInt(), line.nextInt()));
                            break;
                        case 'R':
                            rivestimento.add(
                                    new Romboidale(line.nextInt(), line.nextInt(), line.nextInt()));
                            break;
                        case 'T':
                            rivestimento.add(
                                    new Triangolare(line.nextInt(), line.nextInt(), line.nextInt()));
                            break;
                        case 'P':
                            final List<Pavimentazione.Elemento> elementi = new ArrayList<>();
                            while (line.hasNextInt())
                                elementi.add(
                                        new Pavimentazione.Elemento(
                                                line.nextInt(), rivestimento.get(line.nextInt())));
                            final Pavimentazione p = new Pavimentazione(elementi);
                            pavimentazione.add(p);
                            rivestimento.add(p);
                            break;
                        default:
                            System.out.println("input non valido");
                    }
                }
                for (final Pavimentazione p : pavimentazione){
                    System.out.println(p.getSuperficie() + "\t" + p.getCosto());
                }
            }
        }
    }
}
