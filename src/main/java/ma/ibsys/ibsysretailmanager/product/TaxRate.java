package ma.ibsys.ibsysretailmanager.product;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TaxRate", description = "Énumération des taux de taxe")
public enum TaxRate {
  @Schema(name = "Taux de taxe de 20%")
  TWENTY(20),
  @Schema(name = "Taux de taxe de 14%")
  FOURTEEN(14),
  @Schema(name = "Taux de taxe de 10%")
  TEN(10),
  @Schema(name = "Taux de taxe de 7%")
  SEVEN(7);

  private final int value;

  TaxRate(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
