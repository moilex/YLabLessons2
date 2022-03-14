import java.io.File;
import java.io.FilenameFilter;

public class FileFilter {
    private final String dir = System.getProperty("user.dir");
    private final String ext = ".xml";


    public void findFiles() {
        File file = new File(dir);
        if(!file.exists()) System.out.println(dir + " папка не существует");
        File[] listFiles = file.listFiles(new MyFileNameFilter(ext));
        if(listFiles.length == 0){
            System.out.println(dir + " не содержит файлов с расширением " + ext);
        }else{
            for(File f : listFiles)
                System.out.println("Игра: " + f.getName());
        }
    }


    private class MyFileNameFilter implements FilenameFilter {

        private String ext;

        public MyFileNameFilter(String ext){
            this.ext = ext.toLowerCase();
        }
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(ext);
        }
    }
}
