package de.ddb.pdc.core;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Represents the result of a public-domain calculation for a specific item.
 */
public class PDCResult {

  private boolean publicDomain;
  private List<AnsweredQuestion> trace;

  /**
   * Creates a PDCResult.
   *
   * @param publicDomain whether the item is considered public-domain
   * @param trace        trace of calculation questions and answers
   */
  public PDCResult(boolean publicDomain, List<AnsweredQuestion> trace) {
    this.publicDomain = publicDomain;
    this.trace = trace;
  }

  /**
   * Returns true if the item in question is considered public-domain by
   * the calculator, or false if not.
   */
  public boolean isPublicDomain() {
    return publicDomain;
  }

  /**
   * Returns all questions and answers that led to the result of the
   * calculation. The questions are returned in the order they were
   * asked.
   */
  public List<AnsweredQuestion> getTrace() {
    return trace;
  }
  
  /**
   * JSON representation of the PDCResult.
   * @throws IOException 
   */
  public String toJSONString() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonFactory jsonFactory = new JsonFactory();    
    JsonGenerator jsonGen = jsonFactory.createGenerator(stringWriter);
    jsonGen.writeStartObject();
    jsonGen.writeFieldName("publicDomain");
    jsonGen.writeBoolean(this.isPublicDomain());
    jsonGen.writeStartArray();
    for (AnsweredQuestion answeredQuestion : this.getTrace()) {
      jsonGen.writeStartObject();
      jsonGen.writeFieldName(answeredQuestion.getQuestion().getText());
      jsonGen.writeBoolean(answeredQuestion.getAnswer().toBoolean());
      jsonGen.writeEndObject();
    }
    jsonGen.writeEndArray();
    jsonGen.writeEndObject();
    jsonGen.flush();    
    return stringWriter.toString();
  }
}
