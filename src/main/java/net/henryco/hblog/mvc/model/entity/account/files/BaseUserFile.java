package net.henryco.hblog.mvc.model.entity.account.files;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

/**
 * @author Henry on 05/07/17.
 */
@Entity
public class BaseUserFile {

	@Column @Id
	@GeneratedValue(strategy = AUTO)
	private long id;

	@Column
	private long userID;

	@Column(nullable = false)
	private String file;

	@Column
	private String name;

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;


	@Override
	public String toString() {
		return "BaseUserFile{" +
				"id=" + id +
				", userID=" + userID +
				", file='" + file + '\'' +
				", name='" + name + '\'' +
				", updateTime=" + updateTime +
				'}';
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
