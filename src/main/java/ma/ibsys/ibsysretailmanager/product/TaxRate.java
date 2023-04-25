package ma.ibsys.ibsysretailmanager.product;

public enum TaxRate {
  TWENTY(20),
  FOURTEEN(14),
  TEN(10),
  SEVEN(7);

  private final int value;

  TaxRate(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
