import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CountCC {
	/*
	 * This class is for counting number of CC and NPCC in gold file
	 * Input: CONLL file path
	 * Output: the number of CC (POS), the number of conj. relation
	 *         the number of cc relation, the number of NPCC(conj relation+NP POS)
	 *         
	 *         0	1		2		3	4	5	6	7		8	9
	 *         1	Soon	Soon	RB	RB	-	4	advmod	-	-

	 */
	public void WrongToRight(String goldfile, String beforefile, String afterfile) throws IOException{
		BufferedReader greader = new BufferedReader(new FileReader(goldfile));
		BufferedReader breader = new BufferedReader(new FileReader(beforefile));
		BufferedReader areader = new BufferedReader(new FileReader(afterfile));
		
		String gline = greader.readLine();
		String bline = breader.readLine();
		String aline = areader.readLine();
		int sencount = 1 ;
		boolean toright = false ;
		while(gline!=null){
			if(gline.equals("")){
				gline = greader.readLine();
				bline = breader.readLine();
				aline = areader.readLine();
				if(toright == true){
					System.out.print(sencount+" ");
				}
				toright = false ;
				sencount++ ;
				continue ;
			}
			String[] gcolumn = gline.split("\t");
			String[] bcolumn = bline.split("\t");
			String[] acolumn = aline.split("\t");
			//System.out.println("gline: "+gline);
			//System.out.println("bline: "+bline);
			//System.out.println("aline: "+aline);
			boolean bconj = false ;
			boolean aconj = false ;
			String ghead=gcolumn[6];
			String bhead=bcolumn[6];
			String ahead=acolumn[6];
			if(!bhead.equals(ghead)&&ahead.equals(ghead)){
				toright=true;
			}
			gline = greader.readLine();
			bline = breader.readLine();
			aline = areader.readLine();
		}
		greader.close();
		breader.close();
		areader.close();
	}
	
	public void RightToWrong(String goldfile, String beforefile, String afterfile) throws IOException{
		BufferedReader greader = new BufferedReader(new FileReader(goldfile));
		BufferedReader breader = new BufferedReader(new FileReader(beforefile));
		BufferedReader areader = new BufferedReader(new FileReader(afterfile));
		
		String gline = greader.readLine();
		String bline = breader.readLine();
		String aline = areader.readLine();
		int sencount = 1 ;
		boolean towrong = false ;
		while(gline!=null){
			if(gline.equals("")){
				gline = greader.readLine();
				bline = breader.readLine();
				aline = areader.readLine();
				if(towrong == true){
					System.out.print(sencount+" ");
				}
				towrong = false ;
				sencount++ ;
				continue ;
			}
			String[] gcolumn = gline.split("\t");
			String[] bcolumn = bline.split("\t");
			String[] acolumn = aline.split("\t");
			//System.out.println("gline: "+gline);
			//System.out.println("bline: "+bline);
			//System.out.println("aline: "+aline);
			String ghead=gcolumn[6];
			String bhead=bcolumn[6];
			String ahead=acolumn[6];
			if(bhead.equals(ghead)&&!ahead.equals(ghead)){
				towrong=true;
			}
			gline = greader.readLine();
			bline = breader.readLine();
			aline = areader.readLine();
		}
		greader.close();
		breader.close();
		areader.close();
	}
	
	public static void main(String args[]) throws IOException{
		CountCC test = new CountCC();
		/*
		int[] num1 = test.count("a100.conll");
		//System.out.println();
		//System.out.println("a100.conll : ");
		//System.out.println("CCPOS: "+ num1[0]+", conj: "+num1[1]+",cc: "+num1[2]+",NPCC: "+num1[3]);
		int[] num2 = test.count("b100.conll");
		//System.out.println();
		//System.out.println("b100.conll : ");
		//System.out.println("CCPOS: "+ num2[0]+", conj: "+num2[1]+",cc: "+num2[2]+",NPCC: "+num2[3]);
		int[] num3 = test.count("c100.conll");
		//System.out.println();
		//System.out.println("c100.conll : ");
		//System.out.println("CCPOS: "+ num3[0]+", conj: "+num3[1]+",cc: "+num3[2]+",NPCC: "+num3[3]);
		*/
		/*
		test.countnpcc("a100.conll");
		test.countnpcc("b100.conll");
		test.countnpcc("c100.conll");
		*/
		/*
		System.out.println("For a100: sentence wrong to right after change feature:");
		test.WrongToRight("a100.conll", "a100out.conll", "a100chgout.conll");
		System.out.println();
		System.out.println("For b100: sentence wrong to right after change feature:");
		test.WrongToRight("b100.conll", "b100out.conll", "b100chgout.conll");
		System.out.println();
		System.out.println("For c100: sentence wrong to right after change feature:");
		test.WrongToRight("c100.conll", "c100out.conll", "c100chgout.conll");
		System.out.println();
		*/
		
		/*
		System.out.println("sentence wrong to right after change feature:");
		System.out.print("For a100: ");
		test.WrongToRight("a100.conll", "a100out.conll", "a100simout.conll");
		System.out.println();
		System.out.print("For b100: ");
		test.WrongToRight("b100.conll", "b100out.conll", "b100simout.conll");
		System.out.println();
		System.out.print("For c100: ");
		test.WrongToRight("c100.conll", "c100out.conll", "c100simout.conll");
		System.out.println();
		
		System.out.println("sentence right to wrong after change feature:");
		System.out.print("For a100: ");
		test.RightToWrong("a100.conll", "a100out.conll", "a100simout.conll");
		System.out.println();
		System.out.print("For b100: ");
		test.RightToWrong("b100.conll", "b100out.conll", "b100simout.conll");
		System.out.println();
		System.out.print("For c100: ");
		test.RightToWrong("c100.conll", "c100out.conll", "c100simout.conll");
		System.out.println();
		
		*/
		/*
		System.out.println("sentence wrong to right after change feature:");
		System.out.println("(compare a100simout v.s. a100_addoldfeature_out_chgformat)");
		System.out.print("For a100: ");
		test.WrongToRight("a100.conll", "a100simout.conll", "a100_addoldfeature_out_chgformat.conll");
		System.out.println();
		System.out.print("For b100: ");
		test.WrongToRight("b100.conll",  "b100simout.conll", "b100_addoldfeature_out_chgformat.conll");
		System.out.println();
		System.out.print("For c100: ");
		test.WrongToRight("c100.conll",  "c100simout.conll", "c100_addoldfeature_out_chgformat.conll");
		System.out.println();
		
		System.out.println("sentence right to wrong after change feature:");
		System.out.print("For a100: ");
		test.RightToWrong("a100.conll", "a100simout.conll", "a100_addoldfeature_out_chgformat.conll");
		System.out.println();
		System.out.print("For b100: ");
		test.RightToWrong("b100.conll", "b100simout.conll", "b100_addoldfeature_out_chgformat.conll");
		System.out.println();
		System.out.print("For c100: ");
		test.RightToWrong("c100.conll", "c100simout.conll", "c100_addoldfeature_out_chgformat.conll");
		System.out.println();
		*/
		/*
		System.out.println("sentence wrong to right after change feature:");
		System.out.println("(compare a100simout v.s. a100_addoldfeature_out_chgformat)");
		System.out.print("For a100: ");
		test.WrongToRight("jack\\a.test.100.wsj-00.conll", "jack\\a.baseline.out.txt", "jack\\a.ngram.out.txt");
		System.out.println();
		
		System.out.println("sentence right to wrong after change feature:");
		System.out.print("For a100: ");
		test.RightToWrong("jack\\a.test.100.wsj-00.conll", "jack\\a.baseline.out.txt", "jack\\a.ngram.out.txt");
		*/
		String goldfileString=System.getenv("CODEDATA")+File.separator+"WSJ"+File.separator+"wsj_00-01.conll";
		//String parse1String=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"mst_51"+File.separator+"mst_2order_wsj2-21_proj_iter10_51_parseout.txt";
		String parse1String=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"malt_eng_wsj2-21_00-01test.conll";
		String parse2String=System.getenv("CODEDATA")+File.separator+"BeanParserV1Test"+File.separator+"bean_wsj_2-21_10iter_parseout.txt";
		System.out.println("1. Malt V.S  2. Bean:");
		System.out.println("1 right 2 wrong :");
		test.RightToWrong(goldfileString, parse1String, parse2String);
		System.out.println("\n1. wrong 2 right :");
		test.WrongToRight(goldfileString, parse1String, parse2String);
	}
}
