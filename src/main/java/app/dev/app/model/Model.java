package app.dev.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KaranDeepSingh on 10/11/2018.
 */

public class Model {
    @SerializedName("title")
    private String title;
    @SerializedName("filename")
    private String filename;
    @SerializedName("description")
    private String description;

    public Model(String title, String filename, String description) {
        this.title = title;
        this.filename = filename;
        this.description = description;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public String setFilename(String filename) {
        this.filename = filename;
        return filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}