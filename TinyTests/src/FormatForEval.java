import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FormatForEval {
	public void RewriteFile(String targetformatfile, String sourceformatfile,String writefile) throws IOException{
		//only  change the head of source file
		BufferedReader targetReader=new BufferedReader(new FileReader(targetformatfile));
		BufferedReader sourceReader=new BufferedReader(new FileReader(sourceformatfile));
		File outFile=new File(writefile);
		if(!outFile.exists()){
			outFile.createNewFile();
		}
		FileWriter writer=new FileWriter(outFile);
		String templineString=targetReader.readLine();
		
		while(templineString!=null){
			if(templineString.equals("")){
				writer.write("\n");
				templineString=targetReader.readLine();
				sourceReader.readLine();//skip empty line
			}else{
				String[] targetcolumns=templineString.split("\t");
				String sourceline=sourceReader.readLine();
				String sourceheadString=sourceline.split("\t")[6];
				String sourcerelString=sourceline.split("\t")[7];//
				
				writer.write(targetcolumns[0]+"\t"+targetcolumns[1]+"\t"+targetcolumns[2]+"\t"+targetcolumns[3]+"\t"+targetcolumns[4]+"\t"+targetcolumns[5]+"\t"+sourceheadString+"\t"+targetcolumns[7]+"\t"+targetcolumns[8]+"\t"+targetcolumns[9]+"\t"+targetcolumns[10]+"\n");
				templineString=targetReader.readLine();
			}
		}
		System.out.println("End:"+sourceReader.readLine());//check end
		
		targetReader.close();
		sourceReader.close();
		writer.close();
	}
	public static void main(String args[]) throws IOException{
		/*
		String sourcefile=System.getenv("CODEDATA")+File.separator+"mstmodel_rand100_parseout.txt";
		String targetfile=System.getenv("CODEDATA")+File.separator+"wsj_rand100.conll";
		String writefile=System.getenv("CODEDATA")+File.separator+"mstmodel_rand100_parseout_format.conll";
		FormatForEval test=new FormatForEval();
		test.RewriteFile(targetfile, sourcefile, writefile);
		*/
		String sourcefile=System.getenv("CODEDATA")+File.separator+"wsj_2-21.conll";
		String targetfile=System.getenv("CODEDATA")+File.separator+"wsj_2-21_malt_processindex.txt";
		String writefile=System.getenv("CODEDATA")+File.separator+"wsj_2-21_malt_processindex_new.txt";
		FormatForEval test=new FormatForEval();
		test.RewriteFile(targetfile, sourcefile, writefile);
	}
}
