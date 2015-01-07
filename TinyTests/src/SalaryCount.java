import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SalaryCount {
	// map to store <Name, salary_comment> items
	// salary format: =10+20+12 comment format:
	private HashMap<String, String> people_salary = new HashMap<String, String>();

	public void ReadAddSongFile(String songfile) throws IOException {
		// songfile format:<date \t songnumber \t name \t money>
		BufferedReader reader = new BufferedReader(new FileReader(songfile));
		String lineString = reader.readLine();
		while (lineString != null) {
			String columns[] = lineString.split("\t");
			int songnumber = Integer.parseInt(columns[1]);
			String nameString = columns[2];
			if (people_salary.containsKey(nameString)) {
				String comment = people_salary.get(nameString);
				String items[] = comment.split("\t");
				String money = items[0] + "+"
						+ (int) ((double) songnumber * 0.5);
				String event = items[1] + "、加歌" + songnumber + "首";
				people_salary.put(nameString, money + "\t" + event);
			} else {
				people_salary.put(nameString, "=" + columns[3] + "\t加歌"
						+ songnumber + "首");
			}
			lineString = reader.readLine();
		}
		reader.close();
	}

	public void ReadPropagandaFile(String propagandafile) throws IOException {
		// format:<date \t event \t time \t name \t money>
		BufferedReader reader = new BufferedReader(new FileReader(
				propagandafile));
		String lineString = reader.readLine();
		while (lineString != null) {
			String columns[] = lineString.split("\t");
			String nameString = columns[3];
			if (people_salary.containsKey(nameString)) {
				String comment = people_salary.get(nameString);
				String items[] = comment.split("\t");
				String money = items[0] + "+" + columns[4];
				String event = items[1] + "、" + columns[1];
				people_salary.put(nameString, money + "\t" + event);
			} else {
				people_salary.put(nameString, "=" + columns[4] + "\t"
						+ columns[1]);
			}
			lineString = reader.readLine();
		}
		reader.close();
	}

	public void ReadShoppingFile(String shoppingfile) throws IOException {
		// format:<place \t times \t name \t money>
		BufferedReader reader = new BufferedReader(new FileReader(shoppingfile));
		String lineString = reader.readLine();
		while (lineString != null) {
			String columns[] = lineString.split("\t");
			String nameString = columns[2];
			if (people_salary.containsKey(nameString)) {
				String comment = people_salary.get(nameString);
				String items[] = comment.split("\t");
				int times = Integer.parseInt(columns[1]);
				String shoppingmoneyString = "";
				String shoppingeventString = "";
				if (columns[0].equals("欧尚")) {
					shoppingeventString += "欧尚采购" + times + "次";
					shoppingmoneyString += columns[3];
				} else if (columns[0].equals("天猫")) {
					shoppingeventString += "天猫采购" + times + "次";
					shoppingmoneyString += columns[3];
				} else {
					System.out.println("shopping place wrong !");
				}
				String money = items[0] + "+" + shoppingfile;
				String event = items[1] + "、" + shoppingeventString;
				people_salary.put(nameString, money + "\t" + event);
			} else {
				int times = Integer.parseInt(columns[1]);
				String shoppingmoneyString = "";
				String shoppingeventString = "";
				if (columns[0].equals("欧尚")) {
					shoppingeventString += "欧尚采购" + times + "次";
					shoppingmoneyString += columns[3];
				} else if (columns[0].equals("天猫")) {
					shoppingeventString += "天猫采购" + times + "次";
					shoppingmoneyString += columns[3];
				} else {
					System.out.println("shopping place wrong !");
				}
				people_salary.put(nameString, "=" + shoppingmoneyString + "\t"
						+ shoppingeventString);
			}
			lineString = reader.readLine();
		}
		reader.close();
	}

	public void ReadRegularWage(String regularfile) throws IOException {
		// format : first line: <"ratio" \t reason \t ratio>
		// following line:<name \t post \t money>
		BufferedReader reader = new BufferedReader(new FileReader(regularfile));
		String lineString = reader.readLine();
		double ratio = Double.parseDouble(lineString.split("\t")[2]);
		lineString = reader.readLine();
		while (lineString != null) {
			String columns[] = lineString.split("\t");
			String nameString = columns[0];
			if (people_salary.containsKey(nameString)) {
				String comment = people_salary.get(nameString);
				String items[] = comment.split("\t");
				String money = items[0] + "+"
						+ (int) (Double.parseDouble(columns[2]) * ratio);
				String event = items[1] + "、" + columns[1];
				people_salary.put(nameString, money + "\t" + event);
			} else {
				people_salary.put(nameString,
						"=" + (int) (Double.parseDouble(columns[2]) * ratio)
								+ "\t" + columns[1]);
			}
			lineString = reader.readLine();
		}
		reader.close();
	}

	public void ReadWaterFile(String waterfile) throws IOException {
		// format : <name \t times>
		int watermoney=5;
		BufferedReader reader = new BufferedReader(new FileReader(waterfile));
		String lineString = reader.readLine();
		while (lineString != null) {
			String columns[] = lineString.split("\t");
			String nameString = columns[0];
			if (people_salary.containsKey(nameString)) {
				String comment = people_salary.get(nameString);
				String items[] = comment.split("\t");
				String money = items[0] + "+"
						+ (int) (Integer.parseInt(columns[1]) * watermoney);
				String event = items[1] + "、搬水" + columns[1]+"次";
				people_salary.put(nameString, money + "\t" + event);
			} else {
				people_salary.put(nameString,
						"=" + (int) (Integer.parseInt(columns[1]) * watermoney)
								+ "\t" + "搬水" + columns[1]+"次");
			}
			lineString = reader.readLine();
		}
		reader.close();
	}

	public void printmap() {
		Iterator iterator = people_salary.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}
	}
	public void WriteMapByNameFile(String namefile, String writefilename) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(namefile));
		File outFile=new File(writefilename);
		if(!outFile.exists()){
			outFile.createNewFile();
		}
		FileWriter writer=new FileWriter(outFile);
		
		int peoplecount=0;
		int emptysalarycount=0;
		String lineString=reader.readLine();
		while(lineString!=null){
			peoplecount++;
			if(people_salary.containsKey(lineString)){
				//not empty
				writer.write(lineString+"\t"+people_salary.get(lineString)+"\n");
			}else{
				System.out.println("nosalary:"+lineString);
				writer.write(lineString+"\t0\t \n");
				emptysalarycount++;
			}
			lineString=reader.readLine();
		}
		System.out.println("==========Salary===============");
		System.out.println("Total people:"+peoplecount);
		System.out.println("Salary people:"+people_salary.size());
		System.out.println("Empty people:"+emptysalarycount);
		System.out.println("Attention, if Total people!=Salary people+Empty people, there must be name problem in the files.");
		
		reader.close();
		writer.close();
	}
	public void RewriteAttendenceFile(String namefile, String attendencefile,String writefilename) throws IOException{
		//attendfile format:<name \t vacate \t absence \t money>
		BufferedReader attenReader=new BufferedReader(new FileReader(attendencefile));
		String attenlineString=attenReader.readLine();
		HashMap<String, String> attendhHashMap=new HashMap<String,String>();
		while(attenlineString!=null){
			String columns[]=attenlineString.split("\t");
			attendhHashMap.put(columns[0], columns[3]);
			attenlineString=attenReader.readLine();
		}
		BufferedReader reader = new BufferedReader(new FileReader(namefile));
		File outFile=new File(writefilename);
		if(!outFile.exists()){
			outFile.createNewFile();
		}
		FileWriter writer=new FileWriter(outFile);
		
		int peoplecount=0;
		int emptysalarycount=0;
		String lineString=reader.readLine();
		while(lineString!=null){
			peoplecount++;
			if(attendhHashMap.containsKey(lineString)){
				//not empty
				writer.write(lineString+"\t"+attendhHashMap.get(lineString)+"\n");
			}else{
				System.out.println("attendence error:"+lineString);
				writer.write(lineString+"\t \t \n");
				emptysalarycount++;
			}
			lineString=reader.readLine();
		}
		System.out.println("==========Attendence===============");
		System.out.println("Total people:"+peoplecount);
		System.out.println("count people:"+attendhHashMap.size());
		System.out.println("Empty people:"+emptysalarycount);
		System.out.println("Attention, if Total people!=Salary people+Empty people, there must be name problem in the files.");
		
		reader.close();
		writer.close();
	}
	public static void main(String args[]) throws IOException {
		SalaryCount sc = new SalaryCount();
		sc.ReadRegularWage("1.Regular.txt");
		sc.ReadWaterFile("2.Water.txt");
		sc.ReadAddSongFile("3.Song.txt");
		sc.ReadPropagandaFile("4.Propaganda.txt");
		sc.ReadShoppingFile("5.Shopping.txt");
		sc.printmap();
		sc.WriteMapByNameFile("6.Name.txt", "FinalSalary.txt");
		sc.RewriteAttendenceFile("6.Name.txt", "7.Attend.txt", "RewriteAttend.txt");
	}
}
