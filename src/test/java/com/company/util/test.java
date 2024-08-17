package com.company.util;

public class test {
    public static void main(String[] args) {
        String employeeCode1 = EmployeeCodeGenerator.generateEmployeeCode("销售部");
        System.out.println(employeeCode1);  // xsb20240815001

        String employeeCode2 = EmployeeCodeGenerator.generateEmployeeCode("销售部");
        System.out.println(employeeCode2);  // xsb20240815002
    }
}
