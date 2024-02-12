package test;

import examSources.HighAccessEmployeesValidator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HighAccessEmployeesValidatorTest
{
    @Test
    void findHighAccessEmployeeTest1()
    {
        List<String> employeeAccess1 = new ArrayList<>();
        employeeAccess1.add("a");
        employeeAccess1.add("0549");
        List<String> employeeAccess2 = new ArrayList<>();
        employeeAccess2.add("b");
        employeeAccess2.add("0457");
        List<String> employeeAccess3 = new ArrayList<>();
        employeeAccess3.add("a");
        employeeAccess3.add("0532");
        List<String> employeeAccess4 = new ArrayList<>();
        employeeAccess4.add("a");
        employeeAccess4.add("0621");
        List<String> employeeAccess5 = new ArrayList<>();
        employeeAccess5.add("b");
        employeeAccess5.add("0540");

        List<List<String>> input = new ArrayList<>();
        input.add(employeeAccess1);
        input.add(employeeAccess2);
        input.add(employeeAccess3);
        input.add(employeeAccess4);
        input.add(employeeAccess5);

        List<String> results = HighAccessEmployeesValidator.findHighAccessEmployees(input);
        assertEquals(Arrays.asList("a"), results);
    }

    @Test
    void findHighAccessEmployeeTest2()
    {
        List<List<String>> employeeAccessTimes = Arrays.asList(
                Arrays.asList("qzgyyji","1945"),
                Arrays.asList("qzgyyji","1855"),
                Arrays.asList("jsxkxtugi","1859"),
                Arrays.asList("hhjuaxal","1940"),
                Arrays.asList("hhjuaxal","1831"),
                Arrays.asList("jsxkxtugi","1841"),
                Arrays.asList("hhjuaxal","1918"),
                Arrays.asList("jsxkxtugi","1941"),
                Arrays.asList("hhjuaxal","1852"));
        List<String> results = HighAccessEmployeesValidator.findHighAccessEmployees(employeeAccessTimes);
        assertEquals(Arrays.asList("hhjuaxal"), results);
    }

    private List<List<String>> inputFormer(String input)
    {
        //todo
        return null;
    }
}