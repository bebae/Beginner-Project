public class BookApplication {
    public static void main(String[] args) throws Exception {
        FrontController control = FrontController.getInstance();
        control.process();
    }
}
