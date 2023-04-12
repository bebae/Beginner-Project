package Book;

public class BookVO {
    private String year;
    private String id;
    private String title;
    private String author;
    private String genre;
    private int isbn;
    private String callSign;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLoanYN() {
        return loanYN;
    }

    public void setLoanYN(String loanYN) {
        this.loanYN = loanYN;
    }

    private String loanYN;

    public BookVO() {

    }
    public BookVO(String title, String author, String genre, String callSign, String year, String loanYN) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.callSign = callSign;
        this.year = year;
        this.loanYN = loanYN;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" | ");
        sb.append(title).append(" | ");
        sb.append(author).append(" | ");
        sb.append(genre).append(" | ");
        sb.append(callSign).append(" | ");
        sb.append(year).append(" | ");
        sb.append(loanYN).append(" |");
        return sb.toString();
    }


}
