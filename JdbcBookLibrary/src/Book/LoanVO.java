package Book;

import java.util.Date;

public class LoanVO {
    // 회원의 개인의 반납 목록 VO 입니다.
    private String id;              // 회원 ID
    private String longId;          // 대출 번호
    private String title;           // 책 제목
    private Date loanDate;          // 대출 날짜
    private Date exLonaDate;        // 예정 반납일
    private Date realReturnDate;    // 실제 반납일

    public LoanVO(String id, String title, Date loanDate, Date exLonaDate, Date realReturnDate) {
        this.id = id;
        this.title = title;
        this.loanDate = loanDate;
        this.exLonaDate = exLonaDate;
        this.realReturnDate = realReturnDate;
    }

    public LoanVO(String id, String longId, String title, Date exLonaDate) {
        this.id = id;
        this.longId = longId;
        this.title = title;
        this.exLonaDate = exLonaDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongId() {
        return longId;
    }

    public void setLongId(String longId) {
        this.longId = longId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getExLonaDate() {
        return exLonaDate;
    }

    public void setExLonaDate(Date exLonaDate) {
        this.exLonaDate = exLonaDate;
    }

    public Date getRealReturnDate() {
        return realReturnDate;
    }

    public void setRealReturnDate(Date realReturnDate) {
        this.realReturnDate = realReturnDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if(id != null) sb.append(id).append(" ");
        if(title != null) sb.append(title).append(" ");
        if(loanDate != null) sb.append(loanDate).append(" ");
        if(exLonaDate != null) sb.append(exLonaDate).append(" ");
        if(realReturnDate != null) sb.append(realReturnDate).append(" ");
        return sb.toString();
    }
}

