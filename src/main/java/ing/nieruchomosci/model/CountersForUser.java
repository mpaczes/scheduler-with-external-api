package ing.nieruchomosci.model;

public class CountersForUser {
	
	private Long numberOfRecords;
	
	public CountersForUser() {
	}

	public CountersForUser(Long numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public Long getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(Long numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

}
