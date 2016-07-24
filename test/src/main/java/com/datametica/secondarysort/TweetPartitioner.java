package com.datametica.secondarysort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class TweetPartitioner extends Partitioner<TweetKeyPair, Text> {

	@Override
	public int getPartition(TweetKeyPair keyPair, Text text, int numReduceTasks) {
		//return Math.abs(keyPair.getEmp_id().hashCode() % numberOfPartitions);
		return (keyPair.getEmp_id().hashCode() & Integer.MAX_VALUE) % numReduceTasks;
	}

}
