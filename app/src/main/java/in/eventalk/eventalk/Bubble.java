package in.eventalk.eventalk;

import java.util.HashMap;
import java.util.Map;

public class Bubble {

    private String first_name;
    private String last_name;
    private String title;
    private String body;
    private Integer views;
    private String caption;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The firstName
     */
    public String getFirstName() {
        return first_name;
    }

    /**
     *
     * The firstName
     */
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return last_name;
    }

    /**
     *
     * The last_name
     */
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The body
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @param body
     * The body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     *
     * @return
     * The views
     */
    public Integer getViews() {
        return views;
    }

    /**
     *
     * @param views
     * The views
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     *
     * @return
     * The caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     *
     * @param caption
     * The caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}