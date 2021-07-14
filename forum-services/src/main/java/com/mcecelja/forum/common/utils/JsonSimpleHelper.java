package com.mcecelja.forum.common.utils;

import com.mcecelja.forum.common.exceptions.ForumError;
import com.mcecelja.forum.common.exceptions.ForumException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.io.IOException;

public class JsonSimpleHelper {

	public static ObjectMapper objectMapper = new ObjectMapper();

	static {
		objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
	}

	public static String serialise(Object obj) throws ForumException {
		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (IOException exc) {
			throw new ForumException(ForumError.JSON_PARSE_ERROR, exc);
		}
	}

	public static JsonNode turnIntoJsonTree(Object obj) {
		return objectMapper.valueToTree(obj);
	}

	public static JsonNode turnIntoJsonTree(String jsonString) throws ForumException {
		try {
			return ((jsonString == null) || jsonString.trim().isEmpty()) ? null : objectMapper.readTree(jsonString);
		} catch (Exception exc) {
			throw new ForumException(ForumError.JSON_PARSE_ERROR, exc);
		}
	}

	public static String combineAndSerialise(Object mainObject, String var, Object branchObject) throws ForumException {
		JsonNode main = turnIntoJsonTree(mainObject);
		JsonNode branch = turnIntoJsonTree(branchObject);
		JsonTreeHelper.addElementToJsonTree(main, var, branch);
		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(main);
		} catch (IOException exc) {
			// JsonParseException | JsonMappingException are both caught by IOException
			throw new ForumException(ForumError.JSON_PARSE_ERROR, exc);
		}
	}

	public static <T> T deserialise(String payload, Class<T> objectClass) throws ForumException {
		if (payload == null) return null;
		try {
			return objectMapper.readValue(payload, objectClass);
		} catch (IOException exc) {
			// JsonParseException | JsonMappingException are both caught by IOException
			throw new ForumException(ForumError.JSON_PARSE_ERROR, exc);
		}
	}

	public static <T> T deserialise(JsonNode node, Class<T> objectClass) throws ForumException {
		if (node == null) return null;
		try {
			return objectMapper.treeToValue(node, objectClass);
		} catch (IOException exc) {
			// JsonParseException | JsonMappingException are both caught by IOException
			throw new ForumException(ForumError.JSON_PARSE_ERROR, exc);
		}
	}
}
