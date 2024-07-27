package system.management.employee;

public class Employee {
    private String name;
    private String birth;
    private String phone;
    private String email;
    private String exprience;
    private String role;
    private String etc;

    private Employee(String name, String birth, String phone, String email, String exp, String role, String etc){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.exprience = exp;
        this.role = role;
        this.birth = birth;
        this.etc = etc;
    }

    public static Employee createEmployee(String...data){
        String name = data[0];
        String birth = data[1];
        String phone = data[2];
        String email = data[3];
        String exp = data[4];
        String role = data[5];
        String etc = data[6];
        return new Employee(name, birth, phone, email, exp, role, etc);
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirth() {
        return birth;
    }

    public String getEmail() {
        return email;
    }
    public String getExprience() {
        return exprience;
    }

    public String getRole() {
        return role;
    }

    public String getEtc() {
        return etc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
