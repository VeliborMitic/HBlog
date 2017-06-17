package net.henryco.hblog.mvc.model;


import javax.persistence.*;
import java.util.Date;

/**
 * @author Henry on 15/06/17.
 */

@Entity
@Table(name = "standard_post_preview")
public class StandardPostPreview {


	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id @Column(name = "id") private long id;

	@Column(length = 40) private String title;
	@Column(length = 185) private String previewShort;
	@Column(length = 700) private String previewLong;
	@Column private String imgLink;

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;


	public StandardPostPreview() {
	}

	public StandardPostPreview(String title) {
		this.title = title;
	}

	public StandardPostPreview(long id, String title) {
		this(title);
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

	public String getPreviewShort() {
		return previewShort;
	}

	public void setPreviewShort(String previewShort) {
		this.previewShort = previewShort;
	}

	public String getPreviewLong() {
		return previewLong;
	}

	public void setPreviewLong(String previewLong) {
		this.previewLong = previewLong;
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
		return "StandardPostPreview{" +
				"id=" + id +
				", title='" + title + '\'' +
				", updateTime=" + updateTime +
				'}';
	}
}
