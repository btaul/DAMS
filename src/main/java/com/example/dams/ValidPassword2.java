package com.example.dams;

public class ValidPassword2 {

    public boolean truth;

    public boolean hasErrors(String password){
        char[] chars = password.toCharArray();
        StringBuilder numbers = new StringBuilder();
        StringBuilder upLetters = new StringBuilder();
        StringBuilder lowLetters = new StringBuilder();
        StringBuilder specialCase = new StringBuilder();
        int whiteSpace = 0;
        for(char c : chars){
            if(Character.isDigit(c)){
                numbers.append(c);
            }
            else if(Character.isUpperCase(c)){
                upLetters.append(c);
            }
            else if(Character.isLowerCase(c)){
                lowLetters.append(c);
            }
            else if(c == ' '){
                whiteSpace++;
            }
            else{
                specialCase.append(c);
            }
        }
        truth = (numbers.length() == 0 || upLetters.length() == 0 || lowLetters.length() ==0|| specialCase.length() == 0 || whiteSpace != 0);
        return truth;
    }
}
