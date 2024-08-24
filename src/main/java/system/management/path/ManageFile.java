package system.management.path;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ManageFile {
    public static Map<String, String> filePaths = new HashMap<String, String>() {
        {
            put("ManagePath","");
            put("ReportPath","");
        }
    };

    public static void initProperties(String path) {
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String[] properties;
            String line;
            while((line=br.readLine())!=null){
                properties = line.split("=");
                if(filePaths.containsKey(properties[0].trim())){
                    if(properties[1].equals("null")){
                        filePaths.put(properties[0].trim(),"");
                    }else{
                        filePaths.put(properties[0].trim(),properties[1].trim());
                    }
                }
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void setPath(String key, String path){
        filePaths.put(key,path);
    }
    public static String getPath(String key){
        return filePaths.get(key);
    }

    public static void saveProperty(String toSavePath){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(toSavePath));
            StringBuilder builder = new StringBuilder();
            for(String key:filePaths.keySet()){
                builder.append(key).append("=").append(filePaths.get(key)).append("\n");
            }
            bw.write(builder.toString());
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
