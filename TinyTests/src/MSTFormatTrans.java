import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class MSTFormatTrans {

	public void RewriteFile(String filepath,String maltfile ,String newfilepath) throws IOException{
		/*
		 * this method is used to transform the format of MST parser output,
		 * which take changed the original words(take the numbers as<num>, 
		 * leading to MaltEval CANNOT compare the output files from stanford
		 * and Malt). 
		 */
		//input: MST output file, Malt
		//output: with right format
		File in = new File(filepath);
		BufferedReader reader = new BufferedReader(new FileReader(in));
		File maltin = new File(maltfile);
		BufferedReader maltreader = new BufferedReader(new FileReader(maltin));
		File out = new File(newfilepath);
		if(!out.exists()){
			out.createNewFile();
		}
		FileWriter writer = new FileWriter(out);
		
		String mstline = reader.readLine();
		String maltline = maltreader.readLine();
		while(maltline!=null){
			if(maltline.equals("")){
				writer.write("\n");
			}else{
				String mstcolumns[] = mstline.split("\t");
				String maltcolumns[] = maltline.split("\t");
				writer.write(maltcolumns[0]+"\t"+maltcolumns[1]+"\t"+maltcolumns[2]+"\t"+maltcolumns[3]+"\t"+maltcolumns[4]+"\t"+maltcolumns[5]+"\t"+mstcolumns[6]+"\t"+mstcolumns[7]+"\t"+maltcolumns[8]+"\t"+maltcolumns[9]+"\t"+"\n");
			}
			
			maltline = maltreader.readLine();
			mstline = reader.readLine();
		}
		
		reader.close();
		maltreader.close();
		writer.close();
	}
	
	public static void main(String args[]) throws IOException{
		MSTFormatTrans ts = new MSTFormatTrans();
		
		//ts.RewriteFile("D:\\Kenny\\software\\MaltEval-20081119\\lib\\wsj_00_parseout.conll", "D:\\Kenny\\software\\MaltEval-20081119\\lib\\wsj_00.conll", "D:\\Kenny\\software\\MaltEval-20081119\\lib\\wsj_00_parseouttrans.conll");
		/*
		ts.RewriteFile("a100out.conll", "a100.conll", "a100out_format.conll");
		ts.RewriteFile("a100chgout.conll", "a100.conll", "a100chgout_format.conll");
		ts.RewriteFile("b100out.conll", "b100.conll", "b100out_format.conll");
		ts.RewriteFile("b100chgout.conll", "b100.conll", "b100chgout_format.conll");
		ts.RewriteFile("c100out.conll", "c100.conll", "c100out_format.conll");
		ts.RewriteFile("c100chgout.conll", "c100.conll", "c100chgout_format.conll");
		*/
		/*
		ts.RewriteFile("a100simout.conll", "a100.conll", "a100simout_format.conll");
		ts.RewriteFile("b100simout.conll", "b100.conll", "b100simout_format.conll");
		ts.RewriteFile("c100simout.conll", "c100.conll", "c100simout_format.conll");
		System.out.println("Done.");
		*/
		//ts.RewriteFile("G:\\MaltEval-20081119\\lib\\conjunction\\mstmod_and.conll", "G:\\MaltEval-20081119\\lib\\conjunction\\malt_and.conll", "G:\\MaltEval-20081119\\lib\\conjunction\\mstmod_and_format.conll");
		//ts.RewriteFile("G:\\MaltEval-20081119\\lib\\conjunction\\MST_and.conll", "G:\\MaltEval-20081119\\lib\\conjunction\\malt_and.conll", "G:\\MaltEval-20081119\\lib\\conjunction\\mst_and_format.conll");
		
//		ts.RewriteFile("a100_addoldfeature_out.conll", "a100.conll", "a100_addoldfeature_out_chgformat.conll");
//		ts.RewriteFile("b100_addoldfeature_out.conll", "b100.conll", "b100_addoldfeature_out_chgformat.conll");
//		ts.RewriteFile("c100_addoldfeature_out.conll", "c100.conll", "c100_addoldfeature_out_chgformat.conll");
		
//		ts.RewriteFile("a100_addoldfeat_noextended.conll", "a100.conll", "a100_addoldfeat_noextended_chgformat.conll");
//		ts.RewriteFile("a100_changenpindex_test.conll", "a100.conll", "a100_changenpindex_test_chgformat.conll");
//		ts.RewriteFile("b100_changenpindex_test.conll", "b100.conll", "b100_changenpindex_test_chgformat.conll");
//		ts.RewriteFile("c100_changenpindex_test.conll", "c100.conll", "c100_changenpindex_test_chgformat.conll");
//		ts.RewriteFile("a100_filtersimilow_2.conll", "a100.conll", "a100_filtersimilow_2_chgformat.conll");
//		ts.RewriteFile("b100_filtersimilow_2.conll", "b100.conll", "b100_filtersimilow_2_chgformat.conll");
//		ts.RewriteFile("c100_filtersimilow_2.conll", "c100.conll", "c100_filtersimilow_2_chgformat.conll");
//		ts.RewriteFile("a100_extreme_filterlow.conll", "a100.conll", "a100_extreme_filterlow_chgformat.conll");
//		ts.RewriteFile("b100_extreme_filterlow.conll", "b100.conll", "b100_extreme_filterlow_chgformat.conll");
//		ts.RewriteFile("c100_extreme_filterlow.conll", "c100.conll", "c100_extreme_filterlow_chgformat.conll");
//		ts.RewriteFile("b100_manuallabel_filterlow.conll", "b100.conll", "b100_manuallabel_filterlow_chgformat.conll");
//		ts.RewriteFile("a100_chgthre_047.conll", "a100.conll", "a100_chgthre_047_chgformat.conll");
//		ts.RewriteFile("b100_chgthre_047.conll", "b100.conll", "b100_chgthre_047_chgformat.conll");
//		ts.RewriteFile("c100_chgthre_047.conll", "c100.conll", "c100_chgthre_047_chgformat.conll");
//		ts.RewriteFile("wsj_00_filter.conll", "wsj_00_stanfordout.conll", "wsj_00_filter_chgformat.conll");
		String goldfileString=System.getenv("CODEDATA")+File.separator+"wsj_00-01_malt_processindex_new.txt";
//		String mstfile1String=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"mst_51"+File.separator+"mst_2order_wsj2-21_proj_iter10_51_parseout.txt";
//		String mstfile2String=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"mst_51"+File.separator+"mst_2order_wsj2-21_nonproj_iter10_51_parseout.txt";
//		String mstfile3String=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"mst_51"+File.separator+"mst_1order_wsj2-21_proj_iter10_51_parseout.txt";
//		String mstfile4String=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"mst_51"+File.separator+"mst_1order_wsj2-21_nonproj_iter10_51_parseout.txt";
//		String mstfile1Stringnew=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"mst_51"+File.separator+"mst_2order_wsj2-21_proj_iter10_51_parseout_chgformat.txt";
//		String mstfile2Stringnew=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"mst_51"+File.separator+"mst_2order_wsj2-21_nonproj_iter10_51_parseout_chgformat.txt";
//		String mstfile3Stringnew=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"mst_51"+File.separator+"mst_1order_wsj2-21_proj_iter10_51_parseout_chgformat.txt";
//		String mstfile4Stringnew=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"mst_51"+File.separator+"mst_1order_wsj2-21_nonproj_iter10_51_parseout_chgformat.txt";
//		ts.RewriteFile(mstfile1String, goldfileString, mstfile1Stringnew);
//		ts.RewriteFile(mstfile2String, goldfileString, mstfile2Stringnew);
//		ts.RewriteFile(mstfile3String, goldfileString, mstfile3Stringnew);
//		ts.RewriteFile(mstfile4String, goldfileString, mstfile4Stringnew);
		/*
		String beanfile=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"bean_wsj_2-21_10iter_avoidcircle_parseout.txt";
		String beanfilenew=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"bean_wsj_2-21_10iter_avoidcircle_parseout_chgformat.txt";
		ts.RewriteFile(beanfile, goldfileString, beanfilenew);
		*/
		String beanfile=System.getenv("CODEDATA")+File.separator+"beam"+File.separator+"wsj_00-01_malt_processindex_new_beam_1_godordertrain.out";
		String beanfilenew=System.getenv("CODEDATA")+File.separator+"beam"+File.separator+"wsj_00-01_malt_processindex_new_beam_1_godordertrain.conll";
		ts.RewriteFile(beanfile, goldfileString, beanfilenew);
		System.out.println("Done.");
	}
}
