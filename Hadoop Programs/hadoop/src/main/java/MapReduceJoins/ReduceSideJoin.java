package MapReduceJoins;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ReduceSideJoin 
{

	public static class StudentMapper extends Mapper<LongWritable, Text, Text, Text>
	{
		public void map(LongWritable key,Text value,Context context)
		{
			String[] record_array = value.toString().split(",",-1);
		}
	}
	
	public static class DeptMapper extends Mapper<LongWritable, Text, Text, Text>
	{
		
	}
}
