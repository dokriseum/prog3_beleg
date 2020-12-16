/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package models.mediaDB;

public class UploaderImpl implements Uploader {
    private String name;

    public UploaderImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Uploader name='" + name + "'";
    }
}
