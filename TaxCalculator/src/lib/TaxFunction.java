package lib;

public class TaxFunction {

	private static final int TAX_RATE = 5;
    private static final int STANDARD_DEDUCTIBLE = 54000000;
    private static final int MARRIED_DEDUCTIBLE_INCREASE = 4500000;
    private static final int CHILD_DEDUCTIBLE_PER_CHILD = 1500000;
    private static final int MAX_CHILDREN_FOR_DEDUCTIBLE = 3;
	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		
		//Long method: Dimana terlalu banyak perulangan yang kurang efektif
		// int tax = 0;
		// if (numberOfMonthWorking > 12) {
		// 	System.err.println("More than 12 month working per year");
		// }
		// if (numberOfChildren > 3) {
		// 	numberOfChildren = 3;
		// }
		// if (isMarried) {
		// 	tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - (54000000 + 4500000 + (numberOfChildren * 1500000))));
		// }else {
		// 	tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - 54000000));
		// }

		int totalIncome = calculateTotalIncome(monthlySalary, otherMonthlyIncome, numberOfMonthsWorked);
        int totalDeductible = calculateTotalDeductible(annualDeductible, isMarried, numberOfChildren);

        int taxableIncome = totalIncome - totalDeductible;
        int tax = (int) Math.round(TAX_RATE * taxableIncome / 100);

        return Math.max(tax, 0); // Ensure tax is non-negative
			 
	}
	//metode yang dibagi menjadi metode tersendiri di setiap tugas nya agar mengurangi Long method di setiap metode masing-masing
	private static void validateMonthsWorked(int numberOfMonthsWorked) { 
        if (numberOfMonthsWorked > 12) {
            throw new IllegalArgumentException("Number of months worked per year exceeds 12"); //disini memakai teknik exception tanpa harus penhkondisian sebagai cara penanggulangan Long Method yang banyak seperti sebelumnya
        }
    }
	//metode yang dibagi menjadi metode tersendiri di setiap tugas nya agar mengurangi Long method di setiap metode masing-masing
	private static int calculateTotalIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorked) {
        return (monthlySalary + otherMonthlyIncome) * numberOfMonthsWorked;
    }
	//metode yang dibagi menjadi metode tersendiri di setiap tugas nya agar mengurangi Long method di setiap metode masing-masing
	private static int calculateTotalDeductible(int annualDeductible, boolean isMarried, int numberOfChildren) {
        int totalDeductible = annualDeductible;
        if (isMarried) {
            totalDeductible += MARRIED_DEDUCTIBLE_INCREASE;
        }
        totalDeductible += calculateChildDeductible(numberOfChildren);
        return totalDeductible;
    }
	//metode yang dibagi menjadi metode tersendiri di setiap tugas nya agar mengurangi Long method di setiap metode masing-masing
	private static int calculateChildDeductible(int numberOfChildren) {
        return Math.min(numberOfChildren, MAX_CHILDREN_FOR_DEDUCTIBLE) * CHILD_DEDUCTIBLE_PER_CHILD;
    }
}
