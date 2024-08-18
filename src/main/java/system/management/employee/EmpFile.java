package system.management.employee;

import java.io.*;

public class EmpFile {
    private static final String key = "EmpPath";
    private static String path;

    public static void initProperties(String path) {
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String[] properties;
            String line;
            while((line=br.readLine())!=null){
                properties = line.split("=");
                if(properties[0].trim().equals(key)){
                    if(properties[1].equals("null")){
                        EmpFile.path="";
                    }else{
                        EmpFile.path = properties[1];
                    }
                    break;
                }
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void setPath(String path){
        EmpFile.path = path;
    }
    public static String getPath(){
        return EmpFile.path;
    }

    public static void saveProperty(String toSavePath){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(toSavePath));
            String property = String.format("%s=%s",key,path);
            bw.write(property);
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
