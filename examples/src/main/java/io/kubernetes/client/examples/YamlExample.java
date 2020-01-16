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

import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1DeleteOptions;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodBuilder;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServiceBuilder;
import io.kubernetes.client.openapi.models.V1Status;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;
import io.kubernetes.client.util.Yaml;
import okhttp3.Call;

import java.io.*;
import java.util.HashMap;

/**
 * A simple example of how to parse a Kubernetes object.
 *
 * <p>Easiest way to run this: mvn exec:java
 * -Dexec.mainClass="io.kubernetes.client.examples.YamlExample"
 *
 * <p>From inside $REPO_DIR/examples
 */
public class YamlExample {
  public static void main(String[] args) throws IOException, ApiException, ClassNotFoundException {
    V1Pod pod =
        new V1PodBuilder()
            .withNewMetadata()
            .withName("apod")
            .endMetadata()
            .withNewSpec()
            .addNewContainer()
            .withName("www")
            .withImage("nginx")
            .withNewResources()
            .withLimits(new HashMap<>())
            .endResources()
            .endContainer()
            .endSpec()
            .build();
    Yaml.dump(pod);

    V1Service svc =
        new V1ServiceBuilder()
            .withNewMetadata()
            .withName("aservice")
            .endMetadata()
            .withNewSpec()
            .withSessionAffinity("ClientIP")
            .withType("NodePort")
            .addNewPort()
            .withProtocol("TCP")
            .withName("client")
            .withPort(8008)
            .withNodePort(8080)
            .withTargetPort(new IntOrString(8080))
            .endPort()
            .endSpec()
            .build();
    Yaml.dump(svc);

    // Read yaml configuration file, and deploy it
//    ApiClient client = Config.defaultClient();
    String kubeConfigPath = System.getProperty("user.home") + "/.kube/config";
    ApiClient client =
            ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
    Configuration.setDefaultApiClient(client);

    //  See issue #474. Not needed at most cases, but it is needed if you are using war
    //  packging or running this on JUnit.
//    Yaml.addModelMap("v1", "Service", V1Service.class);

    // Example yaml file can be found in $REPO_DIR/test-svc.yaml
//    File file = new File("test-svc.yaml");

      File file = null;
      FileOutputStream fos = null;
      try {
          // Create temp file.
          file = File.createTempFile("test-svc", ".yaml");

          // Delete temp file when program exits.
          file.deleteOnExit();

          // Write to temp file
          BufferedWriter out = new BufferedWriter(new FileWriter(file));
          out.write("apiVersion: v1");
          out.newLine();
          out.write("kind: Service");
          out.newLine();
          out.write("metadata:");
          out.newLine();
          out.write("  name: test-service");
          out.newLine();
          out.write("spec:");
          out.newLine();
          out.write("  type: ClusterIP");
          out.newLine();
          out.write("  selector:");
          out.newLine();
          out.write("    app: test-service");
          out.newLine();
          out.write("  ports:");
          out.newLine();
          out.write("  - name: port-of-container");
          out.newLine();
          out.write("    port: 8080");
          out.flush();
          out.close();
      } catch (IOException e) {
          e.printStackTrace();
      }




    V1Service yamlSvc = (V1Service) Yaml.load(file);

      System.out.println(yamlSvc.toString());

    // Deployment and StatefulSet is defined in apps/v1, so you should use AppsV1Api instead of
    // CoreV1API
    CoreV1Api api = new CoreV1Api();
      V1Service createResult= null;

    try {
        Call localVarCall = api.createNamespacedServiceCall("cheehove", yamlSvc, null, null, null, null);
        System.out.println("call "+localVarCall.request());
        createResult = api.createNamespacedService("cheehove", yamlSvc, null, null, null);
    }catch (ApiException a){
        System.out.println(a.getCode());
        System.out.println(a.getResponseBody());
    }
      System.out.println("======================= Yaml Dump create ====================");
    System.out.println(createResult.toString());
      System.out.println("======================= Yaml Dump created ====================");

//    V1Status deleteResult =
//        api.deleteNamespacedService(
//            yamlSvc.getMetadata().getName(),
//            "cheehove",
//            null,
//            null,
//            null,
//            null,
//            null,
//            new V1DeleteOptions());
//    System.out.println(deleteResult);
  }
}
