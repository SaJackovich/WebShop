package container;

public enum OrderStatus {

    ACCEPTED(1),
    CONFIRMED(2),
    FORM(3),
    SEND(4),
    COMPLETED(5),
    CANCELED(6);

    private int status;

    OrderStatus(int status) {
        this.status = status;
    }

    public int getStatus(){
        return status;
    }
}
