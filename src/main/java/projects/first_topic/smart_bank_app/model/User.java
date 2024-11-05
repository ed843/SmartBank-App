package projects.first_topic.smart_bank_app.model;

import java.time.LocalDateTime;

public class User {
    private Integer user_id;
    private String user_name;
    private String user_type;
    private String password;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private Integer credit_score = 300;
    private double annual_income;
    private double loan_amount = 0.00;
    private String registration_date;

    public User() {
    }

    public User(String user_name, String user_type, String password, String first_name, String last_name, String phone, String email, Integer credit_score, double annual_income, LocalDateTime registration_date) {
        this.user_name = user_name;
        this.user_type = user_type;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
        this.credit_score = credit_score;
        this.annual_income = annual_income;
        this.registration_date = registration_date.toString();
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCredit_score() {
        return credit_score;
    }

    public void setCredit_score(Integer credit_score) {
        this.credit_score = credit_score;
    }

    public double getAnnual_income() {
        return annual_income;
    }

    public void setAnnual_income(double annual_income) {
        this.annual_income = annual_income;
    }

    public double getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(double loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }
}
