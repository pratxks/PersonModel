import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.*;

public class PersonReader
{
    static ArrayList<String> PersonRecordArray = new ArrayList<String>();

    public static void ReadFileDataToArrayList()
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        PersonRecordArray.clear();

        try
        {
            // uses a fixed known path:
            //  Path file = Paths.get("c:\\My Documents\\data.txt");

            // use the toolkit to get the current working directory of the IDE
            // Not sure if the toolkit is thread safe...
            File workingDirectory = new File(System.getProperty("user.dir"));

            // Typiacally, we want the user to pick the file so we use a file chooser
            // kind of ugly code to make the chooser work with NIO.
            // Because the chooser is part of Swing it should be thread safe.
            chooser.setCurrentDirectory(workingDirectory);
            // Using the chooser adds some complexity to the code.
            // we have to code the complete program within the conditional return of
            // the filechooser because the user can close it without picking a file

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                // Typical java pattern of inherited classes
                // we wrap a BufferedWriter around a lower level BufferedOutputStream
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                // Finally we can read the file LOL!
                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    PersonRecordArray.add(rec);
                    line++;
                    // echo to screen
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }

                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void DisplayPersonRecords()
    {
        //ID#           Firstname     Lastname       Title    YOB
        String strHeader = String.format("%-11s","ID#");
        strHeader += String.format("%-20s", "Firstname");
        strHeader += String.format("%-20s", "Lastname");
        strHeader += String.format("%-10s", "Title");
        strHeader += String.format("%-10s", "YOB");

        System.out.println(strHeader);
        String strUnderLine = String.format("%71s", " ").replace(' ', '=');
        System.out.println(strUnderLine);

        for(String rec : PersonRecordArray)
        {
            String [] arrRecordFields = rec.split(",");
            String strDisplayRec = "";

            for(int i=0; i<arrRecordFields.length; i++)
            {
                String strRecField = arrRecordFields[i];

                if(i == 0)
                {
                    strRecField = String.format("%6s", strRecField).replace(' ', '0');
                    strRecField += String.format("%4s", " ");
                }
                if((i > 0) && (i < 3))
                {
                    strRecField = String.format("%-20s", strRecField);
                }
                if((i > 2) && (i < 5))
                {
                    strRecField = String.format("%-10s", strRecField);
                }

                strDisplayRec += strRecField;
            }

            System.out.println(strDisplayRec);
        }
    }

    public static void main(String[] args)
    {
        ReadFileDataToArrayList();

        DisplayPersonRecords();
    }
}
