package com.datametica.util;

import java.io.IOException;
import org.apache.pig.FilterFunc;
import org.apache.pig.PigWarning;
import org.apache.pig.data.Tuple;

public class filterUDF extends FilterFunc {

	public Boolean exec(Tuple input) throws IOException {
		if ((input == null) || (input.size() == 0)) {
			return null;
		}

		try {
			String record = input.toString();
			return record.contains(Util.word);
		} catch (Exception e) {
			warn("Error reading input: " + e.getMessage(), PigWarning.UDF_WARNING_1);
			return false;
		}
	}
}
