package net.henryco.hblog.mvc.model;


import javax.persistence.*;
import java.util.Date;

/**
 * @author Henry on 15/06/17.
 */

@Entity
@Table(name = "standard_posts")
public class StandardPost {


	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id @Column(name = "id") private long id;

	@Column private String title;
	@Column(length = 20480) private String content;
	@Column private String preview;
	@Column private String imgLink;

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;


	public StandardPost() {
	}

	public StandardPost(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public StandardPost(long id, String title, String content) {
		this(title, content);
		this.id = id;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	@Override
	public String toString() {
		return "StandardPost{" +
				"id=" + id +
				", title='" + title + '\'' +
				", updateTime=" + updateTime +
				'}';
	}
}
