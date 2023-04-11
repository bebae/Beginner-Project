package Book;

public class BookVO {
    private String id;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private int isbn;
    private String callSign;
    private String loanYN;

    public BookVO() {

    }
    public BookVO(String title, String author, String genre, String callSign, String loanYN) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.callSign = callSign;
        this.loanYN = loanYN;
    }

}
