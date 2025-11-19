package cleancode.minesweeper.tobe;

public class Cell {

    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□"; // 체크되지 않음 (닫힌 상태)
    private static final String EMPTY_SIGN = "■";     // 열었더니 비어있음 (열린 상태)

    private int nearbyLandMineCount; // 셀의 근처 지뢰 수
    private boolean isLandMine;     // 지뢰 여부
    private boolean isFlagged;      // 깃발 꽂힌 여부
    private boolean isOpened;       // 열린 여부

    // Cell이 가진 속성 : 근처 지뢰 숫자, 지뢰 여부
    // Cell의 상태 : 깃발 유무, 열렸다/닫혔다, 사용자가 확인함

    private Cell(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        this.nearbyLandMineCount = nearbyLandMineCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    // 정적 팩토리 메서드 => 객체 생성의 역할을 하는 클래스 메서드 (의미)
    // : 직접적으로 생성자를 통해 객체를 생성하는 것이 아닌 메서드를 통해서 객체를 생성
    // 정적 팩토리 메서드를 사용하는 이유 : 이름을 별도로 줄 수 있어서 (여기선 of)
    public static Cell of(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        return new Cell(nearbyLandMineCount, isLandMine, isFlagged, isOpened);
    }

    public static Cell create() {
        return of(0, false, false, false); // 빈 Cell 만들기
    }

    public void turnOnLandMine() {
        this.isLandMine = true;
    }

    public void updateNearbyLandMineCount(int count) {
        this.nearbyLandMineCount = count;
    }

    public void flag() {
        this.isFlagged = true;
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened; // 깃발이 꽂혀있거나 열려있는 경우가 체크된 상태
    }

    public boolean isLandMine() {
        return isLandMine;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean hasLandMineCount() {
        return this.nearbyLandMineCount != 0;
    }

    // 지금 cell의 상태 결정하는 메서드
    public String getSign() {
        if (isOpened) {
            if (isLandMine) {
                return LAND_MINE_SIGN;
            }
            if (hasLandMineCount()) {
                return String.valueOf(nearbyLandMineCount);
            }
            return EMPTY_SIGN;
        } // 열렸을 때의 상황

        if (isFlagged) { // 깃발 꽂혀있는 경우
            return FLAG_SIGN;
        }

        return UNCHECKED_SIGN; // 열려있지도 않고 깃발도 없어서 체크 안 된 상태
    }
}
