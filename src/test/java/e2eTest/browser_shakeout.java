package e2eTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class browser_shakeout {
  @Test
  public void e2eTest() throws IOException {
    Results results =
        Runner.path("classpath:uiTests/index/e2eTest")
            .reportDir("reports/json-reports")
            .tags("@chrome_e2eTest,@firefox_e2eTest,@edge_e2eTest,@safari_e2eTest").parallel(1);
    generateReport(results.getReportDir());
    assertEquals(results.getErrorMessages(), 0, results.getFailCount());
  }

  public static void generateReport(String karateOutputPath) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Collection<File> jsonFiles =
        FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
    List<String> jsonPaths = new ArrayList(jsonFiles.size());
    for (File file : jsonFiles) {
      JSONArray jsonArray = new JSONArray(FileUtils.readFileToString(file, StandardCharsets.UTF_8));
      jsonArray.getJSONObject(0).put("name", jsonArray.getJSONObject(0).get("description"));
      FileUtils.writeStringToFile(file, jsonArray.toString(), StandardCharsets.UTF_8, false);
      jsonPaths.add(file.getAbsolutePath());
    }
  }
}
