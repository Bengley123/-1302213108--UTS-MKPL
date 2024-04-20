package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private static final int SALARY_GRADE_1 = 3000000;
    private static final int SALARY_GRADE_2 = 5000000;
    private static final int SALARY_GRADE_3 = 7000000;
    private static final double FOREIGNER_SALARY_MULTIPLIER = 1.5;

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	//Primitive Obsesion
	// private int yearJoined;
	// private int monthJoined;
	// private int dayJoined;
	// private int monthWorkingInYear;

	private LocalDate joinedDate; //menggantikan penggunaan data primitive pada date, dan tentunya lebih kaya
	private boolean isForeigner;
	private boolean isMale; 
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	//primitive Obsesion
	// private String spouseName;
	// private String spouseIdNumber;
	//Primitive Obsesion
	// private List<String> childNames;
	// private List<String> childIdNumbers;

	private Spouse spouse; 
	private List<Child> children; //menggantikan penggunaan data primitive pada pembagian data child yang terlalu banyak, dan tentunya lebih kaya


	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	 public void setMonthlySalary(int grade) {    
        switch (grade) {
            case 1:
                monthlySalary = SALARY_GRADE_1;
                break;
            case 2:
                monthlySalary = SALARY_GRADE_2;
                break;
            case 3:
                monthlySalary = SALARY_GRADE_3;
                break;
            default:
                throw new IllegalArgumentException("Grade tidak valid");
        }
        if (isForeigner) {
            monthlySalary *= FOREIGNER_SALARY_MULTIPLIER;
        }
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
        LocalDate date = LocalDate.now();
        monthWorkingInYear = (date.getYear() == yearJoined) ? date.getMonthValue() - monthJoined : 12;
        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
    }

}
