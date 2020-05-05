package bank;

import java.io.*;
import java.util.*;
import java.text.*;

public class logging {
public logging(){

}
public void logact(String action){
	File logfile = new File("logFile.txt");
  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	try {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logfile,true)));
		out.println(action+" "+dateFormat.format(date));
		out.close();
	} catch (Exception e) {
		System.out.println("Logging Error");
 	}

	}
}
