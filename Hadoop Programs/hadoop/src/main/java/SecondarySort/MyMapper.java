package SecondarySort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable, Text, MyKey, Text>
{

	protected void map(LongWritable key,Text value, Context context) throws IOException, InterruptedException
	{
		String record=value.toString();
		String[] record_array=record.split(",",-1);
		
		int total_marks=Integer.parseInt(record_array[2].toString()) + Integer.parseInt(record_array[3].toString()) + Integer.parseInt(record_array[4].toString());
		
		String new_value=value.toString().concat(",").concat(total_marks+"");
		
		context.write(new MyKey(new Text(record_array[6]), new Text(String.valueOf(total_marks))), new Text(new_value));
	}
}
