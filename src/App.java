public class App {
    public static void main(String[] args) {
        ArrayStack<Integer> st = new ArrayStack<Integer>();

        for (int i = 0; i < 10; i++) {
            st.push(i);
        }


        System.out.println("st2");

        ArrayStack.rearrangeStack(st, 7);

        System.out.println("st");

        while (!st.isEmpty()) {
            System.out.println(st.pop());
        }
    }
}
