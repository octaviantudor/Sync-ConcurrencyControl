import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Utils {
    public void generate(String filename, Integer size, Integer min, Integer max){
        try(PrintWriter writer = new PrintWriter(filename)) {
            Random random = new Random();
            for(int i=0; i<size; i++){
                Integer nr = random.ints(min, max).findFirst().getAsInt();
                Integer exp = random.ints(min, max).findFirst().getAsInt();
                writer.println(nr+" "+exp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean checkEqualsInt(String f1, String f2){
        try(BufferedReader bf = new BufferedReader(new FileReader(f1))) {
            ArrayList<Integer> l1 = new ArrayList<>();
            String line = bf.readLine();
            while (line != null && !line.equals("")){
                String args[] = line.split(" ");
                Integer nr1 = Integer.parseInt(args[0]);
                Integer nr2 = Integer.parseInt(args[1]);
                l1.add(nr1);
                l1.add(nr2);
                line = bf.readLine();
            }
            try(BufferedReader bf2 = new BufferedReader((new FileReader(f2)))) {
                ArrayList<Integer> l2 = new ArrayList<>();
                line = bf2.readLine();
                while (line != null && !line.equals("")){
                    String args[] = line.split(" ");
                    Integer nr1 = Integer.parseInt(args[0]);
                    Integer nr2 = Integer.parseInt(args[1]);
                    l2.add(nr1);
                    l2.add(nr2);
                    line = bf2.readLine();
                }
                if(l1.size() != l2.size()) {
                    if(l1.size() > l2.size()){
                        for(int i=0; i<l2.size(); i++){
                            if(!l1.get(i).equals(l2.get(i))){
                                System.out.println(l1.get(i));
                                return false;
                            }
                        }
                    }else {
                        for(int i=0; i<l1.size(); i++){
                            if(!l1.get(i).equals(l2.get(i))){
                                System.out.println(l2.get(i));
                                return false;
                            }
                        }
                    }
                    return false;
                }
                for(int i=0; i<l1.size(); i++)
                    if(!l1.get(i).equals(l2.get(i))) {
                        System.out.println("MISSING: " + i);
                        return false;
                    }
                return true;
            }

        }catch (FileNotFoundException ex){
            System.out.println("File not found");
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean checkEqualsDouble(String f1, String f2){
        try(BufferedReader bf = new BufferedReader(new FileReader(f1))) {
            ArrayList<Double> l1 = new ArrayList<>();
            String line = bf.readLine();
            while (line != null && !line.equals("")){
                Double nr = Double.parseDouble(line);
                l1.add(nr);
                line = bf.readLine();
            }
            try(BufferedReader bf2 = new BufferedReader((new FileReader(f2)))) {
                ArrayList<Double> l2 = new ArrayList<>();
                line = bf2.readLine();
                while (line != null && !line.equals("")){
                    Double nr = Double.parseDouble(line);
                    l2.add(nr);
                    line = bf2.readLine();
                }
                if(l1.size() != l2.size())
                    return false;
                for(int i=0; i<l1.size(); i++)
                    if(!l1.get(i).equals(l2.get(i)))
                        return false;
                return true;
            }

        }catch (FileNotFoundException ex){
            System.out.println("File not found");
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
