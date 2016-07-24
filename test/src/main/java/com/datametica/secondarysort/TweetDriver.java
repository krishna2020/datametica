package com.datametica.secondarysort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

import com.datametica.util.Util;

public class TweetDriver extends Configured implements Tool {
	private static Logger logger = Logger.getLogger(Job.class);

	public int run(String[] arg) throws Exception {

		//Default asc order
		// if (args.length != 4) {
		// logger.error("Usage: %s [generic options] <comma seperated input>
		// <output> <reduceTasks> <localpath>");
		// ToolRunner.printGenericCommandUsage(System.err);
		// System.exit(-1);
		// }
		Configuration conf = getConf();
		String[] args = new GenericOptionsParser(conf, arg).getRemainingArgs();
		String inputPath = args[0];
		String outputPath = args[1];
		String sortOrder = args[2];
		if(sortOrder.equalsIgnoreCase(Util.descOrder))
			Util.sortOrder = -1;

		Job job = Job.getInstance(conf, "Tweets Sorting Job");
		job.setJarByClass(TweetDriver.class);
		job.setMapperClass(TweetMapper.class);
		job.setReducerClass(TweetReducer.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setMapOutputKeyClass(TweetKeyPair.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.setGroupingComparatorClass(TweetGroupComparator.class);
		job.setPartitionerClass(TweetPartitioner.class);
		
		FileInputFormat.addInputPath(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));

		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] paramArrayOfString) throws Exception {
		int i = ToolRunner.run(new TweetDriver(), paramArrayOfString);
		logger.info("Job Execution Status" + i);
	}

}
