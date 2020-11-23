package biolab;

import app.Login;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Marlon Call
 */
public class BioLab {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        if (System.getProperty("os.name").equals("Linux")) {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());                    
                    break;
                }
            }
        } else {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        Login login = new Login();
        login.setVisible(true);
    }

}
