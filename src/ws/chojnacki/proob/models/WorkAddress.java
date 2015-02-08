package ws.chojnacki.proob.models;

import javax.persistence.Entity;

@Entity
public class WorkAddress extends Address {

  private String companyName;

  public WorkAddress() {
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
}
