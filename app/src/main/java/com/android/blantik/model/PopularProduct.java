package com.android.blantik.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class PopularProduct implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("subcat_id")
    @Expose
    private String subcatId;
    @SerializedName("sub_subcat_id")
    @Expose
    private String subSubcatId;
    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("reg_id")
    @Expose
    private String regId;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("price_promo")
    @Expose
    private Object pricePromo;
    @SerializedName("price_reseller")
    @Expose
    private String priceReseller;
    @SerializedName("nego")
    @Expose
    private Object nego;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("images")
    @Expose
    private String images;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_update")
    @Expose
    private String dateUpdate;
    @SerializedName("hits")
    @Expose
    private String hits;
    @SerializedName("publish")
    @Expose
    private String publish;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("opsi")
    @Expose
    private Object opsi;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("premium_type")
    @Expose
    private Object premiumType;
    @SerializedName("is_resize")
    @Expose
    private Object isResize;
    @SerializedName("is_reseller")
    @Expose
    private String isReseller;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("is_expired")
    @Expose
    private String isExpired;
    @SerializedName("point")
    @Expose
    private String point;
    @SerializedName("is_vip")
    @Expose
    private String isVip;
    @SerializedName("harga_reseller")
    @Expose
    private String hargaReseller;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("reputation_bad")
    @Expose
    private Object reputationBad;
    @SerializedName("reputation_good")
    @Expose
    private Object reputationGood;
    @SerializedName("point_total")
    @Expose
    private Object pointTotal;
    @SerializedName("point_used")
    @Expose
    private Object pointUsed;
    @SerializedName("point_balance")
    @Expose
    private Object pointBalance;
    @SerializedName("params")
    @Expose
    private String params;
    @SerializedName("expired_date")
    @Expose
    private String expiredDate;
    @SerializedName("status_account")
    @Expose
    private String statusAccount;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("member_id")
    @Expose
    private String memberId;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("image_user")
    @Expose
    private String imageUser;
    @SerializedName("address_user")
    @Expose
    private String addressUser;
    @SerializedName("pin_bb_user")
    @Expose
    private String pinBbUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getSubcatId() {
        return subcatId;
    }

    public void setSubcatId(String subcatId) {
        this.subcatId = subcatId;
    }

    public String getSubSubcatId() {
        return subSubcatId;
    }

    public void setSubSubcatId(String subSubcatId) {
        this.subSubcatId = subSubcatId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Object getPricePromo() {
        return pricePromo;
    }

    public void setPricePromo(Object pricePromo) {
        this.pricePromo = pricePromo;
    }

    public String getPriceReseller() {
        return priceReseller;
    }

    public void setPriceReseller(String priceReseller) {
        this.priceReseller = priceReseller;
    }

    public Object getNego() {
        return nego;
    }

    public void setNego(Object nego) {
        this.nego = nego;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Object getOpsi() {
        return opsi;
    }

    public void setOpsi(Object opsi) {
        this.opsi = opsi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getPremiumType() {
        return premiumType;
    }

    public void setPremiumType(Object premiumType) {
        this.premiumType = premiumType;
    }

    public Object getIsResize() {
        return isResize;
    }

    public void setIsResize(Object isResize) {
        this.isResize = isResize;
    }

    public String getIsReseller() {
        return isReseller;
    }

    public void setIsReseller(String isReseller) {
        this.isReseller = isReseller;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(String isExpired) {
        this.isExpired = isExpired;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getHargaReseller() {
        return hargaReseller;
    }

    public void setHargaReseller(String hargaReseller) {
        this.hargaReseller = hargaReseller;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getReputationBad() {
        return reputationBad;
    }

    public void setReputationBad(Object reputationBad) {
        this.reputationBad = reputationBad;
    }

    public Object getReputationGood() {
        return reputationGood;
    }

    public void setReputationGood(Object reputationGood) {
        this.reputationGood = reputationGood;
    }

    public Object getPointTotal() {
        return pointTotal;
    }

    public void setPointTotal(Object pointTotal) {
        this.pointTotal = pointTotal;
    }

    public Object getPointUsed() {
        return pointUsed;
    }

    public void setPointUsed(Object pointUsed) {
        this.pointUsed = pointUsed;
    }

    public Object getPointBalance() {
        return pointBalance;
    }

    public void setPointBalance(Object pointBalance) {
        this.pointBalance = pointBalance;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getStatusAccount() {
        return statusAccount;
    }

    public void setStatusAccount(String statusAccount) {
        this.statusAccount = statusAccount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public String getPinBbUser() {
        return pinBbUser;
    }

    public void setPinBbUser(String pinBbUser) {
        this.pinBbUser = pinBbUser;
    }
}
