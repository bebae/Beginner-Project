package Book;

public class BookVO {

    private String id;
    private String title;
    private String author;
    private String genre;
    private String isbn;
    private String publisher;
    private int price;
    private String year;
    private String callSign;


    public BookVO() {
    }
    // 책 목록
    public BookVO(String title, String author, String genre, String callSign, String year, String loanYN) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.callSign = callSign;
        this.year = year;
        this.loanYN = loanYN;
    }

    // 책 입고 시 사용
    public BookVO(String title, String author, String genre, String publisher, String year, String price, String key, String call, String isbn) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.year = year;
        this.price = Integer.parseInt(price);
        this.id = key;
        this.callSign = call;
        this.isbn = isbn;
    }

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
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

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    public String getLoanYN() {
        return loanYN;
    }

    public void setLoanYN(String loanYN) {
        this.loanYN = loanYN;
    }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    private String loanYN;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" | ");
        if (id != null) sb.append(id).append(" | ");
        if (title != null) sb.append(title).append(" | ");
        if (author != null) sb.append(author).append(" | ");
        if (genre != null) sb.append(genre).append(" | ");
        if (publisher != null) sb.append(publisher).append(" | ");
        if (price > 0) sb.append(price).append(" | ");
        if (year != null) sb.append(year).append(" | ");
        if (callSign != null) sb.append(callSign).append(" | ");
        if (isbn != null) sb.append(isbn).append(" | ");
        if (loanYN != null) sb.append(loanYN).append(" |");
        return sb.toString();
    }


}
