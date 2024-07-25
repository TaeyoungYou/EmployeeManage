package system.management.total;

public class Total {
    private int totalAmount;        // 총액

    private int totalIncome;        // 소득 subWage + subOption
    private int incomeTax;          // 소득세 소득 * 소득세율
    private int salary;             // 지급액 소득 - 소득세

    private int VAT;                // 부가세 총액 * 부가세율

    private int billedAmount;       // 청구 금액 부가세 + 총액
    private int teamIncomeTax;      // 팀 소득세

    private int totalSalary;        // 내 금액 총액 - 소득 * 팀

}
