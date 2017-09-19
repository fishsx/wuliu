package sx.me.po;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
public class Dept {
    private int deptId;
    private String deptName;
    private Collection<User> usersByDeptId;

    @Id
    @Column(name = "dept_id", nullable = false)
    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "deptName", nullable = false, length = 20)
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dept dept = (Dept) o;

        if (deptId != dept.deptId) return false;
        if (deptName != null ? !deptName.equals(dept.deptName) : dept.deptName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = deptId;
        result = 31 * result + (deptName != null ? deptName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "deptByDeptId")
    public Collection<User> getUsersByDeptId() {
        return usersByDeptId;
    }

    public void setUsersByDeptId(Collection<User> usersByDeptId) {
        this.usersByDeptId = usersByDeptId;
    }
}
