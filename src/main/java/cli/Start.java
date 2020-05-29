package cli;

/**
 * Created by Poliakov.A on 1/16/2018.
 */
public class Start {
    public static void main (String... args) {
        ReferenceUpdater referenceUpdater = new ReferenceUpdater(args);
        referenceUpdater.updateReference();

    }
}
