package dto;

import java.util.List;

public class Model {

  private String modelName;
  private List<Version> versionList;

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public List<Version> getVersionList() {
    return versionList;
  }

  public void setVersionList(List<Version> versionList) {
    this.versionList = versionList;
  }

}
