public class BookController {
    private static BookController instance = new BookController();
    private BookController() {
    }
    public static BookController getInstance() {
        return instance;
    }
    public void process() {
    }
}
