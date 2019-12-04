import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * The purpose of this class is to obtain the next input a program needs.  Each input is
 *    obtained either from a String array (such as the one always accepted by a Java main),
 *    or by prompting the user for the input's value.
 * The class is instantiated with the array.
 * Each call to a method, such as nextInt, tries to find a value in the next unseen index
 *    of the args array.   If such an entry does not exist, or is not of the proper format,
 *    then the user is prompted to supply a value.
 * The prefered manner of obtaining an input is to specify a prompt in case the user
 *    must be approached for the input's value.   For example, if you need the radius of
 *    a circule to compute the circle's area, and "ap" is the variable holding the
 *    ArgsProcessor reference, then you might obtain your input value via:
 *
 *  int radius = ap.nextInt("Circle's radius, in centimeters?");
 *
 *  The ArgsProcessor will first look in the array's next entry for an appropriate value,
 *  but if none exists, it will prompt the user with the specified message.
 *
 * @author roncytron
 *
 */
public class ArgsProcessor {

    private int curArg;
    private String[] args;

    public ArgsProcessor(String[] args) {
        curArg = 0;
        this.args = args;
    }

    /**
     * Prompt-less call
     * @return the next input as an integer
     */
    public int nextInt() {
        return nextInt("An integer");
    }

    /**
     *
     * @param prompt message to be displayed, if necessary
     * @return the next input as a String
     */
    public int nextInt(String prompt) {
        String s = getNextArg(
                prompt,
                new CheckValue() {

                    @Override
                    public boolean check(String s) {
                        try {
                            Integer.parseInt(s);
                            return true;
                        }
                        catch (Throwable t) {
                            return false;
                        }
                    }

                }
        );

        return Integer.parseInt(s);

    }

    /**
     * Prompt-less input
     * @return the next input, as a double
     */
    public double nextDouble() {
        return nextDouble("A double");
    }

    /**
     *
     * @param prompt message to be displayed, if necessary
     * @return the next input as a double
     */
    public double nextDouble(String prompt) {
        String s = getNextArg(
                prompt,
                new CheckValue() {

                    @Override
                    public boolean check(String s) {
                        try {
                            Double.parseDouble(s);
                            return true;
                        }
                        catch (Throwable t) {
                            return false;
                        }
                    }

                }
        );
        return Double.parseDouble(s);
    }

    /**
     * Prompt-less call
     * @return the next input as a String
     */
    public String nextString(){
        return nextString("A String");
    }
    public String nextString(String prompt) {
        return getNextArg(
                prompt,
                new CheckValue() {

                    @Override
                    public boolean check(String s) {
                        return s != null;
                    }

                }
        );
    }

    public boolean nextBoolean() {
        return nextBoolean("A boolean");
    }
    public boolean nextBoolean(String prompt) {
        String s = getNextArg(
                prompt,
                new CheckValue() {

                    @Override
                    public boolean check(String s) {
                        if (s == null)
                            return false;
                        String lc = s.toLowerCase();
                        return lc.equals("true") || lc.equals("false");
                    }

                }
        );
        String lc = s.toLowerCase();
        return lc.equals("true") ? true : false;
    }
    /**
     * Look for the input in args.  If that doesn't work, ask the user
     * @param prompt the message to be displayed if necessary
     * @param v a check to be performed to validate the input
     * @return a valid input, from args or prompted, or null if we are done
     */

    private String getNextArg(String prompt, CheckValue v) {
        String ans = (curArg < args.length) ? args[curArg] : null;

        int num = 0;
        while (!v.check(ans)) {
            Object o =  JOptionPane.showInputDialog(
                    null,
                    prompt,
                    "Parameter " + (curArg),
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    null
            );
            if (o == null)
                return null;
            else
                ans = o.toString();

        }
        ++curArg;
        return ans;
    }

    /**
     * Redirect input to this program by a file chosen from the project
     */
    public static void useStdInput() {
        useStdInput(System.getProperty("user.dir"),"");
    }

    /**
     * Redirect input from the specified subdirectory (e.g., music) of the project
     * @param subdir
     */
    public static void useStdInput(String subdir) {
        useStdInput(System.getProperty("user.dir"), subdir);
    }

    /**
     * Helper method to redirect standard input, using the top directory and the specified
     *    subdirectory (e.g., sound)
     * @param top
     * @param subdir
     */
    private static void useStdInput(String top, String subdir) {
        try {
            File file = chooseFile(top,subdir);
            if (file == null){
                throw new Error("Did not select a file");
            }else{
                FileInputStream in = new FileInputStream(file);
                System.setIn(in);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Pick a file from the project
     * @return the chosen file
     */

    public static File chooseFile() {
        return chooseFile(System.getProperty("user.dir"));
    }

    /**
     * Pick a file from a subdirectory (e.g., music) of the project
     * @param subdir specified subdirectory to begin the file-choosing
     * @return the chosen file
     */
    public static File chooseFile(String subdir) {
        return chooseFile(System.getProperty("user.dir"), subdir);
    }

    /**
     * Helper method to choose a file
     * @param topdir Directory to start looking
     * @param subdir A subdirectory just below (e.g., sound)
     * @return the chosen file
     */
    private static File chooseFile(String topdir, String subdir) {
        String directory = topdir + "/" + subdir;
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(directory));
        fc.showOpenDialog(null);
        return fc.getSelectedFile();
    }

    public static void useStdOutput(String subdir) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")+"/"+subdir));
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                PrintStream ps = new PrintStream(new FileOutputStream(chooser.getSelectedFile()));
                System.setOut(ps);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

}
