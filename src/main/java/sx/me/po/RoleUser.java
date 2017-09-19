package sx.me.po;

import javax.persistence.*;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
@Table(name = "role_user", schema = "wuliu", catalog = "")
public class RoleUser {
    private int roleUserId;
    private User userByUserId;
    private Role roleByRoleId;

    @Id
    @Column(name = "role_user_id", nullable = false)
    public int getRoleUserId() {
        return roleUserId;
    }

    public void setRoleUserId(int roleUserId) {
        this.roleUserId = roleUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleUser roleUser = (RoleUser) o;

        if (roleUserId != roleUser.roleUserId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return roleUserId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uid", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }
}
