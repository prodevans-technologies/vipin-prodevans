package SecondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyGroupComparator extends WritableComparator
{

	protected MyGroupComparator()
	{
		super(MyKey.class,true);
	}
	
	@Override
	public int compare(WritableComparable a, WritableComparable b) 
	{
		
		MyKey key1=(MyKey)a;
		MyKey key2=(MyKey)b;
		
		return key1.getYear().compareTo(key2.getYear());
	}
}
