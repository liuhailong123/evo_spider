package cn.com.evo.integration.xjdx.content.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author rf
 * @date 2019/8/2
 */
public class Movie {
    private String movieCode;

    private String movieType;

    private String url;

    public Movie() {
    }

    public Movie(String movieCode, String movieType, String url) {
        this.movieCode = movieCode;
        this.movieType = movieType;
        this.url = url;
    }

    public String getMovieCode() {
        return movieCode;
    }

    public void setMovieCode(String movieCode) {
        this.movieCode = movieCode;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("movieCode", movieCode)
                .append("movieType", movieType)
                .append("url", url)
                .toString();
    }
}
