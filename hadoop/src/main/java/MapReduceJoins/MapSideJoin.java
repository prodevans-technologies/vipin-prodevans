package MapReduceJoins;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.concurrent.BrokenBarrierException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.vip.wordCount.wordCountMain;
import com.vip.wordCount.wordCountMapper;
import com.vip.wordCount.wordCountReducer;

public class MapSideJoin
{

	public static class MapJoinDCM extends Mapper<LongWritable, Text, Text, Text>
	{
		private HashMap<String, String> DepartmentMap=new HashMap<String, String>();
		private BufferedReader brReader;
		private String strDeptName= "";
		private Text MapOutputKey=new Text("");
		private Text MapOutputValue=new Text("");
		
		private void loadDepartmentHashMap(Path filePath, Context context)throws IOException
		{
			String strLineRead="";
			try
			{
				brReader=new BufferedReader(new FileReader(filePath.toString()));
				
				while((strLineRead=brReader.readLine())!=null)
				{
					String deptFieldArray[] =strLineRead.split(",");
					DepartmentMap.put(deptFieldArray[0].trim(), deptFieldArray[1].trim());
				}
					
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			finally
			{
				if(brReader!=null)
					brReader.close();
			}
		}
		
		@Override
		protected void cleanup(Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException 
		{
			
		}
		
		@Override
		protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException 
		{
			Path[] cacheFiles=DistributedCache.getLocalCacheFiles(context.getConfiguration());
			for(Path eachPath:cacheFiles)
			{
				if(eachPath.getName().toString().trim().equals("departments.txt"))
				{
					loadDepartmentHashMap(eachPath, context);
				}
			}
			
		}
		
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException 
		{
			if(value.toString().length()>0)
			{
				String arrEmpAtributes[]=value.toString().trim().split(",",-1);
				try
				{
					strDeptName=DepartmentMap.get(arrEmpAtributes[2].toString().trim());
				}
				finally {
					strDeptName=( ( strDeptName.equals(null) || strDeptName.equals("") ) ? "NoT-FOUND" : strDeptName);
				}
				MapOutputKey.set(arrEmpAtributes[0].toString());
				MapOutputValue.set(arrEmpAtributes[1].toString()+","
								+   strDeptName+","
								+	arrEmpAtributes[3].toString()
						);
				
			}
			context.write(MapOutputKey, MapOutputValue);
			strDeptName="";
			
		}
		
	}
	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException
	{
		Configuration conf = new Configuration();
		
		DistributedCache.addCacheFile(new URI(args[2]), conf);
		
		Job job = Job.getInstance(conf, "my join");
		job.setJarByClass(MapSideJoin.class);
		job.setMapperClass(MapJoinDCM.class);
		job.setNumReduceTasks(0);
		
		//job.setCombinerClass(IntSumReducer.class);
		
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		FileSystem fs = FileSystem.get(conf);
		Path rawfilepath = new Path(args[0]);
		Path mapperOutFilePath = new Path(args[1]);
		if(fs.exists(mapperOutFilePath)){
			fs.delete(mapperOutFilePath);
		}
		FileInputFormat.addInputPath(job, rawfilepath);
		FileOutputFormat.setOutputPath(job, mapperOutFilePath);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
	
	
}
