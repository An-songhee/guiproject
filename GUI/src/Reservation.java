public class Reservation {
    private int movieId;
    private int screeningId;
    private int accountId;
    private String groupId;
    private String date;
    private int seatId;
    private boolean canceled;
    private String title;
    private String screenDate;
    private int count;
    private String seats;
    private String time;

    public Reservation(int i, int screeningId2, int accountId2, String groupId, String date, int seatId, boolean canceled, String title, String screenDate, int count, String seats, String time) {
        this.movieId = i;
        this.screeningId = screeningId2;
        this.accountId = accountId2;
        this.groupId = groupId;
        this.date = date;
        this.seatId = seatId;
        this.canceled = canceled;
        this.title = title;
        this.screenDate = screenDate;
        this.count = count;
        this.seats = seats;
        this.time = time;  // 생성자에서 time 필드 초기화
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public void cancel() {
        this.canceled = true;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScreenDate() {
        return this.screenDate;
    }

    public void setScreenDate(String screenDate) {
        this.screenDate = screenDate;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSeats() {
        return this.seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    // ... 나머지 필드에 대해서도 동일하게 추가합니다.
}
