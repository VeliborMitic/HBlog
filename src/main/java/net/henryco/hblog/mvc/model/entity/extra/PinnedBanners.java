package net.henryco.hblog.mvc.model.entity.extra;

import javax.persistence.*;

/**
 * @author Henry on 17/06/17.
 */
@Entity
public class PinnedBanners {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id @Column(name = "id", unique = true)
	private long id;

	@Column
	private String mediaUrl;

	@Column
	private String mediaHref;

	@Column
	private boolean actual;

	public PinnedBanners() {}

	public PinnedBanners(String mediaUrl, String mediaHref) {
		this();
		this.mediaUrl = mediaUrl;
		this.mediaHref = mediaHref;
	}

	public PinnedBanners(String mediaUrl, String mediaHref, boolean actual) {
		this(mediaUrl, mediaHref);
		this.actual = actual;
	}

	@Override
	public String toString() {
		return "PinnedBanners{" +
				"id=" + id +
				", mediaUrl='" + mediaUrl + '\'' +
				", mediaHref='" + mediaHref + '\'' +
				", actual=" + actual +
				'}';
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
