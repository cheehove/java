/*
 * Kubernetes
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v1.16.4
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package io.kubernetes.client.openapi.models;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.kubernetes.client.openapi.models.V1beta1VolumeAttachmentSource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * VolumeAttachmentSpec is the specification of a VolumeAttachment request.
 */
@ApiModel(description = "VolumeAttachmentSpec is the specification of a VolumeAttachment request.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-12-10T11:32:23.773Z[Etc/UTC]")
public class V1beta1VolumeAttachmentSpec {
  public static final String SERIALIZED_NAME_ATTACHER = "attacher";
  @SerializedName(SERIALIZED_NAME_ATTACHER)
  private String attacher;

  public static final String SERIALIZED_NAME_NODE_NAME = "nodeName";
  @SerializedName(SERIALIZED_NAME_NODE_NAME)
  private String nodeName;

  public static final String SERIALIZED_NAME_SOURCE = "source";
  @SerializedName(SERIALIZED_NAME_SOURCE)
  private V1beta1VolumeAttachmentSource source;


  public V1beta1VolumeAttachmentSpec attacher(String attacher) {
    
    this.attacher = attacher;
    return this;
  }

   /**
   * Attacher indicates the name of the volume driver that MUST handle this request. This is the name returned by GetPluginName().
   * @return attacher
  **/
  @ApiModelProperty(required = true, value = "Attacher indicates the name of the volume driver that MUST handle this request. This is the name returned by GetPluginName().")

  public String getAttacher() {
    return attacher;
  }


  public void setAttacher(String attacher) {
    this.attacher = attacher;
  }


  public V1beta1VolumeAttachmentSpec nodeName(String nodeName) {
    
    this.nodeName = nodeName;
    return this;
  }

   /**
   * The node that the volume should be attached to.
   * @return nodeName
  **/
  @ApiModelProperty(required = true, value = "The node that the volume should be attached to.")

  public String getNodeName() {
    return nodeName;
  }


  public void setNodeName(String nodeName) {
    this.nodeName = nodeName;
  }


  public V1beta1VolumeAttachmentSpec source(V1beta1VolumeAttachmentSource source) {
    
    this.source = source;
    return this;
  }

   /**
   * Get source
   * @return source
  **/
  @ApiModelProperty(required = true, value = "")

  public V1beta1VolumeAttachmentSource getSource() {
    return source;
  }


  public void setSource(V1beta1VolumeAttachmentSource source) {
    this.source = source;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1beta1VolumeAttachmentSpec v1beta1VolumeAttachmentSpec = (V1beta1VolumeAttachmentSpec) o;
    return Objects.equals(this.attacher, v1beta1VolumeAttachmentSpec.attacher) &&
        Objects.equals(this.nodeName, v1beta1VolumeAttachmentSpec.nodeName) &&
        Objects.equals(this.source, v1beta1VolumeAttachmentSpec.source);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attacher, nodeName, source);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1beta1VolumeAttachmentSpec {\n");
    sb.append("    attacher: ").append(toIndentedString(attacher)).append("\n");
    sb.append("    nodeName: ").append(toIndentedString(nodeName)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

