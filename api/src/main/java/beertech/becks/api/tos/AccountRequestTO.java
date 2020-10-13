package beertech.becks.api.tos;

import javax.validation.constraints.NotBlank;

public class AccountRequestTO {

  @NotBlank private String name;

  private String email;

  @NotBlank private String cpf;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
}
