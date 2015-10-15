/**
 * Created by Adam on 10/7/2015.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class simpleMIPSAssembler {
    public static void main(String[] args){
        System.out.println("Please Enter the text file you wish to convert");
        Scanner in = new Scanner(System.in);
        String filename = in.next();
        filename = filename.trim();


        simpleMIPSAssembler sma = new simpleMIPSAssembler();
        List<String> output = sma.makeOutput(filename);
        try {
            sma.writer(output);
        }catch(IOException e){
            System.out.println("Error: "+e);
        }
        System.out.println("The  file is outputed as output.txt");
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
        List<String> rType = Arrays.asList("add", "sub", "slt", "and", "or", "sll","srl", "s","jr");
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
    public List<String> makeOutput(String fileName){
        IType iType = new IType();
        RType rType = new RType();
        JType jType = new JType();
        simpleMIPSAssembler sma = new simpleMIPSAssembler();
        ArrayList<String> listOfInput = sma.fileRead(fileName);
        List<String> listOfOutput = new ArrayList<>();
        ConversionH cH = new ConversionH();

        for(int i=0; i<listOfInput.size();i++){
            String line = listOfInput.get(i);

            if(sma.checkType(listOfInput.get(i)) == 'r'){

                listOfOutput.add(cH.listToHex(rType.theType(line,rType.checkEncode(line)),'r'));
            }else if(sma.checkType(listOfInput.get(i))=='i'){

                listOfOutput.add(cH.listToHex(iType.theType(line,iType.checkEncode(line)),'i'));

            }else if(sma.checkType(listOfInput.get(i))=='j'){

                listOfOutput.add(cH.listToHex(jType.theType(line,jType.checkEncode(line)),'j'));

            }else{
                listOfOutput.add(line);

            }
        }
        return listOfOutput;


    }

    //writer for outputing hex to a file
    public void writer(List<String> toStore) throws IOException{
        //creates writer and clears file
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        //for each loop to store all the values in the file into the file named output.txt
        for(String s : toStore)
            //stores the strings for outputting
            writer.println(s);
        //closes the writer and outputs to the file
        writer.close();
    }

}
