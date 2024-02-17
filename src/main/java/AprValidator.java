public class AprValidator {
	public boolean aprIsValid(String aprPercent) {
		double apr = Double.parseDouble(aprPercent);
		return apr >= 0 && apr <= 10;
	}
}
