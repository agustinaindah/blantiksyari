package com.android.blantik.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 31/07/2017.
 */

public class ItemContent implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("created_by_alias")
    @Expose
    private String createdByAlias;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("modified_by")
    @Expose
    private String modifiedBy;
    @SerializedName("revised")
    @Expose
    private String revised;
    @SerializedName("hits")
    @Expose
    private String hits;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("last_hits")
    @Expose
    private String lastHits;
    @SerializedName("is_popimage")
    @Expose
    private String isPopimage;
    @SerializedName("is_front")
    @Expose
    private String isFront;
    @SerializedName("is_config")
    @Expose
    private String isConfig;
    @SerializedName("config")
    @Expose
    private String config;
    @SerializedName("publish")
    @Expose
    private String publish;
    @SerializedName("content_id")
    @Expose
    private Integer contentId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("keyword")
    @Expose
    private String keyword;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("intro")
    @Expose
    private String intro;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("lang_id")
    @Expose
    private String langId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByAlias() {
        return createdByAlias;
    }

    public void setCreatedByAlias(String createdByAlias) {
        this.createdByAlias = createdByAlias;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getRevised() {
        return revised;
    }

    public void setRevised(String revised) {
        this.revised = revised;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLastHits() {
        return lastHits;
    }

    public void setLastHits(String lastHits) {
        this.lastHits = lastHits;
    }

    public String getIsPopimage() {
        return isPopimage;
    }

    public void setIsPopimage(String isPopimage) {
        this.isPopimage = isPopimage;
    }

    public String getIsFront() {
        return isFront;
    }

    public void setIsFront(String isFront) {
        this.isFront = isFront;
    }

    public String getIsConfig() {
        return isConfig;
    }

    public void setIsConfig(String isConfig) {
        this.isConfig = isConfig;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }
}
