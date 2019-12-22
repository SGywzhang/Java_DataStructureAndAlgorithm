public class Calculator {

    public static void main(String[] args) {

        String exp = "7+2*6-4";

        ArrayStack2 numStack  = new ArrayStack2(10);
        ArrayStack2 operStack  = new ArrayStack2(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        char ch = ' ';
        String keepNum = "";


        while(true){

            ch = exp.substring(index,index+1).charAt(0);

            if(ArrayStack2.isOper(ch)){

                if(operStack.isEmpty()){
                    operStack.push(ch);
                }
                else{
                    if(ArrayStack2.priority(ch) <=  ArrayStack2.priority(operStack.peek())){

                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        numStack.push(ArrayStack2.cal(num1,num2,oper));
                        operStack.push(ch);
                    }
                    else{
                        operStack.push(ch);
                    }
                }
            }
            else{
                //numStack.push(ch - '0');
                keepNum += ch;

                if(index == exp.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }
                else{
                    if(ArrayStack2.isOper(exp.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum ="";
                    }
                }
            }
            index++;
            if(index >= exp.length()){
                break;
            }

        }

        while(true){

            if(operStack.isEmpty()) break;
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            numStack.push(ArrayStack2.cal(num1,num2,oper));
        }

        System.out.printf("表达式 %s = %d", exp, numStack.peek());
    }
}

class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int val) {
        if (isFull()) {
            System.out.println("stack is full");
            return;
        }
        top++;
        stack[top] = val;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        int res = stack[top];
        top--;
        return res;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        for (int i = top; i >=0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    public int peek(){
        return stack[top];
    }

    public static int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }
        else if(oper == '+' || oper == '-'){
            return 0;
        }
        else{
            return -1;
        }
    }

    public static boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    public static int cal (int num1, int num2, int oper){
        int res = 0;
        switch(oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
