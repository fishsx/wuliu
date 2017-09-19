package sx.me.po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
public class Board {
    private int boardId;
    private String boardTitle;
    private String boardContent;
    private Timestamp boardRegtime;

    @Id
    @Column(name = "board_id", nullable = false)
    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    @Basic
    @Column(name = "board_title", nullable = false, length = 20)
    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    @Basic
    @Column(name = "board_content", nullable = false, length = 500)
    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    @Basic
    @Column(name = "board_Regtime", nullable = false)
    public Timestamp getBoardRegtime() {
        return boardRegtime;
    }

    public void setBoardRegtime(Timestamp boardRegtime) {
        this.boardRegtime = boardRegtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (boardId != board.boardId) return false;
        if (boardTitle != null ? !boardTitle.equals(board.boardTitle) : board.boardTitle != null) return false;
        if (boardContent != null ? !boardContent.equals(board.boardContent) : board.boardContent != null) return false;
        if (boardRegtime != null ? !boardRegtime.equals(board.boardRegtime) : board.boardRegtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = boardId;
        result = 31 * result + (boardTitle != null ? boardTitle.hashCode() : 0);
        result = 31 * result + (boardContent != null ? boardContent.hashCode() : 0);
        result = 31 * result + (boardRegtime != null ? boardRegtime.hashCode() : 0);
        return result;
    }
}
