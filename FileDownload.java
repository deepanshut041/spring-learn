import java.util.ArrayList;
import java.io.*;
public class FileDownload{
	//This function takes data from input.txt
	public ArrayList<String> RetriveData()throws IOException{
		ArrayList<String> file_names=new ArrayList<String>();
		FileInputStream fstream =new FileInputStream("input.txt");
		BufferedReader br=new BufferedReader(new InputStreamReader(fstream));
		String str;
		int i=4;
		while((str=br.readLine()) != null){
			if(i%4==0)
				file_names.add(str);
			i++;
		}
		br.close();
		return file_names;
	}
	//This fuction behaves like downloader bheaves when it find 2 or more than 2 file of same name
	public ArrayList<String> download()throws IOException{
		ArrayList<String> file_names=RetriveData();
		ArrayList<String> final_names=new ArrayList<String>();
		final_names.add(file_names.get(0));
		for(int i=1;i<file_names.size();i++){
			int k=1;
			String c=file_names.get(i);
			for (int j=0;j<final_names.size();j++) {
				if(c.equals(final_names.get(j))){
					if(k==1)
						c=c+"("+k+")";
					if (k>1) {
						c=c.replace(("("+(k-1)+")"),("("+k+")"));
					}
					k++;
				}
			}
			final_names.add(c);
		}
		return final_names;
	}
	//This function escape special charater from names
	/*public ArrayList<String> escape1()throws IOException{
		ArrayList<String> final_names=new ArrayList<String>();
		ArrayList<String> file_names=download();
		for (int i=0;i<file_names.size();i++) {
			String c=file_names.get(i);
			c=c.replace("  "," ");
			final_names.add(c);
			
		}
		return final_names;
	}*/

	//This function adds mp4 at the end of program
	public ArrayList<String> FinalName()throws IOException{
		ArrayList<String> final_names=new ArrayList<String>();
		ArrayList<String> file_names=download();
		for (int i=0;i<file_names.size();i++) {
			final_names.add(file_names.get(i)+".mp4");
		}
		return final_names;
	}
}