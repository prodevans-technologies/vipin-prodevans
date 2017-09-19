package SecondarySort;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;


public class MyReducer extends Reducer<MyKey, Text, Text, NullWritable>
{
	MultipleOutputs<Text, NullWritable> mos= null;
	

	@Override
	protected void cleanup(Reducer<MyKey, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException 
	{
		if(mos!=null)
			mos.close();	
	}


	@Override
	protected void setup(Reducer<MyKey, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException 
	{
		mos=new MultipleOutputs<Text, NullWritable>(context);
	}


	int year=0;
	
	@Override
	protected void reduce(MyKey key, Iterable<Text> values,Context contex) throws IOException, InterruptedException 
	{
		year =Integer.parseInt(key.getYear().toString().trim());
		
		for(Text val : values)
		{
			//contex.write(val, NullWritable.get());
			
			if(year==2014)
			{
				mos.write("Year2014",val, NullWritable.get());
			}
			else if(year==2015)
			{
				mos.write("Year2015",val, NullWritable.get());
			}
			else
			{
				mos.write("rejectedRecords",val, NullWritable.get());
			}
			
		}
	}

}
