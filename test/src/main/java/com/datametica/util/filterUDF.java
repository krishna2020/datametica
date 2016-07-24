package com.datametica.util;

import java.io.IOException;
import org.apache.pig.EvalFunc;
import org.apache.pig.PigWarning;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.logicalLayer.schema.Schema;

public class filterUDF extends EvalFunc<String> {

	public String exec(Tuple input) throws IOException {
		if ((input == null) || (input.size() == 0)) {
			return null;
		}
		String exists = "exists";

		try {
			String record = input.toString();
			if(record.contains(Util.word))
				return exists;
			else 
				return null;
		} catch (Exception e) {
			warn("Error reading input: " + e.getMessage(),
					PigWarning.UDF_WARNING_1);
			return null;
		}
	}

	public Schema outputSchema(Schema input) {
		return new Schema(new Schema.FieldSchema(null, (byte) 55));
	}

}
