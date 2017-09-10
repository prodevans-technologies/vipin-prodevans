package SecondarySort;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;;

public class SecondarySortDriver 
{

	public static void main(String cp[]) throws IOException, ClassNotFoundException, InterruptedException
	{
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "word count");
		job.setJarByClass(SecondarySortDriver.class);
		job.setPartitionerClass(MyPartitioner.class);
		job.setGroupingComparatorClass(MyGroupComparator.class);
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReducer.class);
		//job.setCombinerClass(IntSumReducer.class);
		
		job.setNumReduceTasks(3);
		job.setMapOutputKeyClass(MyKey.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		
		MultipleOutputs.addNamedOutput(job,"Year2014", TextOutputFormat.class, Text.class, NullWritable.class);
		MultipleOutputs.addNamedOutput(job,"Year2015", TextOutputFormat.class, Text.class, NullWritable.class);
		//MultipleOutputs.addNamedOutput(job,"Year2016", TextOutputFormat.class, Text.class, NullWritable.class);
		MultipleOutputs.addNamedOutput(job,"rejectedRecords", TextOutputFormat.class, Text.class, NullWritable.class);
		
		LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
		
		FileSystem fs = FileSystem.get(conf);
		Path rawfilepath = new Path(cp[0]);
		Path mapperOutFilePath = new Path(cp[1]);
		if(fs.exists(mapperOutFilePath)){
			fs.delete(mapperOutFilePath);
		}
		FileInputFormat.addInputPath(job, rawfilepath);
		FileOutputFormat.setOutputPath(job, mapperOutFilePath);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
	

}
