import java.io.*;

public class FileUtils {


    public static void main(String[] args) throws IOException {

        //Test
        FileUtils.fileCopy("D:\\Home\\Desk\\Test\\BaiSe\\123AAA.txt",
                "D:\\Home\\Desk\\Test\\BaiSe\\123BBB.txt");

    }


    /**
     * 拷贝文件夹
     *
     * @param srcPath
     * @param destPath
     */

    public static void copyDir(String srcPath, String destPath) {

        File src = new File(srcPath);
        File dest = new File(destPath);

        if(src.isDirectory()){//文件夹
            dest=new File(dest,src.getName());//在目标路径下面创建一个和源文件夹一样的文件夹名
            if(dest.getAbsolutePath().contains(src.getAbsolutePath())){
                return;
            }
        }
        copyDir(src, dest);
    }

    /**
     * 拷贝文件夹
     *
     * @param src
     * @param dest
     */
    public static void copyDir(File src, File dest) {
        if (src.isFile()) {// 文件
            try {
                FileUtils.fileCopy(src, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (src.isDirectory()) {// 文件夹或者目录
            // 确保目标文件夹存在
            dest.mkdirs();
            // 获取下一级目录|文件
            for (File sub : src.listFiles()) {
                copyDir(sub, new File(sub.getName()));
            }
        }

    }

    /**
     * 文件拷贝
     *
     * @param fromPath
     *            file对象
     * @param toPath
     *            file对象
     * @throws IOException
     */
    public static void fileCopy(File fromPath, File toPath) throws IOException {
        if (!fromPath.isFile()) {
            throw new IOException("只能拷贝文件！！！");
        }
        //dest是已经存在的文件夹，不能建立与文件夹同名的文件
        if(toPath.isDirectory()){
            throw new IOException("不能建立与文件名称相同的文件夹！！！");
        }
        // 2、选择流
        InputStream is = new BufferedInputStream(new FileInputStream(fromPath));
        OutputStream os = new BufferedOutputStream(new FileOutputStream(toPath));
        // 3、文件拷贝 循环+读取+写出
        byte[] flush = new byte[1024];
        int len = 0;
        while (-1 != (len = is.read(flush))) {
            // 写出
            os.write(flush, 0, len);
        }
        os.flush();// 强制刷出
        // 4、关闭流。一般先打开的后关闭
        os.close();
        is.close();
        System.out.println("恭喜您！文件拷贝成功！！！");
    }

    /**
     * 关闭流
     * @param io
     */
    public static <T extends Closeable> void closeAll(T... io){
        for(Closeable temp:io){
            if(null!=temp){
                try {
                    temp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 关闭流
     * ...表示可变参数，即参数的个数没有限制，只要是前面定义类型即可。只能在形参的最后一个位置
     * 处理方法与数组一致
     * @param io
     */
    public static void close(Closeable ... io){
        for(Closeable temp:io){
            if(null!=temp){
                try {
                    temp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 文件拷贝
     *
     */
    public static void fileCopy(String from, String to) throws IOException {
        // 1、建立联系 file对象。源：存在且为文件+目的地：文件可以不存在
        File fromPath = new File(from);
        File toPath = new File(to);
        fileCopy(fromPath, toPath);
    }
}


