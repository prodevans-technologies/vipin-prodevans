package SecondarySort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import SecondarySort.MyKey;

public class MyPartitioner extends Partitioner<MyKey, Text>
{

	@Override
	public int getPartition(MyKey key, Text value, int numReduceTask)
	{
	
		if(Integer.parseInt(key.getYear().toString()) == 2017)
		{
			return 0;
		}
		if(Integer.parseInt(key.getYear().toString()) == 2016)
		{
			return 1;
		}
		if(Integer.parseInt(key.getYear().toString()) == 2015)
		{
			return 2;
		}
		else 
			return 0;
		
	}
	
}
