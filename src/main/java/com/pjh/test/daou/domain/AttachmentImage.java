package com.pjh.test.daou.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "TB_ATT_IMG")
@SequenceGenerator(
        name = "ATT_IMG_SEQ_GENERATOR",
        sequenceName = "ATT_IMG_SEQ",
        initialValue = 31, allocationSize = 1)
public class AttachmentImage {
    @Id @GeneratedValue(
            strategy=GenerationType.IDENTITY,
            generator="ATT_IMG_SEQ"
    )
    @Column(name = "att_img_id")
    private Long id;
    private String imagePath;
    private String imageNickname;

    private boolean enabled = false;

    @OneToOne(mappedBy = "attachmentImage")
    ProductMaster productMaster;
}
