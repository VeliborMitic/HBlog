package net.henryco.hblog.mvc.model.entity.extra;

import javax.persistence.*;

/**
 * @author Henry on 17/06/17.
 */
@Entity
public class PinnedBanner {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id @Column(name = "id", unique = true)
	private long id;

	@Column
	private String name;

	@Column
	private String mediaUrl;

	@Column
	private String mediaHref;

	@Column
	private boolean actual;


	@Override
	public String toString() {
		return "PinnedBanner{" +
				"id=" + id +
				", name='" + name + '\'' +
				", mediaUrl='" + mediaUrl + '\'' +
				", mediaHref='" + mediaHref + '\'' +
				", actual=" + actual +
				'}';
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public String getMediaHref() {
		return mediaHref;
	}

	public void setMediaHref(String mediaHref) {
		this.mediaHref = mediaHref;
	}

	public boolean isActual() {
		return actual;
	}

	public void setActual(boolean actual) {
		this.actual = actual;
	}
}
