/**
 * Created by Adam on 10/7/2015.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class simpleMIPSAssembler {
    public static void main(String[] args){
        System.out.println("Hello World");
        simpleMIPSAssembler sma = new simpleMIPSAssembler();
        IType iType = new IType();
        ArrayList<String> list = sma.fileRead("test.txt");
        List<Integer> listInt;
        ConversionH cH = new ConversionH();
        for(int i = 0; i<list.size();i++){
            String line = list.get(i);
            listInt = iType.theType(line,iType.checkEncode(line));

            /*
            for(int j=0;j<listInt.size();j++){
                System.out.println(listInt.get(j));
            }
            */
            System.out.println(cH.listToHex(listInt,iType.checkEncode(line)));
        }





    }
    public ArrayList<String> fileRead(String fileName) {

        /*
         * This takes the filename for the file you are trying to read in.
          *Then takes each line and adds it
         * to a list.
         *
         * Returns: A list with each line being a new entry.
         */
        String line = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");

        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return list;
    }
    public char checkType(String line) {
        /*
         * This method takes in a line. Then finds the index of the first '\n'
         * creates a substring from that index. That substring is then checked against
         * all of the available mips instructions.
         * Returns: char i
         *          char r
         *          char j
         *          char u - for unknown
         */
        int index;
        List<String> iType = Arrays.asList("addi", "slti", "andi", "ori", "lw", "sw", "beq");
        List<String> rType = Arrays.asList("add", "sub", "slt", "and", "or", "sll", "slr","jr");
        List<String> jType = Arrays.asList("j", "jal");

        if (line.indexOf(' ') != -1) {
            index = line.indexOf(' ');
        } else {
            return 'u';
        }
        String checkStr = line.substring(0, index);
        if (iType.contains(checkStr)) {
            return 'i';
        }
        if (rType.contains(checkStr)) {
            return 'r';
        }
        if (jType.contains(checkStr)) {
            return 'j';
        }
        return 'u';
    }
    public String getLine(ArrayList<String> list, int index){
        return list.get(index);
    }

}
