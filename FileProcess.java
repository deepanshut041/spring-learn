import java.util.ArrayList;
import java.io.*;
class FileProcess{
	//This function gets list of files in java folder
	public ArrayList<String> GetList(){
		File folder = new File("D:/java");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> inames1=new ArrayList<String>();
		for (int i = 0; i < listOfFiles.length; i++) {
      		if (listOfFiles[i].isFile()) 
      		{
        		inames1.add(listOfFiles[i].getName());
			}
    	}
    	return inames1;
	}
	//This function removes under score from file name if present
	public void FileCleaner(){
		ArrayList<String> inames=GetList();
		String s,t;
		for(int i=0;i<inames.size();i++)
		{
			s=inames.get(i);
			t=s.replaceAll("_"," ");
			File oldfile = new File("D:/java/"+s);
			File newfile = new File("D:/java/"+t);
			if(oldfile.renameTo(newfile)) 
			{
	      	} 
	      	else 
	      	{
	         	System.out.println("Rename failed");
	    	} 
		}
	}
	/*
	//this is a temporary function
	public void AddName()throws IOException{
		String s,t="",d;
		FileCleaner();
		FileDownload ob1=new FileDownload();
		ArrayList<String> filename=GetList();
		ArrayList<String> inames=ob1.FinalName();
		for (int i=0;i<inames.size();i++) {
			for (int j=0;j<filename.size();j++) {
				d=inames.get(i);
				d=d.substring(4);
				if(d.equals(filename.get(j)))
				{
					s=d;
					t=inames.get(i);
					File oldfile = new File("D:/java/"+s);
					File newfile = new File("D:/java/"+t);
					if(oldfile.renameTo(newfile)) 
					{
						
			      	}
			      	else 
			      	{
			         	System.out.println("Rename failed");
			    	} 	
				}
			}
		}
	}



	*/
	//This function reames files add intial numer to it
	public void rename()throws IOException{
		String s,t="";
		FileCleaner();
		FileDownload ob1=new FileDownload();
		ArrayList<String> filename=GetList();
		ArrayList<String> inames=ob1.FinalName();
		for (int i=0;i<inames.size();i++) {
			for (int j=0;j<filename.size();j++) {
				if(inames.get(i).equals(filename.get(j)))
				{
					s=inames.get(i);
					if (i<10) {
						t="00"+(i+1)+" "+s;
						
					}
					if (i>9 && i<100) {
						t="0"+(i+1)+" "+s;
						
					}
					if (i>99) {
						t=(i+1)+" "+s;	
					}
					File oldfile = new File("D:/java/"+s);
					File newfile = new File("D:/java/"+t);
					if(oldfile.renameTo(newfile)) 
					{
						
			      	}
			      	else 
			      	{
			         	System.out.println("Rename failed");
			    	} 	
				}
			}
		}

	}
}