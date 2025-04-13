package com.yogi.apitesting.Model;

import org.springframework.stereotype.Component;
import java.lang.String;

public class MovieData {

     String id;
     String url;
     String primarytitle;
     String originaltitle;
     Integer startyear;

     public MovieData(String Id, String url, String primarytitle , String originaltitle , Integer startyear)
     {
          this.setId(Id);
          this.setOriginaltitle(originaltitle);
          this.setPrimarytitle(primarytitle);
          this.setStartyear(startyear);
          this.setUrl(url);
     }

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getUrl() {
          return url;
     }

     public void setUrl(String url) {
          this.url = url;
     }

     public String getPrimarytitle() {
          return primarytitle;
     }

     public void setPrimarytitle(String primarytitle) {
          this.primarytitle = primarytitle;
     }

     public String getOriginaltitle() {
          return originaltitle;
     }

     public void setOriginaltitle(String originaltitle) {
          this.originaltitle = originaltitle;
     }

     public Integer getStartyear() {
          return startyear;
     }

     public void setStartyear(Integer startyear) {
          this.startyear = startyear;
     }

     public String toPrint() {
          return "MovieData{" +
                  "id='" + id + '\'' +
                  ", url='" + url + '\'' +
                  ", primarytitle='" + primarytitle + '\'' +
                  ", originaltitle='" + originaltitle + '\'' +
                  ", startyear=" + startyear +
                  '}';
     }
}

