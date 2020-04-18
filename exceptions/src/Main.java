import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(input());
            System.out.println(inputWithResources());
            System.out.println(inputSeveralExceptions());
            System.out.println(inputThrows());
        } catch (MyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String input() throws MyException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        try {
            s = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        if (s == null || s.isEmpty()) {
            throw new MyException("String can not be empty!");
        }
        return s;
    }


    private static String inputThrows() throws MyException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        try {
            s = reader.readLine();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        if (s == null || s.isEmpty()) {
            throw new MyException("String can not be empty!");
        }
        return s;
    }

    public static String inputWithResources() throws MyException {
        String s = null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            s = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (s == null || s.isEmpty()){
            throw new MyException ("String can not be empty!");
        }
        return s;
    }

    public static String inputSeveralExceptions () {
        String s = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            s = reader.readLine();
            if (s.equals("")) {
                throw new MyException("String can not be empty!");
            }
        } catch (IOException | MyException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }
}
