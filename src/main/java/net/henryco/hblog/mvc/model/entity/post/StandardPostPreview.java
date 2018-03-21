package net.henryco.hblog.mvc.model.entity.post;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Henry on 15/06/17.
 */

@Entity @Data @NoArgsConstructor
@Table(name = "standard_post_preview")
public class StandardPostPreview {


	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id @Column(name = "id") private long id;

	@Column(length = 40) private String title;
	@Column(length = 185) private String previewShort;
	@Column(length = 700) private String previewLong;
	@Column private String imgLink;
	@Column(length = 40) private String author;

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	public StandardPostPreview(String title) {
		this.title = title;
	}

	public StandardPostPreview(long id, String title) {
		this(title);
		this.id = id;
	}

}
