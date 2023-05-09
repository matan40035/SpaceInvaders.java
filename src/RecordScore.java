import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecordScore {
    public static File createFile(String path){
        File file = new File(path);
        try {
            boolean success=file.createNewFile();
            if(success){
                System.out.println("file created!");

            }else{
                System.out.println("File already exist");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
    public static void writeToFile(File file ,String date){
try {
    if(file!=null&&file.exists()){
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(date);
        fileWriter.close();
    }

}catch (IOException e){
    e.printStackTrace();
}
    }
    public static List<String> readFromFile(File file)throws IOException{
        List<String> lines = new ArrayList<>();
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            do{
                line=bufferedReader.readLine();
                lines.add(line);
            }while (line!=null);
        }finally {
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            if (fileReader!=null){
                fileReader.close();
            }
        }return lines;
    }

}
