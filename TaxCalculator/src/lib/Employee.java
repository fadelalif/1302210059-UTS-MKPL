package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;

	private LocalDate joinedDate;

	private int monthWorkingInYear;

	private boolean isForeigner;
	private boolean gender; // true = Laki-laki, false = Perempuan

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
			LocalDate joinedDate, boolean isForeigner, boolean gender) {
		this.joinedDate = joinedDate;
	}

	public enum Grade {
		GRADE_1,
		GRADE_2,
		GRADE_3
	}

	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya
	 * (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3:
	 * 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */

	public void setMonthlySalary(Grade grade) {
		switch (grade) {
			case GRADE_1:
				this.monthlySalary = isForeigner ? 4500000 : 3000000;
				break;
			case GRADE_2:
				this.monthlySalary = isForeigner ? 4500000 : 5000000;
				break;
			case GRADE_3:
				this.monthlySalary = isForeigner ? 4500000 : 7000000;
				break;
		}
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}

	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax() {
		int monthWorkingInYear = LocalDate.now().getYear() == joinedDate.getYear()
				? LocalDate.now().getMonthValue() - joinedDate.getMonthValue()
				: 12;
		return TaxFunction.calculateTax(monthlySalary, 0, monthWorkingInYear, annualDeductible,
				spouseIdNumber.isEmpty(), childIdNumbers.size());
	}
}
