package com.mv.project;

import java.util.List;

public class InformacijeAPI {

        String id = "";
        String title = "";
        String year = "";
        String length = "";
        String rating = "";
        String ratingVotes = "";
        String poster = "";
        String plot = "";
        List<Cast> cast;


        public String getPlot() {
                return plot;
        }

        public void setPlot(String plot) {
                this.plot = plot;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getYear() {
                return year;
        }

        public void setYear(String year) {
                this.year = year;
        }

        public String getLength() {
                return length;
        }

        public void setLength(String length) {
                this.length = length;
        }

        public String getRating() {
                return rating;
        }

        public void setRating(String rating) {
                this.rating = rating;
        }

        public String getRatingVotes() {
                return ratingVotes;
        }

        public void setRatingVotes(String ratingVotes) {
                this.ratingVotes = ratingVotes;
        }

        public String getPoster() {
                return poster;
        }

        public void setPoster(String poster) {
                this.poster = poster;
        }


        public List<Cast> getCast() {
                return cast;
        }

        public void setCast(List<Cast> cast) {
                this.cast = cast;
        }

}
