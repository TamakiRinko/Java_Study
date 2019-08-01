import javax.swing.*;
import java.time.Clock;

public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        TalkingClock talkingClock = new TalkingClock();
        talkingClock.start(1000, true);

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
