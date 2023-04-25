package ma.ibsys.ibsysretailmanager.product;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TaxRate", description = "Enumeration of tax rates")
public enum TaxRate {
  @Schema(name = "20% tax rate")
  TWENTY(20),
  @Schema(name = "14% tax rate")
  FOURTEEN(14),
  @Schema(name = "10% tax rate")
  TEN(10),
  @Schema(name = "7% tax rate")
  SEVEN(7);

  private final int value;

  TaxRate(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
