/*
Copyright 2018 The Kubernetes Authors.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package io.kubernetes.client.examples;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.*;
import io.kubernetes.client.openapi.auth.ApiKeyAuth;
import io.kubernetes.client.openapi.auth.Authentication;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import io.kubernetes.client.util.Yaml;
import net.minidev.json.JSONObject;
import okhttp3.Call;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple example of how to parse a Kubernetes object.
 *
 * <p>Easiest way to run this: mvn exec:java
 * -Dexec.mainClass="io.kubernetes.client.examples.YamlExample"
 *
 * <p>From inside $REPO_DIR/examples
 */
public class YamlExampleDeployment {
  public static void main(String[] args) throws IOException, ApiException, ClassNotFoundException {

    // Read yaml configuration file, and deploy it
//    ApiClient client = Config.defaultClient();
      String kubeConfigPath = System.getProperty("user.home") + "/.kube/config";
      ApiClient client =
            ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();

      Configuration.setDefaultApiClient(client);

      CustomObjectsApi apiInstance = new CustomObjectsApi(client);
      String group = "mz.digitalroute.com"; // String | The custom resource's group name
      String version = "v1alpha1"; // String | The custom resource's version
      String plural = "workflowdeployments"; // String | The custom resource's plural name. For TPRs this would be lowercase plural kind.
      Object body = getBody(); // Object | The JSON schema of the Resource to create.
      String pretty = "true"; // String | If 'true', then the output is pretty printed.
      String namespace = "cheehove";
      try {

          Object result = apiInstance.createNamespacedCustomObject(group, version, namespace, plural, body, pretty);
          System.out.println(result.toString());
      } catch (ApiException e) {
          System.err.println("Status code: " + e.getCode());
          System.err.println("Reason: " + e.getResponseBody());
          System.err.println("Response headers: " + e.getResponseHeaders());
          e.printStackTrace();
      }

  }

  private static Object getBody() {
      org.yaml.snakeyaml.Yaml yaml= new org.yaml.snakeyaml.Yaml();
    Map<String,Object> map= null;
    try {
        File file = new File("workflowdeployment_simple3.yaml");
        InputStream targetStream = new FileInputStream(file);
      map = yaml.loadAs(targetStream, Map.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

//    JSONObject jsonObject=new JSONObject(map);
    return map;
  }

}
