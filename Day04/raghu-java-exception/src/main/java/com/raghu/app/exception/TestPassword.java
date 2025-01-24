package com.rama.app.exception;

public class TestPassword {
    public static void main(String[] args) {
        TestPassword test=new TestPassword();
        try{
            test.password (123456);
        }catch(PasswordCheck e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally{
            System.out.println("session closed");
        }
    }

    public void password(int password) throws PasswordCheck {
        if (password != 12345) {
            throw new PasswordCheck("you entered wrong password.");
        }
    }
}
