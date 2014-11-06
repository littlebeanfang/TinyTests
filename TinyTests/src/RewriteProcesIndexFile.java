import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class RewriteProcesIndexFile {
	public void Rewrite(String processindexfile, String newfile) throws IOException{
		//Ignore the process index column
		BufferedReader reader=new BufferedReader(new FileReader(processindexfile));
		File out=new File(newfile);
		if(!out.exists()){
			out.createNewFile();
		}
		FileWriter writer=new FileWriter(out);
		String lineString;
		while((lineString=reader.readLine())!=null){
			if(lineString.equals("")){
				writer.write("\n");
			}else{
				String tempString=lineString.substring(0, lineString.lastIndexOf("\t"))+"\n";
				writer.write(tempString);
			}
		}
		
		writer.close();
	}
	public static void main(String args[]) throws IOException{
		String indexfileString=System.getenv("CODEDATA")+File.separator+"wsj_rand100_processindex.txt";
		String rewritefile=System.getenv("CODEDATA")+File.separator+"wsj_rand100.conll";
		RewriteProcesIndexFile test=new RewriteProcesIndexFile();
		test.Rewrite(indexfileString, rewritefile);
	}
}
