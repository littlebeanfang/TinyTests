// DeleteLastLine.java
import java.io.*;

public class DeleteLastLine {
	public static void main(String[] args) throws IOException {
		for(int i=1;i<101;i++){
			RandomAccessFile raf = new RandomAccessFile(i+".score", "rw");
			long len = raf.length();
			raf.setLength(len - 2);
			raf.close();
		}
	}
}