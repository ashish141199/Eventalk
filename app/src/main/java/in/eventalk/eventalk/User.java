package in.eventalk.eventalk;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmObject;

public class User extends RealmObject {

    private Integer id;
    private String fbId;
    private String fullName;
    private String email;
    private String dob;
    private String gender;
    private String dp;
    private String location;
    private Boolean isVerified;


    public User() {
    }

    public User(User user) {
        setEmail(user.getEmail());
        setId(user.getId());
        setIsVerified(user.getIsVerified());
        setLocation(user.getLocation());
        setDob(user.getDob());
        setFbId(user.getFbId());
        setFullName(user.getFullName());
        setDp(user.getDp());
        setGender(user.getGender());

    }

    /**
     *
     * @return
     * The id
     */

    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The fbId
     */
    public String getFbId() {
        return fbId;
    }

    /**
     *
     * @param fbId
     * The fb_id
     */
    public void setFbId(String fbId) {
        this.fbId = fbId;
    }
    /**
     *
     * @return
     * The full name
     *
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @param full_name
     * The full name
     */
    public void setFullName(String full_name) {
        this.fullName = full_name;
    }
    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The dob
     */
    public String getDob() {
        return dob;
    }

    /**
     *
     * @param dob
     * The dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     *
     * @return
     * The gender
     */

    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     * The dp
     */
    public String getDp() {
        return dp;
    }

    /**
     *
     * @param dp
     * The dp
     */
    public void setDp(String dp) {
        this.dp = dp;
    }

    /**
     *
     * @return
     * The location
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The isVerified
     */
    public Boolean getIsVerified() {
        return isVerified;
    }

    /**
     *
     * @param isVerified
     * The is_verified
     */
    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }



    @Override
    public String toString() {
        return email + ", " + fullName + ", " + gender + ", " + location + ", " + dob + ", " + isVerified + ", " + dp;
    }
}