package Hive;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;


public class FindDepartment extends UDF
{
	private Text result=new Text();
	String result_date="";
	public String evaluate(String doj) throws ParseException
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf_target=new SimpleDateFormat("yyyy-MMM-dd");
		
		Date dt_doj=sdf.parse(doj.trim());
		result_date=sdf_target.format(dt_doj);
		
		return result_date;
	}
}
