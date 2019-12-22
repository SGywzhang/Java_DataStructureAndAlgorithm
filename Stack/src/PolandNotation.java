import java.util.*;

public class PolandNotation {

    public static void main(String[] args) {

        String suffixExpression = "3 4 + 5 * 6 -";

        List<String> list = getListString(suffixExpression);

        int res = calculate(list);
        System.out.println(res);

        List<String> infixExpression = toInfixExpression("1+((2+3)*4)-5");
        System.out.println("infix expression--->"+infixExpression);

        List<String> suffixExp = infixToSuffixExpression(infixExpression);

        System.out.println(suffixExp);

        System.out.println(calculate(suffixExp));


    }


    public static  List<String> infixToSuffixExpression(List<String> list){
        Stack<String> stack = new Stack<>();
        List<String> tempList = new LinkedList<>();

        for(String item : list){

            if(item.matches("\\d+")){
                tempList.add(item);
            }
            else if(item.equals("(")){
                stack.push(item);
            }
            else if(item.equals(")")){
                while(!stack.peek().equals("(")) {
                    tempList.add(stack.pop());
                }
                stack.pop();
            }
            else {
                while (!stack.isEmpty() && Operation.getPriority(item) <= Operation.getPriority(stack.peek())) {
                    tempList.add(stack.pop());
                }
                stack.push(item);
            }
        }

        while(!stack.isEmpty()){
            tempList.add(stack.pop());
        }

        return tempList;
    }





    public static List<String> toInfixExpression(String str){

        List<String> list = new ArrayList<>();

        int index = 0;
        StringBuilder temp;
        char ch =' ';

        while(index < str.length()){

            if( (ch=str.charAt(index)) < 48 ||
                    (ch=str.charAt(index)) >57 ){
                list.add(""+ch);
                index++;
            }
            else{
                temp = new StringBuilder();
                while(index < str.length() && (ch=str.charAt(index)) >= 48 && (ch=str.charAt(index)) <=57){
                    temp.append(ch);
                    index++;
                }
                list.add(temp.toString());
            }
        }
        return list;
    }

    public static List<String> getListString (String suffixExpression){

        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();

        for(String s:split){
            list.add(s);
        }
        return list;
    }

    public static int calculate(List<String> list){

        Stack<String> stack = new Stack<>();

        for(String s: list){

            if(s.matches("\\d+")){
                stack.push(s);
            }
            else{
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;

                switch (s){
                    case "+":
                       res = num1 + num2;
                       break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        break;
                }
                stack.push(""+res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}


class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getPriority(String oper){

        int res = 0;

        switch (oper){
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("Invalid operation");
                break;
        }
        return res;
    }
}