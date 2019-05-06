package com.lenily.dream.manager.entity;

import java.io.Serializable;

public class Order implements Serializable {
	private Integer id;

	private String userId;

	private String commodityCode;

	private Integer count;

	private Integer money;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode == null ? null : commodityCode.trim();
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		Order other = (Order) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
				&& (this.getCommodityCode() == null ? other.getCommodityCode() == null
						: this.getCommodityCode().equals(other.getCommodityCode()))
				&& (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()))
				&& (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
		result = prime * result + ((getCommodityCode() == null) ? 0 : getCommodityCode().hashCode());
		result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
		result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", userId=").append(userId);
		sb.append(", commodityCode=").append(commodityCode);
		sb.append(", count=").append(count);
		sb.append(", money=").append(money);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}