package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {
	private TaxCalculator taxCalculator; // deklarasi variabel yang ada pada metode calculateAnnualIncomeTax dalam memperbaiki code smell fiture envy

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
		//primitive obsesion
		// this.yearJoined = yearJoined;
		// this.monthJoined = monthJoined;
		// this.dayJoined = dayJoined;
		this.joinedDate = joinedDate;
		this.isForeigner = isForeigner;
		this.isMale = isMale;
		this.children = new ArrayList<>();
		this.taxCalculator = new TaxCalculator();  // deklarasi konstruktor untuk kelas employee yang terhubung dengan metode calculateAnnualIncomeTax dalam memperbaiki code smell fiture envy
		
		//primitive obsesion
		// childNames = new LinkedList<String>();
		// childIdNumbers = new LinkedList<String>();
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
		this.spouse = new spouse(spouseName, spouseIdNumber); //menggabungkan struktur data yang lebih kohesif pada Spouse
		//data clumps
		// this.spouseName = spouseName;
		// this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		children.add(new Child(childName, childIdNumber)); //menggabungkan struktur data yang lebih kohesif pada child
		//data clumps
		// childNames.add(childName);
		// childIdNumbers.add(childIdNumber);
	}
	//Long Method
// 	public int getAnnualIncomeTax() {
//         LocalDate date = LocalDate.now();
//         monthWorkingInYear = (date.getYear() == yearJoined) ? date.getMonthValue() - monthJoined : 12;
//         return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
//     }
	
	// Solusi refactor dalam menanggulangi Long Method di function getAnnualIncomeTax dan juga ini jadi solusi dalam code smell fiture envy
	public int calculateAnnualIncomeTax() {
        LocalDate currentDate = LocalDate.now();
        int monthsWorkedInYear = (currentDate.getYear() == joinedDate.getYear()) ? currentDate.getMonthValue() - joinedDate.getMonthValue() : 12;
        return TaxCalculator.calculateTax(monthlySalary, otherMonthlyIncome, monthsWorkedInYear, annualDeductible, spouse.isSpouseExist(), children.size());
    }


	// Inner class to represent Spouse
		private class Spouse {
			private String name;
			private String idNumber;

			public Spouse(String name, String idNumber) {
				this.name = name;
				this.idNumber = idNumber;
			}

			public boolean isSpouseExist() {
				return name != null && !name.isEmpty() && idNumber != null && !idNumber.isEmpty();
			}
		}

	 // Inner class to represent Child
		private class Child {
			private String name;
			private String idNumber;

			public Child(String name, String idNumber) {
				this.name = name;
				this.idNumber = idNumber;
			}
		}

}